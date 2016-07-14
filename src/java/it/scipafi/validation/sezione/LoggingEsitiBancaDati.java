/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.sezione;

import it.rdbms.model.TbCampoRiscontro;
import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class LoggingEsitiBancaDati {
    
    private static final Logger log = Logger.getLogger(LoggingEsitiBancaDati.class.toString());
    
    public static final LoggingEsitiBancaDati INSTANCE = new LoggingEsitiBancaDati();
    
    private LoggingEsitiBancaDati() {
        
        
    }
    
    public void gestisciEsitoGlobale(Integer esitoRiscontro)
    {
        
        
    }
    
    public void gestioneEsitoBancheDati(Integer schedaId,TbCampoRiscontro tbCampoRiscontro, Integer esitoGlob)
    {
            
    }
   
}
