/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.bean;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import it.scipafi.validation.srvmgr.ScipafiValidationServiceMgr;
import it.scipafi.validation.srvmgr.ScipafiValidationServiceMgrImpl;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author gcerbone
 */
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN) 
@Stateless(name="ScipafiValidationEjb",mappedName="ejb/ScipafiValidationEjb")
public class ScipafiValidationBean implements ScipafiValidationBeanRemote, ScipafiValidationBeanLocal {

    private ScipafiValidationServiceMgr ScipafiValidationServiceMgr = new ScipafiValidationServiceMgrImpl();
    

    @Override
    public List<TbCampoRiscontro> validazioneTbCampiRiscontro(List<TbCampoRiscontro> listTbCampiRiscontro, List<TbScipafiTipoEsitoCampo> listTbScipafiTipoEsitocampo) {
        return ScipafiValidationServiceMgr.validazioneTbCampiRiscontro(listTbCampiRiscontro,listTbScipafiTipoEsitocampo);
    }
}
