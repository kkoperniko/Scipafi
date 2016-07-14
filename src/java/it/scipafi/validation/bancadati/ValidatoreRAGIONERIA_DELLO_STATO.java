/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.bancadati;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.sezione.ValidatoreTESSERA_SANITARIA;
import it.scipafi.validation.sezione.ValidatoreTbCampoRiscontro;
import it.scipafi.validation.mapping.ScipafiElencoCampiRiscontroDatiIdentificativi;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class ValidatoreRAGIONERIA_DELLO_STATO {
    
    private static final Logger log = Logger.getLogger(ValidatoreRAGIONERIA_DELLO_STATO.class.toString());
    private boolean esitoValidazione = false;
    public static final ValidatoreRAGIONERIA_DELLO_STATO INSTANCE = new ValidatoreRAGIONERIA_DELLO_STATO();
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

    public List<TbCampoRiscontro> ValidaSezione_BancaDati_RAGIONERIA_DELLO_STATO(List<TbCampoRiscontro> listTbCampoRiscontro)
    {
        this.esitoValidazione = false;
        Boolean checkValidazioni = null;
        for (TbCampoRiscontro tbcampo :listTbCampoRiscontro)
        {
            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NULLO);
            tbcampo.setObbligatorioEdit(false);


            ValidatoreTESSERA_SANITARIA.INSTANCE.setListEsitiCampo(listEsitiCampo);
            boolean esitoValidazioneCampi = ValidatoreTESSERA_SANITARIA.INSTANCE.controllaSezioneCampiDatiTesseraSanitaria(tbcampo,listTbCampoRiscontro);
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
