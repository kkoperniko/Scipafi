/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.sezione;

import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class ScipafiTipoSchedaCampi {
    
     private static final Logger log = Logger.getLogger(ScipafiTipoSchedaCampi.class.toString());
    
    
    public static final ScipafiTipoSchedaCampi INSTANCE = new ScipafiTipoSchedaCampi();
    private Integer ESITO_SCHEDA_1 = 0;
    private Integer ESITO_SCHEDA_2 = 0;
    private Integer ESITO_SCHEDA_3 = 0;
    
    private ScipafiTipoSchedaCampi() {
    }
    
    public void setESITO_SCHEDA(Integer idScheda,Integer esito){
        
        if (idScheda.equals(1))
            this.ESITO_SCHEDA_1 = esito;
        if (idScheda.equals(2))
            this.ESITO_SCHEDA_2 = esito;
        if (idScheda.equals(3))
            this.ESITO_SCHEDA_3 = esito;
    }
    
    public Integer getESITO_SCHEDA(Integer idScheda){
        Integer result = 0;
        if (idScheda.equals(1))
            result = this.ESITO_SCHEDA_1;
        if (idScheda.equals(2))
            result = this.ESITO_SCHEDA_2;
        if (idScheda.equals(3))
            result = this.ESITO_SCHEDA_3;
        return result;
    }
}
