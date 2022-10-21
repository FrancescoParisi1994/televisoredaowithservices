package it.prova.televisoredaowithservices.service;

import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDAOImp;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreService;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreServiceImp;

public class MyServiceFactory {

	public static TelevisoreService getTelevisoreServiceImp() {
		
		TelevisoreService televisoreService=new TelevisoreServiceImp();
		televisoreService.setTelevisoreDAO(new TelevisoreDAOImp());
		return televisoreService;
		
	}
}
