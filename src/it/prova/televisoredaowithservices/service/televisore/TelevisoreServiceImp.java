package it.prova.televisoredaowithservices.service.televisore;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.connection.MyConnection;
import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDAO;
import it.prova.televisoredaowithservices.model.Televisore;
import it.prova.televisoredaowithservices.connection.Constants;

public class TelevisoreServiceImp implements TelevisoreService {

	private TelevisoreDAO televisoreDAO;

	public void setTelevisoreDAO(TelevisoreDAO televisoreDAO) {
		this.televisoreDAO = televisoreDAO;
	}

	public TelevisoreServiceImp() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Televisore> list() throws Exception {
		List<Televisore> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			televisoreDAO.setConnection(connection);
			result = televisoreDAO.list();
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
		Televisore result = new Televisore();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			televisoreDAO.setConnection(connection);
			result = televisoreDAO.get(idInput);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public int update(Televisore input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			televisoreDAO.setConnection(connection);
			result = televisoreDAO.update(input);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public int insert(Televisore input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			televisoreDAO.setConnection(connection);
			result = televisoreDAO.insert(input);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public int delete(Televisore input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			televisoreDAO.setConnection(connection);
			result = televisoreDAO.delete(input);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return result;

	}

	@Override
	public int removeAll() throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			televisoreDAO.setConnection(connection);
			result = televisoreDAO.removeAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		List<Televisore> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			televisoreDAO.setConnection(connection);
			result = televisoreDAO.findByExample(input);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public List<Televisore> findAllBetweenDate(Date before, Date after) throws Exception {
		List<Televisore> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			televisoreDAO.setConnection(connection);
			result = televisoreDAO.findAllBetweenDate(before,after);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public Televisore findPiuGrande() throws Exception {
		Televisore result = new Televisore();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			televisoreDAO.setConnection(connection);
			result = televisoreDAO.findPiuGrande();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public List<String> findAllDegliUltimi6MesiByMarca() throws Exception {
		List<String> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			televisoreDAO.setConnection(connection);
			result = televisoreDAO.findAllDegliUltimi6MesiByMarca();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return result;

	}

}
