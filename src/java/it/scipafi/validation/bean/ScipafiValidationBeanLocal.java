/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.bean;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gcerbone
 */
@Local
public interface ScipafiValidationBeanLocal {
    
    public List<TbCampoRiscontro> validazioneTbCampiRiscontro(List<TbCampoRiscontro> listTbCampiRiscontro,List<TbScipafiTipoEsitoCampo> listTbScipafiTipoEsitocampo);
    
}
