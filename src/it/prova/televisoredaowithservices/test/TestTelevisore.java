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
		TelevisoreService televisoreService = MyServiceFactory.getTelevisoreServiceImp();

		testList(televisoreService);
		fineTest(televisoreService);

		testInsert(televisoreService);
		fineTest(televisoreService);

		testDelete(televisoreService);
		fineTest(televisoreService);
		
		testUpdate(televisoreService);
		fineTest(televisoreService);
		
		testFindByExample(televisoreService);
		fineTest(televisoreService);
		
		testFindAllBetweenDate(televisoreService);
		fineTest(televisoreService);
		
		testFindPiuGrande(televisoreService);
		fineTest(televisoreService);
		
		testFindAllDegliUltimi6MesiByMarca(televisoreService);
		fineTest(televisoreService);
	}

	// MYTOOL===========00
	private static void intro(String input) {
		System.out.println("\t..." + input + " Inizio...");
	}

	private static void outro(String input, TelevisoreService televisoreService) throws Exception {
		televisoreService.removeAll();
		System.out.println("\t..." + input + " Riuscito...");
	}

	private static void fineTest(TelevisoreService televisoreService) throws Exception {
		System.out.println("elementi attualmente presenti sulla lista sono: " + televisoreService.list().size());
	}

	private static Date stringToDate(String input) throws ParseException {
		Date result = new SimpleDateFormat("dd/MM/yyyy").parse(input);
		return result;
	}

	private static void selectAll(TelevisoreService televisoreService) throws Exception {
		System.out.println(televisoreService.list().size());
		for (Televisore iterable_element : televisoreService.list()) {
			System.out.println(iterable_element.toString());
		}
	}

	// METODI=============================
	private static void testList(TelevisoreService televisoreService) throws Exception {

		String titolo = "testList";
		intro(titolo);
		
		List<Televisore> listaAttuale = televisoreService.list();
		System.out.println(listaAttuale.size());
		
		if (listaAttuale.size() == 0) {
			System.out.println("nessun elemento trovato");
		} else {
			for (Televisore televisoreItem : listaAttuale) {
				System.out.println(televisoreItem.toString());
			}
		}
		
		outro(titolo, televisoreService);
	}

	private static void testInsert(TelevisoreService televisoreService) throws ParseException, Exception {

		String titolo = "testInsert";
		intro(titolo);
		
		int risultato = televisoreService.insert(new Televisore("OK", "Plasma", 32, stringToDate("05/09/1995")));
		
		System.out.println(risultato);
		if (risultato == 0) {
			throw new RuntimeException("INSERT FALLITA");
		}
		selectAll(televisoreService);
		
		outro(titolo, televisoreService);
	}

	private static void testDelete(TelevisoreService televisoreService) throws Exception {

		String titolo = "testDelete";
		intro(titolo);
		
		televisoreService.insert(new Televisore("OK", "Plasma", 32, stringToDate("05/09/1995")));
		televisoreService.insert(new Televisore("OK", "Plasma", 32, stringToDate("05/09/1995")));
		
		for (Televisore iterable_element : televisoreService.list()) {
			long temp = iterable_element.getId();
			televisoreService.delete(iterable_element);
			if (!(televisoreService.get(temp) == null || televisoreService.get(temp).getMarca() == null)) {
				throw new RuntimeException("DELETE FALLITA");
			}
		}
		
		outro(titolo, televisoreService);
	}

	private static void testUpdate(TelevisoreService televisoreService) throws Exception {

		String titolo = "testUpdate";
		intro(titolo);
		
		televisoreService.insert(new Televisore("OK", "Plasma", 32, stringToDate("05/09/1995")));
		
		long idRicerca=televisoreService.list().get(televisoreService.list().size()-1).getId();
		Televisore perUpdate=new Televisore(idRicerca, "Lg", titolo, 0, stringToDate("05/09/1995"));
		
		televisoreService.update(perUpdate);
		if (!(televisoreService.get(idRicerca).getMarca().equals(perUpdate.getMarca()))) {
			throw new RuntimeException("UPDATE FALLITO");
		}
		
		outro(titolo, televisoreService);

	}
	
	private static void testFindByExample(TelevisoreService televisoreService) throws Exception {

		String titolo = "testFindByExample";
		intro(titolo);
		
		televisoreService.insert(new Televisore("Samsung", "Plasma", 32, stringToDate("05/09/1995")));
		televisoreService.insert(new Televisore("OK", "ModellomMoltoFigo", 32, stringToDate("05/09/1995")));
		televisoreService.insert(new Televisore("OK", "Plasma", 40, stringToDate("05/09/1995")));
		televisoreService.insert(new Televisore("OK", "Plasma", 32, stringToDate("05/09/2000")));
		
		Televisore daCercare=new Televisore("", null, 0, null);
		List<Televisore> risultato=televisoreService.findByExample(daCercare);
		for (Televisore televisoreItem : risultato) {
			System.out.println(televisoreItem.toString());
		}
		if (risultato.size()==0) {
			throw new RuntimeException("FINDBYEXAMPLE FALLITA");
		}
		outro(titolo, televisoreService);
	}

	private static void testFindAllBetweenDate(TelevisoreService televisoreService) throws Exception {
		
		String titolo="testFindAllBetweenDate";
		intro(titolo);
		
		televisoreService.insert(new Televisore("Samsung", "Plasma", 32, stringToDate("05/09/1993")));
		televisoreService.insert(new Televisore("OK", "ModellomMoltoFigo", 32, stringToDate("05/09/1998")));
		televisoreService.insert(new Televisore("OK", "Plasma", 40, stringToDate("05/09/1995")));
		televisoreService.insert(new Televisore("OK", "Plasma", 32, stringToDate("05/09/2000")));
		
		Date daData=stringToDate("01/01/1990");
		Date aData=stringToDate("01/01/1996");
		if (televisoreService.findAllBetweenDate(daData, aData).size()!=2) {
			throw new RuntimeException("FINDALLBETWEENDATE FALLITA");
		}
		outro(titolo, televisoreService);
		
	}
	
	private static void testFindPiuGrande(TelevisoreService televisoreService) throws Exception {
		
		String titolo="testFindPiuGrande";
		intro(titolo);
		
		televisoreService.insert(new Televisore("Samsung", "Plasma", 52, stringToDate("05/09/1993")));
		televisoreService.insert(new Televisore("OK", "ModellomMoltoFigo", 32, stringToDate("05/09/1998")));
		televisoreService.insert(new Televisore("OK", "Plasma", 40, stringToDate("05/09/1995")));
		televisoreService.insert(new Televisore("OK", "Plasma", 30, stringToDate("05/09/2000")));
		
		Televisore risultato=televisoreService.findPiuGrande();
		if (risultato.getPollici()!=52) {
			throw new RuntimeException("FINDPIUGRANDE FALLITA");
		}
		outro(titolo, televisoreService);
		
	}

	private static void testFindAllDegliUltimi6MesiByMarca(TelevisoreService televisoreService) throws Exception {
		
		String titolo="testFindAllDegliUltimi6MesiByMarca";
		intro(titolo);
		
		televisoreService.insert(new Televisore("Samsung", "Plasma", 52, stringToDate("05/09/1993")));
		televisoreService.insert(new Televisore("OK", "ModellomMoltoFigo", 32, stringToDate("05/09/1998")));
		televisoreService.insert(new Televisore("OK", "Plasma", 40, stringToDate("05/09/1995")));
		televisoreService.insert(new Televisore("OK", "Plasma", 30, stringToDate("05/09/2022")));
		
		List<String> risultato=televisoreService.findAllDegliUltimi6MesiByMarca();
		if (risultato.size()!=1) {
			throw new RuntimeException("FINDALLDEGLIULTIMI6MESIBYMARCA FALLITA");
		}
		outro(titolo, televisoreService);
		
	}

}
