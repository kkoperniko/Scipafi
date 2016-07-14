package it.scipafi.validation.sezione;

import it.rdbms.model.TbScipafiTipoDichiarazioneRedditi;
import it.scipafi.validation.cache.liste.TbScipafiListeStatichePrecaricate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	/**
	 * 
	 */
    	public static String FORMATO_DATE_1 = "dd/MM/yyyy";
	public static String FORMATO_DATE_2 = "yyyy.MM.dd HH:mi:ss";

	public static String conversionDateFormat(String sDate,String tipoService) 
	{
		StringBuilder result = new StringBuilder();
	    String[] elementi;
	    String separatore = null;
	    elementi = sDate.split("-");
	    if (tipoService.equals("INAIL"))
	    	separatore = "-";
	    if (tipoService.equals("TS_SANITARIA"))
	    	separatore = "/";
	    if (elementi.length > 1 )
	    	result.append(elementi[2]).append(separatore).append(elementi[1]).append(separatore).append(elementi[0]);
	    else
	    	result.append(sDate);
	    if (isVuoto(sDate))
	    	return null;
	    else
	    	return result.toString();  
	}
	
	public static boolean contrMaxLength(String campo, int lunghezza)
	{
		if(Util.isVuoto(campo))
		{
			if(lunghezza == 0)
				return true;
			
			return false;
		}
		
		if(campo.length() <= lunghezza)
		{
			return true;
		}
		
		return false;
	}
	
	public static String randomString8an(){
		
		String caratteri = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random rnd = new Random();
		char[] text = new char[8];
		boolean ok = false;
		
		while(!ok){
			for(int i = 0; i<8; i++){
				text[i]=caratteri.charAt(rnd.nextInt(caratteri.length()));
			}
			if(String.valueOf(text).matches(".*\\d.*")){
				ok=true;
			}
		}
		
		return String.valueOf(text);
	}

        public static boolean isDate(String campo)
        {
            boolean result = false;
            try
             {
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
                Date date = format.parse(campo);
                result = true;
                if (campo.length()<10)
                    result = false;
             }   
            catch (Exception e)
            {
                result = false;
            }    
            return result;
        } 
        
        public static Date convertDataStringToDate(String campo)
        {
            Date result = null;
            try
             {
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
                result = format.parse(campo);
             }   
            catch (Exception e)
            {
                result = null;
            }    
            return result;
        } 
        
        public static String convertDataDateToString(Date campo)
         {
            String result = null;
            try
             {
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
                result = format.format(campo);
             }   
            catch (Exception e)
            {
                result = null;
            }    
            return result;
        }
        
	public static boolean isVuoto(String campo)
	{
		if(campo == null || campo.trim().equals("") || campo.trim().equalsIgnoreCase("null"))
			return true;

		return false;
	}
	
	public static boolean isNumericoIntero(String campo)
	{
		
		if(Util.isVuoto(campo))
			return false;
		
		if (campo.matches("[0-9]+"))
			return true;

		return false;
	}
	
	
	public static boolean isNumericoInteroImporto(String campo)
	{
		
		if(Util.isVuoto(campo))
			return false;
		
		try {
			Integer.parseInt(campo);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	
	public static boolean isCodiceFiscalePF_PIVA(String campo)
	{
		if(campo == null)
			return false;
		
		if(  isCodiceFiscalePF(campo) || isCodiceFiscalePIVA(campo))
			return true;
		
		return false;
	}
	
	public static boolean isCodiceFiscalePF(String campo)
	{
		String pattern ="\\p{L}{6,6}[0-9LMNPQRSTUV][0-9LMNPQRSTUV]\\p{L}[0-9LMNPQRSTUV][0-9LMNPQRSTUV]\\p{L}[0-9LMNPQRSTUV][0-9LMNPQRSTUV][0-9LMNPQRSTUV]\\p{L}";
		String pattern2 ="\\p{L}{6,6}\\d\\d\\p{L}\\d\\d\\p{L}\\d\\d\\d\\p{L}";
		if(campo == null)
			return false;
		
		//if( campo.length()!=16 || !Util.isAlfanumericoSemplice(campo))
//		if(campo.length()!=16 || !campo.matches(pattern) || !campo.matches(pattern2))
		if(campo.length()!=16 || (!campo.matches(pattern) && !campo.matches(pattern2)))
			return false;
		
		return true;
	}
	
	
	public static boolean isCodiceFiscalePIVA(String campo)
	{
//		String pattern ="\\p{L}{6,6}\\d\\d\\p{L}\\d\\d\\p{L}\\d\\d\\d\\p{L}";
		String pattern ="\\d{11,11}";
		
		if(campo == null)
			return false;
		
		//if( campo.length()!=11 || !Util.isNumericoIntero(campo))
		if( campo.length()!=11 || !campo.matches(pattern))
			return false;
		
		return true;
	}
	
	public static boolean isAlfanumericoSemplice(String campo)
	{		
		if(Util.isVuoto(campo))
			return false;
		
		if (campo.matches("[a-zA-Z0-9]+"))
			return true;
		
		
		return false;

	}
	
	public static boolean isAlfanumericoIndirizzo(String campo)
	{		
		if(Util.isVuoto(campo))
			return false;
		
//		if (campo.matches("([a-zA-Z0-9]+[\\.]?[a-zA-Z0-9\\s'*,\\-]*)+"))
//		if (campo.matches("([a-zA-Z0-9\\s\\/]+[\\.]?[a-zA-Z0-9\\s'*,\\-]*)+"))
		if (campo.matches("([a-zA-Z0-9\\s\\/.,'\\-*])+"))
			return true;
		
		
		return false;

	}
	
	public static boolean isAlfanumericoConSpazi(String campo)
	{		
		if(Util.isVuoto(campo))
			return false;
		
		if (campo.matches("[a-zA-Z0-9\\s'*, ]+"))
			return true;
		
		
		return false;

	}
	
	public static boolean isAlfabetico(String campo)
	{		
		if(Util.isVuoto(campo))
			return false;
		
		if (campo.matches("[a-zA-Z\\s'-]+"))
			return true;
		
		
		return false;

	}
	
	
	/**
	 * Restituisce la data odierna nel formato voluto
	 * */
	public static String getDataOdierna(String formato)
	{		
		try{
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		
		Date date = new Date();
		
		String data = sdf.format(date);
		
		return data;
		}catch(Exception e)
		{
			return "";
		}
	}	
	
	public static boolean isTipoDocumentoAD(String campo)
	{
		if(Util.isVuoto(campo))
			return false;
		
		boolean risultato = false;
		try {
			risultato = true; //CacheDB.getHashTipiDocumentoAD().containsKey(campo);
		} catch (Exception e) {

			return false;
		}
		
		return risultato;
	}
        
        public static String getAnnoOdierno()
	{		
		String data = Util.getDataOdierna(FORMATO_DATE_1);
		
		String anno = data.substring(6, 10);
		
		return anno;
	}
        public static int compareAnno(String annoA, String annoB)
	{		
		if(Util.isVuoto(annoA))
			return -1;
		
		if(Util.isVuoto(annoB))
			return -1;		
		int annoAI = 0;
		try{
			annoAI = Integer.parseInt(annoA);
		}catch(NumberFormatException e)
		{
			return -1;
		}
		
		int annoBI = 0;
		try{
			annoBI = Integer.parseInt(annoB);
		}catch(NumberFormatException e)
		{
			return -1;
		}		
		
		if(annoAI > annoBI)
		{
			return 2;
		}
		
		if(annoAI < annoBI)
		{
			return 0;
		}
		
		return 1;
	}	
        public static boolean isTipoDichiarazione(String campo)
	{
		if(Util.isVuoto(campo))
			return false;
		
		boolean risultato = false;
		try {
                    for (TbScipafiTipoDichiarazioneRedditi tb : TbScipafiListeStatichePrecaricate.INSTANCE.getListaTipoDichiarazioneRedditi())
                    {
                        if (tb.getTipodichiarazioneredditiId().equals(new Integer(campo)))
                        {
                            risultato = true;
                            break;
                        }    
                    }    
		} catch (Exception e) {

			return false;
		}
		
		return risultato;
	}
}