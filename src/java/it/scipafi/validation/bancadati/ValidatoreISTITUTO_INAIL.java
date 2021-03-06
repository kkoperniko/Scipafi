/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.bancadati;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.mapping.ScipafiElencoCampiRiscontroINAIL;
import it.scipafi.validation.sezione.ValidatoreINAIL;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class ValidatoreISTITUTO_INAIL {
    
    private static final Logger log = Logger.getLogger(ValidatoreISTITUTO_INAIL.class.toString());
    private boolean esitoValidazione = false;
    public static final ValidatoreISTITUTO_INAIL INSTANCE = new ValidatoreISTITUTO_INAIL();
    private List<TbScipafiTipoEsitoCampo> listEsitiCampo;

    public boolean isEsitoValidazione() {
        return esitoValidazione;
    }

    public void setEsitoValidazione(boolean esitoValidazione) {
        this.esitoValidazione = esitoValidazione;
    }

    public List<TbScipafiTipoEsitoCampo> getListEsitiCampo() {
        return listEsitiCampo;
    }

    public void setListEsitiCampo(List<TbScipafiTipoEsitoCampo> listEsitiCampo) {
        this.listEsitiCampo = listEsitiCampo;
    }
    
    public List<TbCampoRiscontro> ValidaSezione_BancaDati_INAIL(List<TbCampoRiscontro> listTbCampoRiscontro)
    {
        this.esitoValidazione = false;
        Boolean checkValidazioni = null;
        ValidatoreINAIL.INSTANCE.setListEsitiCampo(listEsitiCampo);
        for (TbCampoRiscontro tbcampo :listTbCampoRiscontro)
        {
            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroINAIL.NULLO);
            tbcampo.setObbligatorioEdit(false);
            
            boolean esitoValidazioneCampi = ValidatoreINAIL.INSTANCE.controllaSezioneCampiINAIL(tbcampo);
            if (null==checkValidazioni)
                checkValidazioni = esitoValidazioneCampi;
            else
                if (checkValidazioni)
                    checkValidazioni = esitoValidazioneCampi;
        }    
        this.esitoValidazione = checkValidazioni;
        return listTbCampoRiscontro;
        
    }
    
}
