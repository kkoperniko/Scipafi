/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.sezione;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.mapping.ScipafiElencoCampiRiscontroDatiIdentificativi;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class ValidatoreDATI_IDENTIFICATIVI {
    
    private static final Logger log = Logger.getLogger(ValidatoreDATI_IDENTIFICATIVI.class.toString());
    public static final ValidatoreDATI_IDENTIFICATIVI INSTANCE = new ValidatoreDATI_IDENTIFICATIVI();
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
    
    public boolean controllaSezioneCampiDatiIdentificativi(TbCampoRiscontro tbcampo)
    {        
            boolean esitoValidazione = false;
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDatiIdentificativi.CODICE_FISCALE))
            {
                if ((tbcampo.getFlagobbligosistema() > 0 ) && ((null==tbcampo.getValoreInput()) || (tbcampo.getValoreInput().isEmpty())))
                {
                    tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);
                    tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                    tbcampo.setObbligatorioEdit(true);
                    LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                    LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_DATI_OBBLIGATORI_MANCANTI);
                    
                }
                else
                {    
                    if (null==tbcampo.getValoreInput())
                    {
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_BANCA_DATI_NON_AUTORIZZATA);
                    }
                    else
                    {    
                        if (Util.isCodiceFiscalePF(tbcampo.getValoreInput()))
                        {
                            Integer esito_controllo = SchipafiUtilityCheckDati.INSTACE.ESITO_CONTROLLO_OK;
                            esito_controllo = SchipafiUtilityCheckDati.INSTACE.checkControlDigitLetterCodiceFiscale(tbcampo.getValoreInput());
                            if(esito_controllo.equals(SchipafiUtilityCheckDati.INSTACE.ESITO_CONTROLLO_OK))
                            {
                               this.sezValorizzata = true;
                               esitoValidazione = true;
                               tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO);  
                               tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO).getDescrizione()); 
                               ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                               LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                               LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                           
                            }
                            else
                            {
                               tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.SINTATTICAMENTE_ERRATO);  
                               tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                               ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                               LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                               LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                            }
                        }
                        else
                        {
                               tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);  
                               tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                               ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                               LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                               LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);
                        }
                    }    
                }    
            } 
            // ESISTE IN VITA
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDatiIdentificativi.ESISTE_IN_VITA))
            {
                  
                    if ((!tbcampo.getValoreInput().isEmpty()) && (!Util.isAlfabetico(tbcampo.getValoreInput()) || tbcampo.getValoreInput().length()>40))
                    {
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);

                    }
                    else
                    {
                        if (!tbcampo.getValoreInput().isEmpty())
                        {    
                           esitoValidazione = true;
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                        }
                        else
                        {
                            esitoValidazione = true;
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NULLO);  
                            tbcampo.setValoreOutput("");  
                        }    
                    }    
            }
            
            // NOME
            
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDatiIdentificativi.NOME))
            {
               if ((tbcampo.getFlagobbligosistema() > 0 ) && ((null==tbcampo.getValoreInput()) || (tbcampo.getValoreInput().isEmpty())))
                {
                    tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);
                    tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                    tbcampo.setObbligatorioEdit(true);
                    LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                    LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_DATI_OBBLIGATORI_MANCANTI);

                }
                else
                {    
                    if (null==tbcampo.getValoreInput())
                    {
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SERVIZI_NON_AUTORIZZATO);
                            
                    }
                    if(!Util.isAlfabetico(tbcampo.getValoreInput()) || tbcampo.getValoreInput().length()>40)
                    {
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);
                            
                    }
                    else
                    {
                           this.sezValorizzata = true;
                           esitoValidazione = true;
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                    }    
                }     
            }
            
            // COGNOME
            
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDatiIdentificativi.COGNOME))
            {
                if ((tbcampo.getFlagobbligosistema() > 0 ) && ((null==tbcampo.getValoreInput()) || (tbcampo.getValoreInput().isEmpty())))
                {
                    tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);
                    tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                    tbcampo.setObbligatorioEdit(true);
                    LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                    LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_DATI_OBBLIGATORI_MANCANTI);
                    
                }
                else
                {    
                    if (null==tbcampo.getValoreInput())
                    {
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SERVIZI_NON_AUTORIZZATO);
                            
                    }
                    if(!Util.isAlfabetico(tbcampo.getValoreInput()) || tbcampo.getValoreInput().length()>40)
                    {
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione());
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);                            
                    }
                    else
                    {
                           this.sezValorizzata = true;
                           esitoValidazione = true;
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                           
                    }                     
                }                 
            }
            
            //DATA DI NASCITA
            
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDatiIdentificativi.DATA_DI_NASCITA))
            {
               if ((tbcampo.getFlagobbligosistema() > 0 ) && ((null==tbcampo.getValoreInput()) || (tbcampo.getValoreInput().isEmpty())))
                {
                    tbcampo.setValoreInputData(null);
                    tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);
                    tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                    tbcampo.setObbligatorioEdit(true);
                    LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                    LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_DATI_OBBLIGATORI_MANCANTI);
                }
                else
                {    
                    if (null==tbcampo.getValoreInput())
                    {
                            tbcampo.setValoreInputData(null);
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SERVIZI_NON_AUTORIZZATO);
                    }
                    if(!Util.isDate(tbcampo.getValoreInput()))
                    {
                            tbcampo.setValoreInputData(null);
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);
                            
                    }
                    else
                    {
                           this.sezValorizzata = true;
                           esitoValidazione = true;
                           tbcampo.setValoreInputData(Util.convertDataStringToDate(tbcampo.getValoreInput()));
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                    }    
                }     
            }
            
            //SESSO
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDatiIdentificativi.SESSO))
            {
               if ((tbcampo.getFlagobbligosistema() > 0 ) && ((null==tbcampo.getValoreInput()) || (tbcampo.getValoreInput().isEmpty())))
                {
                    tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);
                    tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                    tbcampo.setObbligatorioEdit(true);
                    LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                    LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_DATI_OBBLIGATORI_MANCANTI);
                    
                }
                else
                {    
                    if (null==tbcampo.getValoreInput())
                    {
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SERVIZI_NON_AUTORIZZATO);
                            
                    }                    
                    else
                    {
                           this.sezValorizzata = true;
                           esitoValidazione = true;
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                    }    
                }     
            }
            //STATO DI NASCITA
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDatiIdentificativi.STATO_DI_NASCITA))
            {
                    // A Front End si richiede per default black se italia altrimenti si valorizza.
                    // A Front End arriva in forma di combo precaricata la lista degli stati.
                    this.sezValorizzata = true;
                    esitoValidazione = true;
                    tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO);  
                    tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO).getDescrizione()); 
                    ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                    LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                    LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
            }
            //PROVINCIA DI NASCITA
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDatiIdentificativi.PROVINCIA_DI_NASCITA))
            {
               if ((tbcampo.getFlagobbligosistema() > 0 ) && ((null==tbcampo.getValoreInput()) || (tbcampo.getValoreInput().isEmpty())))
                {
                    tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);
                    tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                    tbcampo.setObbligatorioEdit(true);
                    LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                    LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_DATI_OBBLIGATORI_MANCANTI);
                    
                }
                else
                {    
                    if (null==tbcampo.getValoreInput())
                    {
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SERVIZI_NON_AUTORIZZATO);
                            
                    }
                    else
                    {
                           this.sezValorizzata = true;
                           esitoValidazione = true;
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                    }    
                }     
            }
            
            //CITTA DI NASCITA
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDatiIdentificativi.CITTA_DI_NASCITA))
            {
               if ((tbcampo.getFlagobbligosistema() > 0 ) && ((null==tbcampo.getValoreInput()) || (tbcampo.getValoreInput().isEmpty())))
                {
                    tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);
                    tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                    tbcampo.setObbligatorioEdit(true);
                    LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                    LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_DATI_OBBLIGATORI_MANCANTI);
                    
                }
                else
                {    
                    if (null==tbcampo.getValoreInput())
                    {
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SERVIZI_NON_AUTORIZZATO);
                            
                    }
                    else
                    {
                           this.sezValorizzata = true;
                           esitoValidazione = true;
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDatiIdentificativi.POSITIVO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                    }    
                }     
            }
            return esitoValidazione;
        }
}
