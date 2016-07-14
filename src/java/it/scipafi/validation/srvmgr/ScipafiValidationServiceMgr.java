/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.srvmgr;

import it.rdbms.model.TbCampoRiscontro;
import it.rdbms.model.TbScipafiTipoEsitoCampo;
import java.util.List;

/**
 *
 * @author gcerbone
 */
public interface ScipafiValidationServiceMgr {
    
    public List<TbCampoRiscontro> validazioneTbCampiRiscontro(List<TbCampoRiscontro> listTbCampiRiscontro,List<TbScipafiTipoEsitoCampo> listTbScipafiTipoEsitocampo);
    
}
