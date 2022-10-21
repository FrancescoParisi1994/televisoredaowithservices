package it.prova.televisoredaowithservices.test;

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
	}

	private static void intro(String input) {
		System.out.println("\t..."+input+" Inizio...");
	}
	
	private static void outro(String input) {
		
		System.out.println("\t..."+input+" Riuscito...");
	}
	private static void fineTest(TelevisoreService televisoreService) throws Exception {
		System.out.println("elementi attuaalmente presenti sulla lista sono: "+televisoreService.list().size());
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
}
