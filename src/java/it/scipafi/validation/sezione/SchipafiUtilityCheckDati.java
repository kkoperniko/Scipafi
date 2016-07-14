package it.scipafi.validation.sezione;

import it.rdbms.model.TbScipafiTipoEsitoCampo;
import java.util.ArrayList;
import java.util.List;


public class SchipafiUtilityCheckDati {
	
	public static SchipafiUtilityCheckDati INSTACE = new SchipafiUtilityCheckDati();
	
	public final Integer ESITO_CONTROLLO_OK = 0;
	public final Integer ESITO_CONTROLLO_NO_LENGTH = 1;
	public final Integer ESITO_CONTROLLO_NO_LETTER_DIGIT = 2;
	public final int MAX_LENGHT_TS_NUMERO = 20;
	public final int MAX_LENGHT_CDOD_FISC = 16;
	public final int MAX_LENGHT_CDOD_FISC_PROVV = 11;
	
	private final List<ItemEntityKeyValue> caratteriOrdinePARI = initListaCarattereOrdinePARI();
	private final List<ItemEntityKeyValue> caratteriOrdineDISPARI = initListaCarattereOrdineDISPARI();
	private final List<ItemEntityKeyValue> caratteriCHECK_DIGIT = initListaCarattereCHECK_DIGIT();
       
	
	private void SchipafiUtilityCheckDati(){
	}
	
               
	private List<ItemEntityKeyValue>  initListaCarattereCHECK_DIGIT()
	{
		List<ItemEntityKeyValue> result = new ArrayList<ItemEntityKeyValue>();
		result.add(new  ItemEntityKeyValue("A", 0));
		result.add(new  ItemEntityKeyValue("B", 1));
		result.add(new  ItemEntityKeyValue("C", 2));
		result.add(new  ItemEntityKeyValue("D", 3));
		result.add(new  ItemEntityKeyValue("E", 4));
		result.add(new  ItemEntityKeyValue("F", 5));
		result.add(new  ItemEntityKeyValue("G", 6));
		result.add(new  ItemEntityKeyValue("H", 7));
		result.add(new  ItemEntityKeyValue("I", 8));
		result.add(new  ItemEntityKeyValue("J", 9));
		result.add(new  ItemEntityKeyValue("K", 10));
		result.add(new  ItemEntityKeyValue("L", 11));
		result.add(new  ItemEntityKeyValue("M", 12));
		result.add(new  ItemEntityKeyValue("N", 13));
		result.add(new  ItemEntityKeyValue("O", 14));
		result.add(new  ItemEntityKeyValue("P", 15));
		result.add(new  ItemEntityKeyValue("Q", 16));
		result.add(new  ItemEntityKeyValue("R", 17));
		result.add(new  ItemEntityKeyValue("S", 18));
		result.add(new  ItemEntityKeyValue("T", 19));
		result.add(new  ItemEntityKeyValue("U", 20));
		result.add(new  ItemEntityKeyValue("V", 21));
		result.add(new  ItemEntityKeyValue("W", 22));
		result.add(new  ItemEntityKeyValue("X", 23));
		result.add(new  ItemEntityKeyValue("Y", 24));
		result.add(new  ItemEntityKeyValue("Z", 25));
		return result;
	}
	private List<ItemEntityKeyValue>  initListaCarattereOrdinePARI()
	{
		List<ItemEntityKeyValue> result = new ArrayList<ItemEntityKeyValue>();
		result.add(new  ItemEntityKeyValue("A",0));
		result.add(new  ItemEntityKeyValue("B",1));
		result.add(new  ItemEntityKeyValue("C",2));
		result.add(new  ItemEntityKeyValue("D",3));
		result.add(new  ItemEntityKeyValue("E",4));
		result.add(new  ItemEntityKeyValue("F",5));
		result.add(new  ItemEntityKeyValue("G",6));
		result.add(new  ItemEntityKeyValue("H",7));
		result.add(new  ItemEntityKeyValue("I",8));
		result.add(new  ItemEntityKeyValue("J",9));
		result.add(new  ItemEntityKeyValue("K",10));
		result.add(new  ItemEntityKeyValue("L",11));
		result.add(new  ItemEntityKeyValue("M",12));
		result.add(new  ItemEntityKeyValue("N",13));
		result.add(new  ItemEntityKeyValue("O",14));
		result.add(new  ItemEntityKeyValue("P",15));
		result.add(new  ItemEntityKeyValue("Q",16));
		result.add(new  ItemEntityKeyValue("R",17));
		result.add(new  ItemEntityKeyValue("S",18));
		result.add(new  ItemEntityKeyValue("T",19));
		result.add(new  ItemEntityKeyValue("U",20));
		result.add(new  ItemEntityKeyValue("V",21));
		result.add(new  ItemEntityKeyValue("W",22));
		result.add(new  ItemEntityKeyValue("X",23));
		result.add(new  ItemEntityKeyValue("Y",24));
		result.add(new  ItemEntityKeyValue("Z",25));
		result.add(new  ItemEntityKeyValue("0",0));
		result.add(new  ItemEntityKeyValue("1",1));
		result.add(new  ItemEntityKeyValue("2",2));
		result.add(new  ItemEntityKeyValue("3",3));
		result.add(new  ItemEntityKeyValue("4",4));
		result.add(new  ItemEntityKeyValue("5",5));
		result.add(new  ItemEntityKeyValue("6",6));
		result.add(new  ItemEntityKeyValue("7",7));
		result.add(new  ItemEntityKeyValue("8",8));
		result.add(new  ItemEntityKeyValue("9",9));
		return result;
	}
	
