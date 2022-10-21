package it.prova.televisoredaowithservices.dao.televisore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.AbstractMySQLDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreDAOImp extends AbstractMySQLDAO implements TelevisoreDAO {

	public TelevisoreDAOImp() {
		super();
		// TODO Auto-generated constructor stub
	}

	// MYTOOL=======================00
	private void controlloConnessione() throws SQLException, Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
	}

	private static void controlloParametriIngrsso(Object input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");
	}

	private static java.sql.Date conversioneDataSQL(Date input) throws ParseException {
		java.sql.Date result = new java.sql.Date(input.getTime());
		return result;
	}

	private ResultSet autoQueryRs(String query, Object input) throws Exception {
		ResultSet result = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			if (input instanceof String) {
				preparedStatement.setString(1, (String) input);
			}
			if (input instanceof Long) {
				preparedStatement.setLong(1, (Long) input);
			}
			if (input instanceof Date) {
				preparedStatement.setDate(1, conversioneDataSQL((Date) input));
			}
			result = preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	private int autoQueryUp(String query, Object input) throws Exception {
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			if (input instanceof String) {
				preparedStatement.setString(1, (String) input);
			}
			if (input instanceof Long) {
				preparedStatement.setLong(1, (Long) input);
			}
			if (input instanceof Date) {
				preparedStatement.setDate(1, conversioneDataSQL((Date) input));
			}
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	private static List<Televisore> autoComplete(ResultSet resultSet) throws Exception {
		List<Televisore> result = new ArrayList<>();
		while (resultSet.next()) {
			Televisore temp = new Televisore();
			temp.setId(resultSet.getLong("id"));
			temp.setMarca(resultSet.getString("marca"));
			temp.setModello(resultSet.getString("modello"));
			temp.setPollici(resultSet.getInt("pollici"));
			temp.setDataProduzione(resultSet.getDate("dataproduzione"));
			result.add(temp);
		}
		return result;
	}

	private static Televisore autoCompleteSingle(ResultSet resultSet) throws Exception {
		Televisore result = new Televisore();
		if (resultSet.next()) {
			result.setId(resultSet.getLong("id"));
			result.setMarca(resultSet.getString("marca"));
			result.setModello(resultSet.getString("modello"));
			result.setPollici(resultSet.getInt("pollici"));
			result.setDataProduzione(resultSet.getDate("dataproduzione"));
		}
		return result;
	}

	// METODI=====================================
	@Override
	public List<Televisore> list() throws Exception {

		controlloConnessione();

		List<Televisore> result = new ArrayList<>();
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from televisore")) {
			result = autoComplete(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public Televisore get(Long idInput) throws Exception {

		controlloParametriIngrsso(idInput);
		if (idInput < 1l) {
			throw new RuntimeException("inserirecelemento valido");
		}
		controlloConnessione();

		Televisore result = new Televisore();
		try {
			result = autoCompleteSingle(autoQueryRs("select * from televisore where id=?", idInput));
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

		controlloParametriIngrsso(input);
		if (input.getId() == null || input.getId() < 1l) {
			throw new RuntimeException("inserire elemento valido");
		}
		controlloConnessione();

		int result = 0;
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("update televisore set marca=?, modello=?, pollici=?, dataproduzione=? where id=?")) {
			preparedStatement.setString(1, input.getMarca());
			preparedStatement.setString(2, input.getModello());
			preparedStatement.setInt(3, input.getPollici());
			preparedStatement.setDate(4, conversioneDataSQL(input.getDataProduzione()));
			preparedStatement.setLong(5, input.getId());
			result = preparedStatement.executeUpdate();
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

		controlloParametriIngrsso(input);
		if (input.getMarca() == null || input.getMarca().isBlank() || input.getModello() == null
				|| input.getModello() == null || input.getDataProduzione() == null || input.getPollici() < 1) {
			throw new RuntimeException("inserire elemento valido");
		}
		controlloConnessione();

		int result = 0;
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("insert into televisore(marca,modello,pollici,dataproduzione) values (?,?,?,?)")) {
			preparedStatement.setString(1, input.getMarca());
			preparedStatement.setString(2, input.getModello());
			preparedStatement.setInt(3, input.getPollici());
			preparedStatement.setDate(4, new java.sql.Date(input.getDataProduzione().getTime()));
			result = preparedStatement.executeUpdate();
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

		controlloParametriIngrsso(input);
		if (input.getId() == null || input.getId() < 1) {
			throw new RuntimeException("inserire elmento valido");
		}
		int result = 0;
		try {
			result = autoQueryUp("delete from televisore where id=?", input.getId());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Televisore> findAllBetweenDate(Date before, Date after) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Televisore findPiuGrande() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Televisore> findAllDegliUltimi6MesiByMarca() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeAll() throws Exception {

		controlloConnessione();
		int result = 0;
		try (Statement statement = connection.createStatement()) {
			result = statement.executeUpdate("delete from televisore");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
		// TODO Auto-generated method stub

	}

}
