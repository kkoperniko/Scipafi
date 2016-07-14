/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.sezione;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.mapping.ScipafiElencoCampiRiscontroAltroDocumento;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class ValidatoreALTRO_DOCUMENTO {
    
    private static final Logger log = Logger.getLogger(ValidatoreALTRO_DOCUMENTO.class.toString());
    public static final ValidatoreALTRO_DOCUMENTO INSTANCE = new ValidatoreALTRO_DOCUMENTO();
    private List<TbScipafiTipoEsitoCampo> listEsitiCampo;
    public boolean sezValorizzata;

    public boolean isSezValorizzata()
    {
        return this.sezValorizzata;
    }
    
    public void setListEsitiCampo(List<TbScipafiTipoEsitoCampo> listEsitiCampo)
    {
        this.listEsitiCampo = listEsitiCampo;
    }

    /**
     * @return the listEsitiCampo
     */
    public List<TbScipafiTipoEsitoCampo> getListEsitiCampo() {
        return listEsitiCampo;
    }
    
    public TbScipafiTipoEsitoCampo getTbScipafiTipoEsitoCampoDaLista(Integer valueID)
    {
        TbScipafiTipoEsitoCampo result = new TbScipafiTipoEsitoCampo();
        for (TbScipafiTipoEsitoCampo tbEsitoCampo : getListEsitiCampo())
        {
            if (tbEsitoCampo.getTipoEsitoCampoId().equals(valueID))
            {    
                result.setSemaforoId(tbEsitoCampo.getSemaforoId());
                result.setDescrizione(tbEsitoCampo.getDescrizione());
                result.setTipoEsitoCampoId(tbEsitoCampo.getTipoEsitoCampoId());
                break;
            }    
        }
        return result;
    }  
    
    public boolean controllaSezioneCampiAltroDocumento(TbCampoRiscontro tbcampo)
    {        
            boolean esitoValidazione = false;
            /*
             * CAMPO_AD_TIPO_DOC
             * */
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroAltroDocumento.CAMPO_AD_TIPO_DOC))
            {
                    if ((null==tbcampo.getValoreInput() || (tbcampo.getValoreInput().isEmpty())))
                    {
                            esitoValidazione = true;
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroAltroDocumento.NULLO);  
                            tbcampo.setValoreOutput("");  
                    }
                    else
                    {    
                        if(Util.isTipoDocumentoAD(tbcampo.getValoreInput()))
                        {
                           this.sezValorizzata = true;
                           esitoValidazione = true;
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroAltroDocumento.POSITIVO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroAltroDocumento.POSITIVO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                        }
                        else
                        {
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroAltroDocumento.NEGATIVO);
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroAltroDocumento.NEGATIVO).getDescrizione()); 
                            tbcampo.setObbligatorioEdit(true);
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_DATI_OBBLIGATORI_MANCANTI);
                     
                        }
                    }    
            } 
            
            
            /*
             * CAMPO_AD_NUMERO_DOC
             * */
            
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroAltroDocumento.CAMPO_AD_NUMERO_DOC))
            {
                    if ( (null==tbcampo.getValoreInput()) || (tbcampo.getValoreInput().isEmpty()))
                    {
                            esitoValidazione = true;
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroAltroDocumento.NULLO);  
                            tbcampo.setValoreOutput("");  
                    }
                    else
                    {    
                        if (!Util.isAlfanumericoSemplice(tbcampo.getValoreInput()) || (!Util.contrMaxLength(tbcampo.getValoreInput(),50)))
                        {
                               tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroAltroDocumento.NEGATIVO);  
                               tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroAltroDocumento.NEGATIVO).getDescrizione()); 
                               ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                               LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                               LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);
                        }
                        else
                        {
                           this.sezValorizzata = true;
                           esitoValidazione = true;
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroAltroDocumento.POSITIVO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroAltroDocumento.POSITIVO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                        }
                    }    
            }
            
            /*
             * CAMPO_AD_DATASCADENZA
             * */
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroAltroDocumento.CAMPO_AD_DATA_SCADENZA))
            {
                    if ( (null==tbcampo.getValoreInput()) || (tbcampo.getValoreInput().isEmpty()))
                    {
                            esitoValidazione = true;
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroAltroDocumento.NULLO);  
                            tbcampo.setValoreOutput(""); 
                            tbcampo.setValoreInputData(null);  
                    }
                    else
                    {
                           tbcampo.setValoreInputData(Util.convertDataStringToDate(tbcampo.getValoreInput()));
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroAltroDocumento.POSITIVO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroAltroDocumento.POSITIVO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                    }  
            } 
            
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroAltroDocumento.CAMPO_AD_DOC_RUBATO_SMARRITO))
            {
                if ( (null==tbcampo.getValoreInput()) || (tbcampo.getValoreInput().isEmpty()))
                    {
                            esitoValidazione = true;
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroAltroDocumento.NULLO);  
                            tbcampo.setValoreOutput(""); 
                    }
                    else
                    {    
                           this.sezValorizzata = true;
                           esitoValidazione = true;
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroAltroDocumento.POSITIVO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroAltroDocumento.POSITIVO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                    }    
                }   
                                
            return esitoValidazione;
    }
}