	private List<ItemEntityKeyValue>  initListaCarattereOrdineDISPARI()
	{
		List<ItemEntityKeyValue> result = new ArrayList<ItemEntityKeyValue>();
		result.add(new  ItemEntityKeyValue("A", 1));
		result.add(new  ItemEntityKeyValue("B", 0));
		result.add(new  ItemEntityKeyValue("C", 5));
		result.add(new  ItemEntityKeyValue("D", 7));
		result.add(new  ItemEntityKeyValue("E", 9));
		result.add(new  ItemEntityKeyValue("F", 13));
		result.add(new  ItemEntityKeyValue("G", 15));
		result.add(new  ItemEntityKeyValue("H", 17));
		result.add(new  ItemEntityKeyValue("I", 19));
		result.add(new  ItemEntityKeyValue("J", 21));
		result.add(new  ItemEntityKeyValue("K", 2));
		result.add(new  ItemEntityKeyValue("L", 4));
		result.add(new  ItemEntityKeyValue("M", 18));
		result.add(new  ItemEntityKeyValue("N", 20));
		result.add(new  ItemEntityKeyValue("O", 11));
		result.add(new  ItemEntityKeyValue("P", 3));
		result.add(new  ItemEntityKeyValue("Q", 6));
		result.add(new  ItemEntityKeyValue("R", 8));
		result.add(new  ItemEntityKeyValue("S", 12));
		result.add(new  ItemEntityKeyValue("T", 14));
		result.add(new  ItemEntityKeyValue("U", 16));
		result.add(new  ItemEntityKeyValue("V", 10));
		result.add(new  ItemEntityKeyValue("W", 22));
		result.add(new  ItemEntityKeyValue("X", 25));
		result.add(new  ItemEntityKeyValue("Y", 24));
		result.add(new  ItemEntityKeyValue("Z", 23));
		result.add(new  ItemEntityKeyValue("0", 1));
		result.add(new  ItemEntityKeyValue("1", 0));
		result.add(new  ItemEntityKeyValue("2", 5));
		result.add(new  ItemEntityKeyValue("3", 7));
		result.add(new  ItemEntityKeyValue("4", 9));
		result.add(new  ItemEntityKeyValue("5", 13));
		result.add(new  ItemEntityKeyValue("6", 15));
		result.add(new  ItemEntityKeyValue("7", 17));
		result.add(new  ItemEntityKeyValue("8", 19));
		result.add(new  ItemEntityKeyValue("9", 21));
		return result;
	}
	
