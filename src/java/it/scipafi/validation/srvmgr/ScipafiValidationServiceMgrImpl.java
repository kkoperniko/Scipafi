/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.srvmgr;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.sezione.ScipafiElencoBancheDati;
import it.scipafi.validation.sezione.ValidatoreTbCampoRiscontro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author gcerbone
 */
public class ScipafiValidationServiceMgrImpl implements ScipafiValidationServiceMgr {
    
    
    private boolean esito_AGENZIA_ENTRATE = false;
    private boolean esito_CEN_NAPOLI = false;
    private boolean esito_MIN_TRASPORTI = false;
    private boolean esito_INPS = false;
    private boolean esito_INPDAP = false;
    private boolean esito_INAIL = false;
    private boolean esito_CEN_INTERFORCE = false;
    private boolean esito_RAGIONERIA_DELLO_STATO = false;
    
    
    

    @Override
    public List<TbCampoRiscontro> validazioneTbCampiRiscontro(List<TbCampoRiscontro> listTbCampiRiscontro,List<TbScipafiTipoEsitoCampo> listTbScipafiTipoEsitocampo) {
        List<TbCampoRiscontro> result =  new ArrayList<TbCampoRiscontro>();
        HashMap<Integer,List<TbCampoRiscontro>> listTbCampiBancheDati = new HashMap<Integer, List<TbCampoRiscontro>>();
        List<TbCampoRiscontro> listTbCampoRiscontroBancaDati = new ArrayList<TbCampoRiscontro>();
        List<TbCampoRiscontro> listaCampi_AGENZIE_ENTRATE = new ArrayList<TbCampoRiscontro>();
        List<TbCampoRiscontro> listaCampi_CEN_NAPOLI = new ArrayList<TbCampoRiscontro>();
        List<TbCampoRiscontro> listaCampi_MIN_TRASPORTI = new ArrayList<TbCampoRiscontro>();
        List<TbCampoRiscontro> listaCampi_INPS = new ArrayList<TbCampoRiscontro>();
        List<TbCampoRiscontro> listaCampi_INPDAP = new ArrayList<TbCampoRiscontro>();
        List<TbCampoRiscontro> listaCampi_INAIL = new ArrayList<TbCampoRiscontro>();
        List<TbCampoRiscontro> listaCampi_CEN_INTERFORCE = new ArrayList<TbCampoRiscontro>();
        List<TbCampoRiscontro> listaCampi_RAGIONERIA_DELLO_STATO = new ArrayList<TbCampoRiscontro>();
        for (TbCampoRiscontro tbRiscontro : listTbCampiRiscontro)
        {
            if (tbRiscontro.getBancadatiid().equals(ScipafiElencoBancheDati.AGENZIE_ENTRATE))
            {
                listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                if (null!= listTbCampiBancheDati.get(tbRiscontro.getBancadatiid()))
                    listaCampi_AGENZIE_ENTRATE = listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                listaCampi_AGENZIE_ENTRATE.add(tbRiscontro); 
            }
            if (tbRiscontro.getBancadatiid().equals(ScipafiElencoBancheDati.CEN_NAPOLI))
            {
                listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                if (null!= listTbCampiBancheDati.get(tbRiscontro.getBancadatiid()))
                    listaCampi_CEN_NAPOLI = listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                listaCampi_CEN_NAPOLI.add(tbRiscontro); 
            }     
            if (tbRiscontro.getBancadatiid().equals(ScipafiElencoBancheDati.MIN_TRASPORTI))
            {
                listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                if (null!= listTbCampiBancheDati.get(tbRiscontro.getBancadatiid()))
                    listaCampi_MIN_TRASPORTI = listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                listaCampi_MIN_TRASPORTI.add(tbRiscontro); 
            }  
            if (tbRiscontro.getBancadatiid().equals(ScipafiElencoBancheDati.INPS))
            {
                listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                if (null!= listTbCampiBancheDati.get(tbRiscontro.getBancadatiid()))
                    listaCampi_INPS = listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                listaCampi_INPS.add(tbRiscontro);  
            }  
            if (tbRiscontro.getBancadatiid().equals(ScipafiElencoBancheDati.INPDAP))
            {
                listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                if (null!= listTbCampiBancheDati.get(tbRiscontro.getBancadatiid()))
                    listaCampi_INPDAP = listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                listaCampi_INPDAP.add(tbRiscontro); 
            }  
            if (tbRiscontro.getBancadatiid().equals(ScipafiElencoBancheDati.INAIL))
            {
                listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                if (null!= listTbCampiBancheDati.get(tbRiscontro.getBancadatiid()))
                    listaCampi_INAIL = listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                listaCampi_INAIL.add(tbRiscontro); 
            }  
            if (tbRiscontro.getBancadatiid().equals(ScipafiElencoBancheDati.CEN_INTERFORCE))
            {
                listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                if (null!= listTbCampiBancheDati.get(tbRiscontro.getBancadatiid()))
                    listaCampi_CEN_INTERFORCE = listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                listaCampi_CEN_INTERFORCE.add(tbRiscontro);  
            }  
            if (tbRiscontro.getBancadatiid().equals(ScipafiElencoBancheDati.RAGIONERIA_DELLO_STATO))
            {
                listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                if (null!= listTbCampiBancheDati.get(tbRiscontro.getBancadatiid()))
                    listaCampi_RAGIONERIA_DELLO_STATO = listTbCampiBancheDati.get(tbRiscontro.getBancadatiid());
                listaCampi_RAGIONERIA_DELLO_STATO.add(tbRiscontro); 
            }                          
        }    
        listTbCampiBancheDati.put(ScipafiElencoBancheDati.AGENZIE_ENTRATE, listaCampi_AGENZIE_ENTRATE);
        listTbCampiBancheDati.put(ScipafiElencoBancheDati.CEN_NAPOLI, listaCampi_CEN_NAPOLI);
        listTbCampiBancheDati.put(ScipafiElencoBancheDati.MIN_TRASPORTI, listaCampi_MIN_TRASPORTI);
        listTbCampiBancheDati.put(ScipafiElencoBancheDati.INPS, listaCampi_INPS);
        listTbCampiBancheDati.put(ScipafiElencoBancheDati.INPDAP, listaCampi_INPDAP);
        listTbCampiBancheDati.put(ScipafiElencoBancheDati.INAIL, listaCampi_INAIL);
        listTbCampiBancheDati.put(ScipafiElencoBancheDati.CEN_INTERFORCE, listaCampi_CEN_INTERFORCE);
        listTbCampiBancheDati.put(ScipafiElencoBancheDati.RAGIONERIA_DELLO_STATO, listaCampi_RAGIONERIA_DELLO_STATO);
        
        ///  Inizio Fase e chiamata controllo validazione
        List<TbCampoRiscontro> listaCampi = null;
        if (null==ValidatoreTbCampoRiscontro.INSTANCE.getListTbScipafiTipoEsitoCampo())
            ValidatoreTbCampoRiscontro.INSTANCE.setListTbScipafiTipoEsitoCampo(listTbScipafiTipoEsitocampo);
        listaCampi = listTbCampiBancheDati.get(ScipafiElencoBancheDati.AGENZIE_ENTRATE);
        
        listaCampi = ValidatoreTbCampoRiscontro.INSTANCE.ValidaSezione_BancaDati_AGENZIE_ENTRATE(listaCampi);
        this.esito_AGENZIA_ENTRATE = ValidatoreTbCampoRiscontro.INSTANCE. isEsito_AGENZIA_ENTRATE();
        result.addAll(listaCampi);
        
        listaCampi = listTbCampiBancheDati.get(ScipafiElencoBancheDati.CEN_NAPOLI);
        listaCampi = ValidatoreTbCampoRiscontro.INSTANCE.ValidaSezione_BancaDati_CEN_NAPOLI(listaCampi);
        result.addAll(listaCampi);
        
        listaCampi = listTbCampiBancheDati.get(ScipafiElencoBancheDati.MIN_TRASPORTI);
        listaCampi = ValidatoreTbCampoRiscontro.INSTANCE.ValidaSezione_BancaDati_MIN_TRASPORTI(listaCampi);
        result.addAll(listaCampi);
        
        listaCampi = listTbCampiBancheDati.get(ScipafiElencoBancheDati.INPS);
        listaCampi = ValidatoreTbCampoRiscontro.INSTANCE.ValidaSezione_BancaDati_INPS(listaCampi);
        result.addAll(listaCampi);
        
        listaCampi = listTbCampiBancheDati.get(ScipafiElencoBancheDati.INPDAP);
        listaCampi = ValidatoreTbCampoRiscontro.INSTANCE.ValidaSezione_BancaDati_INPDAP(listaCampi);
        this.esito_INPDAP = ValidatoreTbCampoRiscontro.INSTANCE.isEsito_INPDAP();
        result.addAll(listaCampi);
        
        listaCampi = listTbCampiBancheDati.get(ScipafiElencoBancheDati.INAIL);
        listaCampi = ValidatoreTbCampoRiscontro.INSTANCE.ValidaSezione_BancaDati_INAIL(listaCampi);
        this.esito_INAIL = ValidatoreTbCampoRiscontro.INSTANCE.isEsito_INAIL();
        result.addAll(listaCampi);        
        
        listaCampi = listTbCampiBancheDati.get(ScipafiElencoBancheDati.CEN_INTERFORCE);
        listaCampi = ValidatoreTbCampoRiscontro.INSTANCE.ValidaSezione_BancaDati_CEN_INTERFORCE(listaCampi);
        this.esito_CEN_INTERFORCE = ValidatoreTbCampoRiscontro.INSTANCE.isEsito_CEN_INTERFORCE();
        result.addAll(listaCampi);
        
        listaCampi = listTbCampiBancheDati.get(ScipafiElencoBancheDati.RAGIONERIA_DELLO_STATO);
        listaCampi = ValidatoreTbCampoRiscontro.INSTANCE.ValidaSezione_BancaDati_RAGIONERIA_DELLO_STATO(listaCampi);
        this.esito_RAGIONERIA_DELLO_STATO = ValidatoreTbCampoRiscontro.INSTANCE.isEsito_RAGIONERIA_DELLO_STATO();
        result.addAll(listaCampi);
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
