/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.bancadati;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.mapping.ScipafiElencoCampiRiscontroCartaIdentita;
import it.scipafi.validation.sezione.ValidatoreALTRO_DOCUMENTO;
import it.scipafi.validation.sezione.ValidatoreCARTA_DI_IDENTITA;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class ValidatoreCEN_INTERFORCE {
    
    private static final Logger log = Logger.getLogger(ValidatoreCEN_INTERFORCE.class.toString());
    private boolean esitoValidazione = false;
    public static final ValidatoreCEN_INTERFORCE INSTANCE = new ValidatoreCEN_INTERFORCE();
    private List<TbScipafiTipoEsitoCampo> listEsitiCampo;

    /**
     * @return the esitoValidazione
     */
    public boolean isEsitoValidazione() {
        return esitoValidazione;
    }

    /**
     * @return the listEsitiCampo
     */
    public List<TbScipafiTipoEsitoCampo> getListEsitiCampo() {
        return listEsitiCampo;
    }

    /**
     * @param listEsitiCampo the listEsitiCampo to set
     */
    public void setListEsitiCampo(List<TbScipafiTipoEsitoCampo> listEsitiCampo) {
        this.listEsitiCampo = listEsitiCampo;
    }    
    
    
    public List<TbCampoRiscontro> ValidaSezione_BancaDati_CEN_INTERFORCE(List<TbCampoRiscontro> listTbCampoRiscontro)
    {
        this.esitoValidazione = false;
        Boolean checkValidazioni = null;
        ValidatoreCARTA_DI_IDENTITA.INSTANCE.setListEsitiCampo(listEsitiCampo);
        ValidatoreALTRO_DOCUMENTO.INSTANCE.setListEsitiCampo(listEsitiCampo);
        for (TbCampoRiscontro tbcampo :listTbCampoRiscontro)
        {
            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroCartaIdentita.NULLO);
            tbcampo.setObbligatorioEdit(false);
            
            boolean esitoValidazioneCampi = ValidatoreCARTA_DI_IDENTITA.INSTANCE.controllaSezioneCampiCartaIdentita(tbcampo);
            if (null==checkValidazioni)
                checkValidazioni = esitoValidazioneCampi;
            else
                if (checkValidazioni)
                    checkValidazioni = esitoValidazioneCampi;
            
            
            esitoValidazioneCampi = ValidatoreALTRO_DOCUMENTO.INSTANCE.controllaSezioneCampiAltroDocumento(tbcampo);
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
