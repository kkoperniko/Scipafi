/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scipafi.validation.sezione;

/**
 *
 * @author gcerbone
 */
public interface ScipafiElencoEsitiBancaDatiGlobali {
    
    
    public static Integer COD_ESITO_GLOB_POSITIVO = 1;
    public static Integer COD_ESITO_GLOB_NEGATIVO = 2;
    public static Integer COD_ESITO_GLOB_ERROR_SINTATTICI_NON_OBBLIGATORI = 2;
    public static Integer COD_ESITO_GLOB_MANCATA_RESPONSE_BANCA_DATI_UNA_O_PIU =  3;
    public static Integer COD_ESITO_GLOB_ERROR_SINTATTICI_NON_OBBLIGATORI_IN_INPUT = 4;
    public static Integer COD_ESITO_GLOB_ERROR_BANCHE_DATI_NON_RESPONSE = 5;
    public static Integer COD_ESITO_GLOB_ERROR_SINTATTICI_OBBLIGATORI_IN_INPUT = 6;
    public static Integer COD_ESITO_GLOB_ERROR_DATI_OBBLIGATORI_MANCANTI = 7;
    public static Integer COD_ESITO_GLOB_ERROR_SERVIZI_NON_AUTORIZZATO = 8;
    public static Integer COD_ESITO_GLOB_ERROR_ELABORAZIONE_NON_BUON_FINE = 9;
    public static Integer COD_ESITO_GLOB_ERROR_IDENTIFICATIVO_RICHIESTA_GIA_EVASA = 10;
    public static Integer COD_ESITO_GLOB_ERROR_ELABORAZIONE_ANCORA_IN_CORSO = 11;
    public static Integer COD_ESITO_GLOB_ERROR_IDENTIFICATIVO_RICHIESTA_INESISTENTE = 12;
    public static Integer COD_ESITO_GLOB_ERROR_FILE_CORROTTO = 13;
    public static Integer COD_ESITO_GLOB_ERROR_TENTATIVO_RISCONTRO_NON_AUTORIZZATO =  14;
    
    
    public static Integer COD_ESITO_BANCA_DATI_POSITIVO =  1;
    public static Integer COD_ESITO_BANCA_DATI_PRESENZA_ERRORI_SINTATTICI_INPUT = 2;
    public static Integer COD_ESITO_BANCA_ELABORAZIONE_NON_BUON_FINE = 3;
    public static Integer COD_ESITO_BANCA_ELABORAZIONE_TIME_OUT = 4;
    public static Integer COD_ESITO_BANCA_RISPOSTA_PARZIALE = 5;
    public static Integer COD_ESITO_BANCA_RICHIESTA_NON_AUTORIZZATA = 6;
    public static Integer COD_ESITO_BANCA_MANCANZA_DATI_OBBLIGATORIO_IN_INPUT = 7;
    
    
    
    
    public static Integer COD_ESITO_GLOB_BANCA_DATI_NON_AUTORIZZATA  = 14;
    
}
