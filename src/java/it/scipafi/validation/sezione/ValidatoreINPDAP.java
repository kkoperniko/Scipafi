/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.sezione;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.mapping.ScipafiElencoCampiRiscontroINPDAP;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class ValidatoreINPDAP {
    
    private static final Logger log = Logger.getLogger(ValidatoreINPDAP.class.toString());
    public static final ValidatoreINPDAP INSTANCE = new ValidatoreINPDAP();
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
    
    public boolean controllaSezioneCampiINPDAP(TbCampoRiscontro tbcampo)
    {
        boolean esitoValidazione = false;
        
        if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroINPDAP.CAMPO_INPDAP_CF_DATORE_LAVORO))
        {
            if ((null==tbcampo.getValoreInput() || (tbcampo.getValoreInput().isEmpty())))
            {
                esitoValidazione = true;
                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.NULLO);  
                tbcampo.setValoreOutput("");  
            }
            else
            {
                if(tbcampo.getValoreInput().length() > 9 || !Util.isNumericoIntero(tbcampo.getValoreInput()))
                {
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.SINTATTICAMENTE_ERRATO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINPDAP.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_NON_OBBLIGATORI);
                }
                else
                {
                   this.sezValorizzata = true;
                   esitoValidazione = true;
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.POSITIVO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINPDAP.POSITIVO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);  
                }    
                    
            }
        }
        
        if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroINPDAP.CAMPO_INPDAP_MESE_COMPETENZA))
        {
            if ((null==tbcampo.getValoreInput() || (tbcampo.getValoreInput().isEmpty())))
            {
                esitoValidazione = true;
                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.NULLO);  
                tbcampo.setValoreOutput("");  
            }
            else
            {
                if(tbcampo.getValoreInput().length() > 9 || !Util.isNumericoIntero(tbcampo.getValoreInput()))
                {
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.SINTATTICAMENTE_ERRATO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINPDAP.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_NON_OBBLIGATORI);
                }
                else
                {
                   this.sezValorizzata = true;
                   esitoValidazione = true;
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.POSITIVO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINPDAP.POSITIVO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);  
                }    
                    
            }
        }
        
        if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroINPDAP.CAMPO_INPDAP_MESE_COMPETENZA))
        {
            if ((null==tbcampo.getValoreInput() || (tbcampo.getValoreInput().isEmpty())))
            {
                esitoValidazione = true;
                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.NULLO);  
                tbcampo.setValoreOutput("");  
            }
            else
            {
                if(tbcampo.getValoreInput().length() > 9 || !Util.isNumericoIntero(tbcampo.getValoreInput()))
                {
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.SINTATTICAMENTE_ERRATO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINPDAP.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_NON_OBBLIGATORI);
                }
                else
                {
                   this.sezValorizzata = true;
                   esitoValidazione = true;
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.POSITIVO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINPDAP.POSITIVO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);  
                }    
                    
            }
        }
        
        if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroINPDAP.CAMPO_INPDAP_ANNO_COMPETENZA))
        {
            if ((null==tbcampo.getValoreInput() || (tbcampo.getValoreInput().isEmpty())))
            {
                esitoValidazione = true;
                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.NULLO);  
                tbcampo.setValoreOutput("");  
            }
            else
            {
                if(tbcampo.getValoreInput().length() > 9 || !Util.isNumericoIntero(tbcampo.getValoreInput()))
                {
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.SINTATTICAMENTE_ERRATO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINPDAP.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_NON_OBBLIGATORI);
                }
                else
                {
                   this.sezValorizzata = true;
                   esitoValidazione = true;
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.POSITIVO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINPDAP.POSITIVO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);  
                }    
                    
            }
        }
        if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroINPDAP.CAMPO_INPDAP_IMPONIBILI_CONTRIBUTIVO_MESE))
        {
            if ((null==tbcampo.getValoreInput() || (tbcampo.getValoreInput().isEmpty())))
            {
                esitoValidazione = true;
                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.NULLO);  
                tbcampo.setValoreOutput("");  
            }
            else
            {
                if(tbcampo.getValoreInput().length() > 9 || !Util.isNumericoIntero(tbcampo.getValoreInput()))
                {
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.SINTATTICAMENTE_ERRATO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINPDAP.SINTATTICAMENTE_ERRATO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_ERROR_SINTATTICI_NON_OBBLIGATORI);
                }
                else
                {
                   this.sezValorizzata = true;
                   esitoValidazione = true;
                   tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINPDAP.POSITIVO);  
                   tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroINPDAP.POSITIVO).getDescrizione()); 
                   ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                   LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);  
                }    
                    
            }
        }
        
        return esitoValidazione;
    }
    
}
