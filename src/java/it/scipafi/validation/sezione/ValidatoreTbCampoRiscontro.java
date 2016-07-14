/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.sezione;

import it.scipafi.validation.bancadati.ValidatoreAGENZIA_DELLE_ENTRATE;
import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.bancadati.ValidatoreCEN_INTERFORCE;
import it.scipafi.validation.bancadati.ValidatoreISTITUTO_INAIL;
import it.scipafi.validation.bancadati.ValidatoreISTITUTO_INPDAP;
import it.scipafi.validation.bancadati.ValidatoreRAGIONERIA_DELLO_STATO;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author gcerbone
 */
public class ValidatoreTbCampoRiscontro {
    
    private static final Logger log = Logger.getLogger(ValidatoreTbCampoRiscontro.class.toString());
    
    
    public static final ValidatoreTbCampoRiscontro INSTANCE = new ValidatoreTbCampoRiscontro();
    private List<TbScipafiTipoEsitoCampo> listTbScipafiTipoEsitoCampo;
    
    private boolean esito_AGENZIA_ENTRATE = false;
    private boolean esito_CEN_NAPOLI = false;
    private boolean esito_MIN_TRASPORTI = false;
    private boolean esito_INPS = false;
    private boolean esito_INPDAP = false;
    private boolean esito_INAIL = false;
    private boolean esito_CEN_INTERFORCE = false;
    private boolean esito_RAGIONERIA_DELLO_STATO = false;
    
    private ValidatoreTbCampoRiscontro() {
    }
    
    
    
    
    public List<TbScipafiTipoEsitoCampo> getListTbScipafiTipoEsitoCampo()
    {
        return this.listTbScipafiTipoEsitoCampo;
    }
    
    public void setListTbScipafiTipoEsitoCampo(List<TbScipafiTipoEsitoCampo> listTbScipafiTipoEsitoCampo)
    {
        this.listTbScipafiTipoEsitoCampo = listTbScipafiTipoEsitoCampo;
    }
    
