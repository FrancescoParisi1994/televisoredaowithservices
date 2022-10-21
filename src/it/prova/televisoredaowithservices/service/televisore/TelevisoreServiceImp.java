package it.prova.televisoredaowithservices.service.televisore;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.prova.televisoredaowithservices.connection.MyConnection;
import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDAO;
import it.prova.televisoredaowithservices.model.Televisore;
import it.prova.televisoredaowithservices.connection.Constants;
public class TelevisoreServiceImp implements TelevisoreService{

	private TelevisoreDAO televisoreDAO;
	
	public void setTelevisoreDAO(TelevisoreDAO televisoreDAO) {
		this.televisoreDAO=televisoreDAO;
	}
	public TelevisoreServiceImp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public List<Televisore> list() throws Exception {
		List<Televisore> result=new ArrayList<>();
		try (Connection connection=MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)){
			televisoreDAO.setConnection(connection);
			result=televisoreDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return result;
	}
	@Override
	public Televisore get(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int update(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int insert(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}