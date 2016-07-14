/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.bancadati;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.sezione.ValidatoreDATI_IDENTIFICATIVI;
import it.scipafi.validation.sezione.ValidatoreDOMICILIO_FISCALE;
import it.scipafi.validation.sezione.ValidatoreTbCampoRiscontro;
import it.scipafi.validation.mapping.ScipafiElencoCampiRiscontroDatiIdentificativi;
import it.scipafi.validation.sezione.ValidatoreCARTA_DI_IDENTITA;
import it.scipafi.validation.sezione.ValidatoreDICHIARAZIONE_DEI_REDDITI;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class ValidatoreAGENZIA_DELLE_ENTRATE {
    
    private static final Logger log = Logger.getLogger(ValidatoreAGENZIA_DELLE_ENTRATE.class.toString());
    private boolean esitoValidazione = false;
    public static final ValidatoreAGENZIA_DELLE_ENTRATE INSTANCE = new ValidatoreAGENZIA_DELLE_ENTRATE();
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
    
    public List<TbCampoRiscontro> ValidaSezione_BancaDati_AGENZIE_ENTRATE(List<TbCampoRiscontro> listTbCampoRiscontro)
    {
        this.esitoValidazione = false;
        Boolean checkValidazioni = null;
        ValidatoreDATI_IDENTIFICATIVI.INSTANCE.setListEsitiCampo(listEsitiCampo);
        ValidatoreCARTA_DI_IDENTITA.INSTANCE.setListEsitiCampo(listEsitiCampo);
        ValidatoreDOMICILIO_FISCALE.INSTANCE.setListEsitiCampo(listEsitiCampo);
        ValidatoreDICHIARAZIONE_DEI_REDDITI.INSTANCE.setListEsitiCampo(listEsitiCampo);
        for (TbCampoRiscontro tbcampo :listTbCampoRiscontro)
        {
            tbcampo.setValoreSemaforo(ScipafiElencoCampiRiscontroDatiIdentificativi.NULLO);
            tbcampo.setObbligatorioEdit(false);
            //System.out.println(tbcampo.getCodice());
            
            //  DATI IDENTIFICATIVI
            
            boolean esitoValidazione = ValidatoreDATI_IDENTIFICATIVI.INSTANCE.controllaSezioneCampiDatiIdentificativi(tbcampo);
            if (null==checkValidazioni)
                checkValidazioni = esitoValidazione;
            else
                if (checkValidazioni)
                    checkValidazioni = esitoValidazione;


            // DATI DOMICILIO FISCALE
            
            esitoValidazione = ValidatoreDOMICILIO_FISCALE.INSTANCE.controllaSezioneCampiDomicilioFiscale(tbcampo);
            if (null==checkValidazioni)
                checkValidazioni = esitoValidazione;
            else
                if (checkValidazioni)
                    checkValidazioni = esitoValidazione;
            
            
            // DATI CARTA IDENTITA
            
            esitoValidazione = ValidatoreCARTA_DI_IDENTITA.INSTANCE.controllaSezioneCampiCartaIdentita(tbcampo);
            if (null==checkValidazioni)
                checkValidazioni = esitoValidazione;
            else
                if (checkValidazioni)
                    checkValidazioni = esitoValidazione;
            
            //  DICHIARAZIONE DEI REDDITI
            
            esitoValidazione = ValidatoreDICHIARAZIONE_DEI_REDDITI.INSTANCE.controllaSezioneCampiDatiDichiarazioneRedditi(tbcampo);
            if (null==checkValidazioni)
                checkValidazioni = esitoValidazione;
            else
                if (checkValidazioni)
                    checkValidazioni = esitoValidazione;
            
        }   
        this.esitoValidazione = checkValidazioni;
        return listTbCampoRiscontro;
    } 

}