	public Integer checkControlDigitLetterCodiceFiscale(String value){
		
			Integer result = this.ESITO_CONTROLLO_OK;
			int sum_order_pari = 0;
			int sum_order_dispari = 0;
			int value_check_digit = 0;
			String codFiscAppo = "";
			String carattere = "";
			if (value.length()!=MAX_LENGHT_CDOD_FISC){
				result=this.ESITO_CONTROLLO_NO_LENGTH;
			}
			else {
				value = value.toUpperCase();
				int i =0;
				while (i<value.length())
				{
					
					carattere = Character.toString(value.charAt(i));
					if (i<(value.length()-1)) // non prendo l' utlimo carattere
					{	
						codFiscAppo = codFiscAppo + carattere;
						if ( ((i+1) % 2) == 0) // ordine pari
						{
					          for (ItemEntityKeyValue item : caratteriOrdinePARI){
					        	  if (item.getCarattere().equals(carattere)) {
					        		  sum_order_pari = sum_order_pari + item.getValore();
					        		  break;
					        	  }
					          }
						}
						else
						{
							for (ItemEntityKeyValue item : caratteriOrdineDISPARI){
					        	  if (item.getCarattere().equals(carattere)) {
					        		  sum_order_dispari = sum_order_dispari + item.getValore();
					        		  break;
					        	  }
					          }
						}
					}
						
					i++;	
				}
				value_check_digit =  ((sum_order_pari + sum_order_dispari) %  26);
				for (ItemEntityKeyValue item : caratteriCHECK_DIGIT){
		        	  if (item.getValore() == value_check_digit) {
		        		  carattere = item.getCarattere();
		        		  break;
		        	  }
		          }
				codFiscAppo = codFiscAppo + carattere;
				if (!codFiscAppo.equals(value))
		           result = this.ESITO_CONTROLLO_NO_LETTER_DIGIT;
			}
			return result;
	}
	
	public Integer checkControlLHUNTesseraSanitariaNumero(String value){
		
		Integer result = this.ESITO_CONTROLLO_OK;
		int sum_order_pari = 0;
		int value_order = 0;
		int sum_order_dispari = 0;
		int value_check_digit = 0;
		String codFiscAppo = "";
		String carattere = "";
		if (value.length()!=MAX_LENGHT_TS_NUMERO) // Lunghezza string anumerica TS Numero.
		{
			result=this.ESITO_CONTROLLO_NO_LENGTH;
		}
		else {
			value = value.toUpperCase();
			int i =0;
			while (i<value.length())
			{
				carattere = Character.toString(value.charAt(i));
				codFiscAppo = codFiscAppo + carattere;
				if ((i % 2) == 0) // ordine pari
				{
					value_order = new Integer(carattere) * 2;
					if (value_order > 9)                        
						value_order = value_order - 9;                       
					sum_order_pari = sum_order_pari + value_order;     
				}
				else
				{
					sum_order_dispari = sum_order_dispari +  new Integer(carattere); 
				}
				i++;	
			}
			value_check_digit = sum_order_pari + sum_order_dispari;                                        
	        if ((value_check_digit % 10 ) > 0)  
	           result = this.ESITO_CONTROLLO_NO_LETTER_DIGIT;
		}
		return result;
	}
	
	public Integer checkControlCodiceFiscaleProvv(String value){
		
		Integer result = this.ESITO_CONTROLLO_OK;
		int sum_order_pari = 0;
		int value_order = 0;
		int sum_order_dispari = 0;
		int value_check_digit = 0;
		String codFiscAppo = "";
		String carattere = "";
		if (value.length()!=MAX_LENGHT_CDOD_FISC_PROVV){
			result=this.ESITO_CONTROLLO_NO_LENGTH;
		}
		else {
			value = value.toUpperCase();
			int i =0;
			while (i<value.length())
			{
				carattere = Character.toString(value.charAt(i));
				if (i<(value.length()-1)) // non prendo l' utlimo carattere
				{	
					codFiscAppo = codFiscAppo + carattere;
					if ( ((i+1) % 2) == 0) // ordine pari
					{
						value_order = new Integer(carattere) * 2;
						if (value_order > 9)                        
							value_order = value_order - 9;                       
						sum_order_pari = sum_order_pari + value_order;     
					}
					else
					{
						sum_order_dispari = sum_order_dispari +  new Integer(carattere); 
					}
				}
				i++;	
			}
			value_check_digit = sum_order_pari + sum_order_dispari;                                        
	        if ((value_check_digit % 10 ) > 0)  
	        	value_check_digit = (10 - (value_check_digit % 10 ));     
	        codFiscAppo = codFiscAppo + new Integer(value_check_digit).toString();
			if (!codFiscAppo.equals(value))
	           result = this.ESITO_CONTROLLO_NO_LETTER_DIGIT;
		}
		return result;
	}
	
	
	private class ItemEntityKeyValue
	{
		private int valore;
		private String carattere;
		

		
		public ItemEntityKeyValue(String carattere,int valore){
			this.carattere = carattere;
			this.valore = valore;
		}
		
		public int getValore() {
			return valore;
		}
		public void setValore(int valore) {
			this.valore = valore;
		}
		public String getCarattere() {
			return carattere;
		}
		public void setCarattere(String carattere) {
			this.carattere = carattere;
		}
		
		
		
	}

}
