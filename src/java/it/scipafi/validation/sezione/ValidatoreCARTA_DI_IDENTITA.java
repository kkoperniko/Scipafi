/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.sezione;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.mapping.ScipafiElencoCampiRiscontroCartaIdentita;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class ValidatoreCARTA_DI_IDENTITA {
    
    private static final Logger log = Logger.getLogger(ValidatoreCARTA_DI_IDENTITA.class.toString());
    public static final ValidatoreCARTA_DI_IDENTITA INSTANCE = new ValidatoreCARTA_DI_IDENTITA();
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
    
    public boolean controllaSezioneCampiCartaIdentita(TbCampoRiscontro tbcampo)
    {        
            boolean esitoValidazione = false;
            /*
             * CAMPO_CI_NUMERO
             * */
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroCartaIdentita.CAMPO_CI_NUMERO))
            {
                if ((tbcampo.getFlagobbligosistema() > 0 ) && ((null==tbcampo.getValoreInput()) || (tbcampo.getValoreInput().isEmpty())))
                {
                    tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroCartaIdentita.NEGATIVO);
                    tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroCartaIdentita.NEGATIVO).getDescrizione()); 
                    tbcampo.setObbligatorioEdit(true);
                    LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                    LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_DATI_OBBLIGATORI_MANCANTI);
                    
                }
                else
                {    
                    if (null==tbcampo.getValoreInput())
                    {
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroCartaIdentita.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroCartaIdentita.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_BANCA_DATI_NON_AUTORIZZATA);
                    }
                    else
                    {    
                        if (tbcampo.getValoreInput().length() <= 9)
                        {
                           this.sezValorizzata = true;
                           esitoValidazione = true;
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroCartaIdentita.POSITIVO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroCartaIdentita.POSITIVO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                        }
                        else
                        {
                               tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroCartaIdentita.NEGATIVO);  
                               tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroCartaIdentita.NEGATIVO).getDescrizione()); 
                               ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                               LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                               LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);
                        }
                    }    
                }    
            } 
            
            
            /*
             * CAMPO_CI_DATASCADENZA
             * */
            
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroCartaIdentita.CAMPO_CI_DATA_SCADENZA))
            {
                    if ((!tbcampo.getValoreInput().isEmpty()) && (!Util.isDate(tbcampo.getValoreInput())))
                    {
                            tbcampo.setValoreInputData(null);
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroCartaIdentita.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroCartaIdentita.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);
                    }
                    else
                    {
                        if (!tbcampo.getValoreInput().isEmpty())
                        {
                           tbcampo.setValoreInputData(Util.convertDataStringToDate(tbcampo.getValoreInput()));
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroCartaIdentita.POSITIVO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroCartaIdentita.POSITIVO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                        }
                        else
                        {
                                esitoValidazione = true;
                                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroCartaIdentita.NULLO);  
                                tbcampo.setValoreOutput(""); 
                                tbcampo.setValoreInputData(null);
                        }    
                    }  
            }
            return esitoValidazione;
    }
}
