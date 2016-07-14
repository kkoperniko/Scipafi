/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.sezione;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.mapping.ScipafiElencoCampiRiscontroDomicilioFiscale;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class ValidatoreDOMICILIO_FISCALE {
    
    private static final Logger log = Logger.getLogger(ValidatoreDOMICILIO_FISCALE.class.toString());
    public static final ValidatoreDOMICILIO_FISCALE INSTANCE = new ValidatoreDOMICILIO_FISCALE();
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
    
    
    public  boolean controllaSezioneCampiDomicilioFiscale(TbCampoRiscontro tbcampo)
    {
        //  SEZIONE 2 DOMICILIO FISCALE
            boolean esitoValidazione = false;
            // VIA DOMICILIO FISCALE
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDomicilioFiscale.VIA_DOMICILIO_FISCALE))
            {
                  
                    if (!tbcampo.getValoreInput().isEmpty())
                    {
                        if(!Util.isAlfabetico(tbcampo.getValoreInput()) || tbcampo.getValoreInput().length()>40)
                        {
                                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDomicilioFiscale.NEGATIVO);  
                                tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDomicilioFiscale.NEGATIVO).getDescrizione()); 
                                LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                                LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);

                        }
                        else
                        {
                               esitoValidazione = true;
                               tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDomicilioFiscale.POSITIVO);  
                               tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDomicilioFiscale.POSITIVO).getDescrizione()); 
                               ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                               LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                               LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                        }
                    }
                    else
                        tbcampo.setValoreOutput(null);
            }
            
            
            //PROVINCIA DOMICILIO FISCALE
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDomicilioFiscale.PROVINCIA_DOMICILIO_FISCALE))
            {
                  
                    if (!tbcampo.getValoreInput().isEmpty())
                    {
                        if(!Util.isAlfabetico(tbcampo.getValoreInput()) || tbcampo.getValoreInput().length()>40)
                        {
                                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDomicilioFiscale.NEGATIVO);  
                                tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDomicilioFiscale.NEGATIVO).getDescrizione()); 
                                LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                                LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);

                        }
                        else
                        {
                               esitoValidazione = true;
                               tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDomicilioFiscale.POSITIVO);  
                               tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDomicilioFiscale.POSITIVO).getDescrizione()); 
                               ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                               LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                               LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                        } 
                    }
                    else
                        tbcampo.setValoreOutput(null);
    
            }
            
            // CITTA DOMICILIO FISCALE
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDomicilioFiscale.CITTA_DOMICILIO_FISCALE))
            {
                  
                    if (!tbcampo.getValoreInput().isEmpty())
                    {
                    
                        if(!Util.isAlfabetico(tbcampo.getValoreInput()) || tbcampo.getValoreInput().length()>40)
                        {
                                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDomicilioFiscale.NEGATIVO);  
                                tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDomicilioFiscale.NEGATIVO).getDescrizione()); 
                                LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                                LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);

                        }
                        else
                        {
                               esitoValidazione = true;
                               tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDomicilioFiscale.POSITIVO);  
                               tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDomicilioFiscale.POSITIVO).getDescrizione()); 
                               ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                               LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                               LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                        } 
                    }
                    else
                        tbcampo.setValoreOutput(null);
            }
            
            // CAP DOMICILIO FISCALE
            if (tbcampo.getCodice().equals(ScipafiElencoCampiRiscontroDomicilioFiscale.CAP_DOMICILIO_FISCALE))
            {
                  
                    if (!tbcampo.getValoreInput().isEmpty())
                    {
                    
                        if(!Util.isNumericoIntero(tbcampo.getValoreInput()) || (tbcampo.getValoreInput().length() != 5))
                        {
                                tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDomicilioFiscale.NEGATIVO);  
                                tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDomicilioFiscale.NEGATIVO).getDescrizione()); 
                                LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA);
                                LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_NEGATIVO);

                        }
                        else
                        {
                               esitoValidazione = true;
                               tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDomicilioFiscale.POSITIVO);  
                               tbcampo.setValoreOutput(getTbScipafiTipoEsitoCampoDaLista(ScipafiElencoCampiRiscontroDomicilioFiscale.POSITIVO).getDescrizione()); 
                               ScipafiTipoSchedaCampi.INSTANCE.setESITO_SCHEDA(tbcampo.getSchedaid(), ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);
                               LoggingEsitiBancaDati.INSTANCE.gestioneEsitoBancheDati(tbcampo.getBancadatiid(), tbcampo, ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_BANCA_DATI_POSITIVO);
                               LoggingEsitiBancaDati.INSTANCE.gestisciEsitoGlobale(ScipafiElencoEsitiBancaDatiGlobali.COD_ESITO_GLOB_POSITIVO);                        
                        }
                    }
                    else
                        tbcampo.setValoreOutput(null);
            }
        return esitoValidazione;
    }            
}
