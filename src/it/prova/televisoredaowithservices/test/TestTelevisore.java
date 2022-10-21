package it.prova.televisoredaowithservices.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.model.Televisore;
import it.prova.televisoredaowithservices.service.MyServiceFactory;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreService;

public class TestTelevisore {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TelevisoreService televisoreService=MyServiceFactory.getTelevisoreServiceImp();
		
		testList(televisoreService);
		fineTest(televisoreService);
		
		testInsert(televisoreService);
		fineTest(televisoreService);
	}

	private static void intro(String input) {
		System.out.println("\t..."+input+" Inizio...");
	}
	
	private static void outro(String input) {
		
		System.out.println("\t..."+input+" Riuscito...");
	}
	
	private static void fineTest(TelevisoreService televisoreService) throws Exception {
		System.out.println("elementi attualmente presenti sulla lista sono: "+televisoreService.list().size());
	}
	
	private static Date stringToDate(String input) throws ParseException {
		Date result=new SimpleDateFormat("dd/MM/yyyy").parse(input);
		return result;
	}

	private static void selectAll(TelevisoreService televisoreService) throws Exception {
		System.out.println(televisoreService.list().size());
		for (Televisore iterable_element : televisoreService.list()) {
			System.out.println(iterable_element.toString());
		}
	}
	private static void testList(TelevisoreService televisoreService) throws Exception {
		
		String titolo="testList";
		intro(titolo);
		List<Televisore> listaAttuale=televisoreService.list();
		System.out.println(listaAttuale.size());
		if (listaAttuale.size()==0) {
			System.out.println("nessun elemento trovato");
		}else {
			for (Televisore televisoreItem : listaAttuale) {
				System.out.println(televisoreItem.toString());
			}
		}
		outro(titolo);
	}
	
	private static void testInsert(TelevisoreService televisoreService) throws ParseException, Exception {
		
		String titolo="testInsert";
		intro(titolo);
		int risultato=televisoreService.insert(new Televisore("OK", "Plasma", 32, stringToDate("05/09/1995")));
		System.out.println(risultato);
		if (risultato==0) {
			throw new RuntimeException("INSERT FALLITA");
		}
		selectAll(televisoreService);
		outro(titolo);
	}
}

