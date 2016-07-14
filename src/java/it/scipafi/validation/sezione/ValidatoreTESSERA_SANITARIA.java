/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.sezione;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.mapping.ScipafiElencoCampiRiscontroTesseraSanitaria;
import it.scipafi.validation.mapping.ScipafiElencoCampiRiscontroTesseraSanitaria;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class ValidatoreTESSERA_SANITARIA {
    
    private static final Logger log = Logger.getLogger(ValidatoreTESSERA_SANITARIA.class.toString());
    private List<TbScipafiTipoEsitoCampo> listEsitiCampo;
    private boolean esitoValoreNumeroTesseraSanitaria = false;
    
    public static final ValidatoreTESSERA_SANITARIA INSTANCE = new ValidatoreTESSERA_SANITARIA();
    public boolean sezValorizzata;
    
    
    
    public boolean isSezValorizzata()
    {
        return this.sezValorizzata;
    }
    
    
    public boolean controllaSezioneCampiDatiTesseraSanitaria(TbCampoRiscontro tbcampo,List<TbCampoRiscontro> listTbCampoRiscontro)
    {        
            boolean esitoValidazioneCampi = false;
            
            
            //DATA SCADENZA
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroTesseraSanitaria.CAMPO_TS_DATA_SCADENZA))
            {
                    if ((!tbcampo.getValoreInput().isEmpty()) && (!Util.isDate(tbcampo.getValoreInput())))
                    {
                            tbcampo.setValoreInputData(null);
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroTesseraSanitaria.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroTesseraSanitaria.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);
                    }
                    else
                    {
                        if (!tbcampo.getValoreInput().isEmpty())
                        {
                           if (!esitoValoreNumeroTesseraSanitaria)
                           {
                                for (TbCampoRiscontro tbcampo_x :listTbCampoRiscontro)
                                {
                                  if (tbcampo_x.getCodice().equals(ScipafiElencoCampiRiscontroTesseraSanitaria.CAMPO_TS_NUMERO))
                                    {
                                        if ((tbcampo_x.getFlagobbligosistema() > 0 ) && ((null==tbcampo_x.getValoreInput()) || (tbcampo_x.getValoreInput().isEmpty())))
                                        {
                                            tbcampo_x.setValoreSemaforo(ScipafiElencoCampiRiscontroTesseraSanitaria.NEGATIVO);
                                            tbcampo_x.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroTesseraSanitaria.NEGATIVO).getDescrizione()); 
                                            tbcampo_x.setObbligatorioEdit(true);
                                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo_x.getBancadatiid(), tbcampo_x, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_DATI_OBBLIGATORI_MANCANTI);
                                        }
                                        else
                                        {    
                                            if (null==tbcampo_x.getValoreInput())
                                            {
                                                    tbcampo_x.setValoreSemaforo(ScipafiElencoCampiRiscontroTesseraSanitaria.NEGATIVO);  
                                                    tbcampo_x.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroTesseraSanitaria.NEGATIVO).getDescrizione()); 
                                                    LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo_x.getBancadatiid(), tbcampo_x, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                                                    LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_BANCA_DATI_NON_AUTORIZZATA);
                                            }
                                            else
                                            {    
                                                if (Util.isNumericoIntero(tbcampo_x.getValoreInput()))
                                                {
                                                    Integer esito_controllo = SchipafiUtilityCheckDati.INSTACE.ESITO_CONTROLLO_OK;
                                                    esito_controllo = SchipafiUtilityCheckDati.INSTACE.checkControlLHUNTesseraSanitariaNumero(tbcampo_x.getValoreInput());
                                                    if(esito_controllo.equals(SchipafiUtilityCheckDati.INSTACE.ESITO_CONTROLLO_OK))
                                                    {
                                                       this.sezValorizzata = true;
                                                       esitoValidazioneCampi = true;
                                                       esitoValoreNumeroTesseraSanitaria = true;
                                                       tbcampo_x.setValoreSemaforo(ScipafiElencoCampiRiscontroTesseraSanitaria.POSITIVO);  
                                                       tbcampo_x.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroTesseraSanitaria.POSITIVO).getDescrizione()); 
                                                       ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo_x.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                                                       LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo_x.getBancadatiid(), tbcampo_x, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                                                       LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                           
                                                    }
                                                    else
                                                    {
                                                       tbcampo_x.setValoreSemaforo(ScipafiElencoCampiRiscontroTesseraSanitaria.SINTATTICAMENTE_ERRATO);  
                                                       tbcampo_x.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroTesseraSanitaria.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                                                       ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo_x.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                                                       LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo_x.getBancadatiid(), tbcampo_x, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                                                       LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                                                    }
                                                }
                                                else
                                                {
                                                       tbcampo_x.setValoreSemaforo(ScipafiElencoCampiRiscontroTesseraSanitaria.NEGATIVO);  
                                                       tbcampo_x.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroTesseraSanitaria.NEGATIVO).getDescrizione()); 
                                                       ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo_x.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                                                       LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo_x.getBancadatiid(), tbcampo_x, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                                                       LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);
                                                }
                                            }    
                                        }
                                        break;
                                    } 
                                }
                           } 
                           tbcampo.setValoreInputData(Util.convertDataStringToDate(tbcampo.getValoreInput()));
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroTesseraSanitaria.POSITIVO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroTesseraSanitaria.POSITIVO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                        }
                        else
                        {
                                esitoValidazioneCampi = true;
                                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroTesseraSanitaria.NULLO);  
                                tbcampo.setValoreOutput(""); 
                                tbcampo.setValoreInputData(null);
                        }    
                    }    
            }
            // NUMERO TESSERA SANITARIA
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroTesseraSanitaria.CAMPO_TS_NUMERO))
            {
                    esitoValoreNumeroTesseraSanitaria = false;
                    if (null==tbcampo.getValoreInput())
                    {
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroTesseraSanitaria.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroTesseraSanitaria.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_BANCA_DATI_NON_AUTORIZZATA);
                    }
                    else
                    {    
                        if (Util.isNumericoIntero(tbcampo.getValoreInput()))
                        {
                            Integer esito_controllo = SchipafiUtilityCheckDati.INSTACE.ESITO_CONTROLLO_OK;
                            esito_controllo = SchipafiUtilityCheckDati.INSTACE.checkControlLHUNTesseraSanitariaNumero(tbcampo.getValoreInput());
                            if(esito_controllo.equals(SchipafiUtilityCheckDati.INSTACE.ESITO_CONTROLLO_OK))
                            {
                               this.sezValorizzata = true;
                               esitoValidazioneCampi = true;
                               esitoValoreNumeroTesseraSanitaria = true;
                               tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroTesseraSanitaria.POSITIVO);  
                               tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroTesseraSanitaria.POSITIVO).getDescrizione()); 
                               ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                               LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                               LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                           
                            }
                            else
                            {
                               tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroTesseraSanitaria.SINTATTICAMENTE_ERRATO);  
                               tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroTesseraSanitaria.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                               ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                               LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                               LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                            }
                        }
                        else
                        {
                                esitoValidazioneCampi = true;
                                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroTesseraSanitaria.NULLO);  
                                tbcampo.setValoreOutput(""); 
                                /*
                               tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroTesseraSanitaria.NEGATIVO);  
                               tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroTesseraSanitaria.NEGATIVO).getDescrizione()); 
                               ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                               LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                               LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);
                                */
                        }
                    }    
            }
            return esitoValidazioneCampi;
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
    
}