    public TbScipafiTipoEsitoCampo getTbScipafiTipoEsitoCampoDaLista(Integer valueID)
    {
        TbScipafiTipoEsitoCampo result = new TbScipafiTipoEsitoCampo();
        for (TbScipafiTipoEsitoCampo tbEsitoCampo : listTbScipafiTipoEsitoCampo)
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
    public List<TbCampoRiscontro> ValidaSezione_BancaDati_AGENZIE_ENTRATE(List<TbCampoRiscontro> listTbCampoRiscontro)
    {
        List<TbCampoRiscontro> result = new ArrayList<TbCampoRiscontro>();
        ValidatoreAGENZIA_DELLE_ENTRATE.INSTANCE.setListEsitiCampo(getListTbScipafiTipoEsitoCampo());
        result = ValidatoreAGENZIA_DELLE_ENTRATE.INSTANCE.ValidaSezione_BancaDati_AGENZIE_ENTRATE(listTbCampoRiscontro);
        this.esito_AGENZIA_ENTRATE = ValidatoreAGENZIA_DELLE_ENTRATE.INSTANCE.isEsitoValidazione();
        return result;
        
    }        
   
    public List<TbCampoRiscontro> ValidaSezione_BancaDati_CEN_NAPOLI(List<TbCampoRiscontro> listTbCampoRiscontro)
    {
        return listTbCampoRiscontro;
    }
        
    public List<TbCampoRiscontro> ValidaSezione_BancaDati_MIN_TRASPORTI(List<TbCampoRiscontro> listTbCampoRiscontro)
    {
        return listTbCampoRiscontro;
    }
    
    public List<TbCampoRiscontro> ValidaSezione_BancaDati_INPS(List<TbCampoRiscontro> listTbCampoRiscontro)
    {
        return listTbCampoRiscontro;
    }
    
    public List<TbCampoRiscontro> ValidaSezione_BancaDati_INPDAP(List<TbCampoRiscontro> listTbCampoRiscontro)
    {
        List<TbCampoRiscontro> result = new ArrayList<TbCampoRiscontro>();
        ValidatoreISTITUTO_INPDAP.INSTANCE.setListEsitiCampo(getListTbScipafiTipoEsitoCampo());
        result = ValidatoreISTITUTO_INPDAP.INSTANCE.ValidaSezione_BancaDati_INPDAP(listTbCampoRiscontro);
        this.esito_INPDAP = ValidatoreISTITUTO_INPDAP.INSTANCE.isEsitoValidazione();
        return result;
    }
    
    public List<TbCampoRiscontro> ValidaSezione_BancaDati_INAIL(List<TbCampoRiscontro> listTbCampoRiscontro)
    {
        List<TbCampoRiscontro> result = new ArrayList<TbCampoRiscontro>();
        ValidatoreISTITUTO_INAIL.INSTANCE.setListEsitiCampo(getListTbScipafiTipoEsitoCampo());
        result = ValidatoreISTITUTO_INAIL.INSTANCE.ValidaSezione_BancaDati_INAIL(listTbCampoRiscontro);
        this.esito_INAIL = ValidatoreISTITUTO_INAIL.INSTANCE.isEsitoValidazione();
        return result;
    }
    public List<TbCampoRiscontro> ValidaSezione_BancaDati_CEN_INTERFORCE(List<TbCampoRiscontro> listTbCampoRiscontro)
    {
        List<TbCampoRiscontro> result = new ArrayList<TbCampoRiscontro>();
        ValidatoreCEN_INTERFORCE.INSTANCE.setListEsitiCampo(getListTbScipafiTipoEsitoCampo());
        result = ValidatoreCEN_INTERFORCE.INSTANCE.ValidaSezione_BancaDati_CEN_INTERFORCE(listTbCampoRiscontro);
        this.esito_CEN_INTERFORCE = ValidatoreCEN_INTERFORCE.INSTANCE.isEsitoValidazione();
        return result;
    }
    public List<TbCampoRiscontro> ValidaSezione_BancaDati_RAGIONERIA_DELLO_STATO(List<TbCampoRiscontro> listTbCampoRiscontro)
    {
        List<TbCampoRiscontro> result = new ArrayList<TbCampoRiscontro>();
        ValidatoreRAGIONERIA_DELLO_STATO.INSTANCE.setListEsitiCampo(getListTbScipafiTipoEsitoCampo());
        result = ValidatoreRAGIONERIA_DELLO_STATO.INSTANCE.ValidaSezione_BancaDati_RAGIONERIA_DELLO_STATO(listTbCampoRiscontro);
        this.esito_RAGIONERIA_DELLO_STATO = ValidatoreRAGIONERIA_DELLO_STATO.INSTANCE.isEsitoValidazione();
        return result;
    }

    /**
     * @return the esito_AGENZIA_ENTRATE
     */
    public boolean isEsito_AGENZIA_ENTRATE() {
        return esito_AGENZIA_ENTRATE;
    }

    /**
     * @return the esito_CEN_NAPOLI
     */
    public boolean isEsito_CEN_NAPOLI() {
        return esito_CEN_NAPOLI;
    }

    /**
     * @return the esito_MIN_TRASPORTI
     */
    public boolean isEsito_MIN_TRASPORTI() {
        return esito_MIN_TRASPORTI;
    }

    /**
     * @return the esito_INPS
     */
    public boolean isEsito_INPS() {
        return esito_INPS;
    }

    /**
     * @return the esito_INPDAP
     */
    public boolean isEsito_INPDAP() {
        return esito_INPDAP;
    }

    /**
     * @return the esito_CEN_INTERFORCE
     */
    public boolean isEsito_CEN_INTERFORCE() {
        return esito_CEN_INTERFORCE;
    }

    /**
     * @return the esito_RAGIONERIA_DELLO_STATO
     */
    public boolean isEsito_RAGIONERIA_DELLO_STATO() {
        return esito_RAGIONERIA_DELLO_STATO;
    }

    /**
     * @return the esito_INAIL
     */
    public boolean isEsito_INAIL() {
        return esito_INAIL;
    }
    
}
