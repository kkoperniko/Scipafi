/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.sezione;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoDichiarazioneRedditi;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.cache.liste.TbScipafiListeStatichePrecaricate;
import it.scipafi.validation.mapping.ScipafiElencoCampiRiscontroDichiarazioneRedditi;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class ValidatoreDICHIARAZIONE_DEI_REDDITI {
    
    private static final Logger log = Logger.getLogger(ValidatoreDICHIARAZIONE_DEI_REDDITI.class.toString());
    public static final ValidatoreDICHIARAZIONE_DEI_REDDITI INSTANCE = new ValidatoreDICHIARAZIONE_DEI_REDDITI();
    private List<TbScipafiTipoEsitoCampo> listEsitiCampo;
    public boolean sezValorizzata; 
    public String COD_DB_TIPO_DICHIARAZIONE_UNICO = "1";
    public String COD_DB_TIPO_DICHIARAZIONE_730 = "2";
    public String COD_DB_TIPO_DICHIARAZIONE_CUD = "3";
    public String codTipoDichiarazione = null;

    public List<TbScipafiTipoEsitoCampo> getListEsitiCampo() {
        return listEsitiCampo;
    }

    public void setListEsitiCampo(List<TbScipafiTipoEsitoCampo> listEsitiCampo) {
        this.listEsitiCampo = listEsitiCampo;
    }

    public boolean isSezValorizzata() {
        return sezValorizzata;
    }

    public void setSezValorizzata(boolean sezValorizzata) {
        this.sezValorizzata = sezValorizzata;
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
    
    
    public boolean controllaSezioneCampiDatiDichiarazioneRedditi(TbCampoRiscontro tbcampo)
    {   
        boolean esitoValidazione = false;
        
        /*
         * CAMPO_DRED_ANNO_DICH
         * */
        if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDichiarazioneRedditi.CAMPO_DRED_ANNO_DICH))
        {
                if ((null==tbcampo.getValoreInput() || (tbcampo.getValoreInput().isEmpty())))
                {
                    esitoValidazione = true;
                    tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.NULLO);  
                    tbcampo.setValoreOutput("");  
                }
                else
                {
                        int risultatoControllo = Util.compareAnno(tbcampo.getValoreInput(), Util.getAnnoOdierno());
                        if(!Util.isNumericoIntero(tbcampo.getValoreInput()) || risultatoControllo == -1 || risultatoControllo == 2 )
			{
                               tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.SINTATTICAMENTE_ERRATO);  
                               tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                               ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                               LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                               LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_NON_OBBLIGATORI);
                        }
                        else
                        {
                               this.sezValorizzata = true;
                               esitoValidazione = true;
                               tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.POSITIVO);  
                               tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.POSITIVO).getDescrizione()); 
                               ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                               LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                               LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                           
                        }
                }    
        }  
        
        /*
         * CAMPO_DRED_TIPO_DICH
         * */
        if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDichiarazioneRedditi.CAMPO_DRED_TIPO_DICH))
        {
           
                if ((null==tbcampo.getValoreInput() || (tbcampo.getValoreInput().isEmpty())))
                {
                    esitoValidazione = true;
                    tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.NULLO);  
                    tbcampo.setValoreOutput("");  
                }
                else
                {
                        if (!tbcampo.getValoreInput().isEmpty())
                        {    
                            if(Util.isTipoDichiarazione(tbcampo.getValoreInput()))
                            {
                                String tipologiaIDBD="";
                                for (TbScipafiTipoDichiarazioneRedditi tdx : TbScipafiListeStatichePrecaricate.INSTANCE.getListaTipoDichiarazioneRedditi())
                                if (tdx.getTipodichiarazioneredditiId().equals(new Integer(tbcampo.getValoreInput()))) 
                                {
                                    tipologiaIDBD =  tdx.getCod_bd();
                                    codTipoDichiarazione = tdx.getTipodichiarazioneredditiId().toString();
                                    break;
                                } 
                                if (!tipologiaIDBD.isEmpty())
                                {
                                    this.sezValorizzata = true;
                                    esitoValidazione = true;
                                    tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.POSITIVO);  
                                    tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.POSITIVO).getDescrizione()); 
                                    ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                                    LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                                    LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);   
                                } 
                                else
                                {
                                    tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.NEGATIVO);  
                                    tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.NEGATIVO).getDescrizione()); 
                                    LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                                    LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_BANCA_DATI_NON_AUTORIZZATA);
                                }    
                                                       
                            }
                           else
                            {
                                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.NEGATIVO);  
                                tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.NEGATIVO).getDescrizione()); 
                                LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                                LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_BANCA_DATI_NON_AUTORIZZATA);
                            }           
                        }
                        else
                        {
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.NEGATIVO);  
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.NEGATIVO).getDescrizione()); 
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_BANCA_DATI_NON_AUTORIZZATA);
                        }
                }
        }
        
        /*
         * CAMPO_DRED_REDDITO_COMPLESSIO
         * */
        if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDichiarazioneRedditi.CAMPO_DRED_REDDITO_COMPLESSIO))
        {
            if ((null==tbcampo.getValoreInput() || (tbcampo.getValoreInput().isEmpty()) && Util.isVuoto(codTipoDichiarazione)))
            {
                esitoValidazione = true;
                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.NULLO);  
                tbcampo.setValoreOutput("");  
            }
            else
            {
                if(Util.isVuoto(tbcampo.getValoreInput()) && !Util.isVuoto(codTipoDichiarazione) && Util.isTipoDichiarazione(codTipoDichiarazione) && !COD_DB_TIPO_DICHIARAZIONE_CUD.equals(codTipoDichiarazione))
                {
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.SINTATTICAMENTE_ERRATO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);

                }
                else
                {
                    if(Util.isVuoto(tbcampo.getValoreInput()))
                        tbcampo.setValoreInput("0");
                    if(!Util.isNumericoInteroImporto(tbcampo.getValoreInput()))
                    {
                       this.sezValorizzata = true;
                       tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.POSITIVO);  
                       tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.POSITIVO).getDescrizione()); 
                       ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                       LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                       LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);

                    }else
                    {
                       tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.SINTATTICAMENTE_ERRATO);  
                       tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                       ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                       LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                       LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                    }
                } 
            }    
        }
        
        /*
         * CAMPO_DRED_CF_SOSTITUTIVO
         * */
        if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDichiarazioneRedditi.CAMPO_DRED_CF_SOSTITUTIVO))
        {
           if ((null==tbcampo.getValoreInput() || (tbcampo.getValoreInput().isEmpty()) && Util.isVuoto(codTipoDichiarazione)))
            {
                esitoValidazione = true;
                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.NULLO);  
                tbcampo.setValoreOutput("");  
            }
            else
            {
                if(Util.isVuoto(tbcampo.getValoreInput()) && !Util.isVuoto(codTipoDichiarazione) && Util.isTipoDichiarazione(codTipoDichiarazione) && !COD_DB_TIPO_DICHIARAZIONE_CUD.equals(codTipoDichiarazione))
		{
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.SINTATTICAMENTE_ERRATO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);

                }
                else
                {
                    if(!Util.isCodiceFiscalePF_PIVA(tbcampo.getValoreInput().toUpperCase()) || !Util.contrMaxLength(tbcampo.getValoreInput(), 16))
                    {
                        if(Util.isTipoDichiarazione(codTipoDichiarazione) && COD_DB_TIPO_DICHIARAZIONE_CUD.equals(codTipoDichiarazione))
                        {
                            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.NEGATIVO);
                            tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.NEGATIVO).getDescrizione()); 
                            tbcampo.setObbligatorioEdit(true);
                            LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                            LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_DATI_OBBLIGATORI_MANCANTI);
                        }
                        else
                        {
                           tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.SINTATTICAMENTE_ERRATO);  
                           tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                           ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                           LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                           LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                        }
                    }
                    else
                    {
                       this.sezValorizzata = true;
                       tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.POSITIVO);  
                       tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.POSITIVO).getDescrizione()); 
                       ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                       LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                       LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);

                    }    

                    if(!Util.isNumericoInteroImporto(tbcampo.getValoreInput()))
                    {
                       this.sezValorizzata = true;
                       tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.POSITIVO);  
                       tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.POSITIVO).getDescrizione()); 
                       ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                       LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                       LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);

                    }else
                    {
                       tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDichiarazioneRedditi.SINTATTICAMENTE_ERRATO);  
                       tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDichiarazioneRedditi.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                       ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                       LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                       LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                    }
                }    
            }
        }
        return esitoValidazione;
    }     
}
