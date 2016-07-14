/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.sezione;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.mapping.ScipafiElencoCampiRiscontroINAIL;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class ValidatoreINAIL {
    
    private static final Logger log = Logger.getLogger(ValidatoreINAIL.class.toString());
    public static final ValidatoreINAIL INSTANCE = new ValidatoreINAIL();
    private List<TbScipafiTipoEsitoCampo> listEsitiCampo;
    public boolean sezValorizzata; 

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
    
    
    public boolean controllaSezioneCampiINAIL(TbCampoRiscontro tbcampo)
    {   
        boolean esitoValidazione = false;
        /*
         * CAMPO_INAIL_NUMERO
         * */        
        if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroINAIL.CAMPO_INAIL_NUMERO))
        {
            if ((null==tbcampo.getValoreInput() || (tbcampo.getValoreInput().isEmpty())))
            {
                esitoValidazione = true;
                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINAIL.NULLO);  
                tbcampo.setValoreOutput("");  
            }
            else
            {
                if(tbcampo.getValoreInput().length() > 9 || !Util.isNumericoIntero(tbcampo.getValoreInput()))
                {
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINAIL.SINTATTICAMENTE_ERRATO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINAIL.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_NON_OBBLIGATORI);
                }
                else
                {
                   this.sezValorizzata = true;
                   esitoValidazione = true;
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINAIL.POSITIVO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINAIL.POSITIVO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);  
                }    
                    
            }
        }
        /*
         * CAMPO_INAIL_CF_DATOE_LAVORO
         * */        
        if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroINAIL.CAMPO_INAIL_CF_DATORE_LAVORO))
        {
            if ((null==tbcampo.getValoreInput() || (tbcampo.getValoreInput().isEmpty())))
            {
                esitoValidazione = true;
                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINAIL.NULLO);  
                tbcampo.setValoreOutput("");  
            }
            else
            {
                if(  !Util.isCodiceFiscalePF_PIVA(tbcampo.getValoreInput()) || !Util.contrMaxLength(tbcampo.getValoreInput(), 16))
                {
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINAIL.SINTATTICAMENTE_ERRATO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINAIL.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_NON_OBBLIGATORI);
                }
                else
                {
                   this.sezValorizzata = true;
                   esitoValidazione = true;
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINAIL.POSITIVO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINAIL.POSITIVO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                      
                }    
            }                
        }
        /*
         * CAMPO_CODICE_CLINTE
         * */        
        if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroINAIL.CAMPO_INAIL_CODICE_CLIENTE))
        {
            if ((null==tbcampo.getValoreInput() || (tbcampo.getValoreInput().isEmpty())))
            {
                esitoValidazione = true;
                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINAIL.NULLO);  
                tbcampo.setValoreOutput("");  
            }
            else
            {
                if(tbcampo.getValoreInput().length() > 9 || !Util.isNumericoIntero(tbcampo.getValoreInput()))
                {
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINAIL.SINTATTICAMENTE_ERRATO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINAIL.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_NON_OBBLIGATORI);
                } 
                else
                {
                   this.sezValorizzata = true;
                   esitoValidazione = true;
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINAIL.POSITIVO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINAIL.POSITIVO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                      
                }
            }                
        }
        /*
         * CAMPO_CODICE_CONTROLLO
         * */
        if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroINAIL.CAMPO_INAIL_CODICE_CONTROLLO))
        {
            if ((null==tbcampo.getValoreInput() || (tbcampo.getValoreInput().isEmpty())))
            {
                esitoValidazione = true;
                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINAIL.NULLO);  
                tbcampo.setValoreOutput("");  
            }
            else
            {
                if(tbcampo.getValoreInput().length() > 2 || !Util.isNumericoIntero(tbcampo.getValoreInput()))
		{
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINAIL.SINTATTICAMENTE_ERRATO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINAIL.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_NON_OBBLIGATORI);
                } 
                else
                {
                   this.sezValorizzata = true;
                   esitoValidazione = true;
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINAIL.POSITIVO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINAIL.POSITIVO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                      
		}
            }                
        }
        return esitoValidazione;
    }
    
    
}
