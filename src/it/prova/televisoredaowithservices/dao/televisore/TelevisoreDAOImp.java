package it.prova.televisoredaowithservices.dao.televisore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

	private static void controlloParametriIngresso(Object input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");
	}

	private static java.sql.Date conversioneDataSQL(Date input) throws ParseException {
		java.sql.Date result = new java.sql.Date(input.getTime());
		return result;
	}

	private ResultSet autoQuerySingleRs(String query, Object input) throws Exception {
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
			if (input instanceof Integer) {
				preparedStatement.setInt(1, (int) input);
			}
			result = preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	private ResultSet autoQueryMultiRs(String query, List<Object> input) throws Exception {
		ResultSet result = null;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			for (int i = 0; i < input.size(); i++) {
				if (input.get(i) instanceof String) {
					preparedStatement.setString(i + 1, (String) input.get(i));
				}
				if (input.get(i) instanceof Long) {
					preparedStatement.setLong(i + 1, (Long) input.get(i));
				}
				if (input.get(i) instanceof Date) {
					preparedStatement.setDate(i + 1, conversioneDataSQL((Date) input.get(i)));
				}
				if (input.get(i) instanceof Integer) {
					preparedStatement.setInt(i + 1, (int) input.get(i));
				}
			}
			result = preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	private int autoQuerySingleUp(String query, Object input) throws Exception {
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
			if (input instanceof Integer) {
				preparedStatement.setInt(1, (int) input);
			}

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	private int autoQueryMultiUp(String query, List<Object> input) throws Exception {
		int result = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			for (int i = 0; i < input.size(); i++) {
				if (input.get(i) instanceof String) {
					preparedStatement.setString(i + 1, (String) input.get(i));
				}
				if (input.get(i) instanceof Long) {
					preparedStatement.setLong(i + 1, (Long) input.get(i));
				}
				if (input.get(i) instanceof Date) {
					preparedStatement.setDate(i + 1, conversioneDataSQL((Date) input.get(i)));
				}
				if (input.get(i) instanceof Integer) {
					preparedStatement.setInt(i + 1, (int) input.get(i));
				}
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

	private static Date stringToDate(String input) throws ParseException {
		Date result = new SimpleDateFormat("dd/MM/yyyy").parse(input);
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

		controlloParametriIngresso(idInput);
		if (idInput < 1l) {
			throw new RuntimeException("inserirecelemento valido");
		}
		controlloConnessione();

		Televisore result = new Televisore();
		try {
			result = autoCompleteSingle(autoQuerySingleRs("select * from televisore where id=?", idInput));
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

		controlloParametriIngresso(input);
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
			preparedStatement.setDate(4, new java.sql.Date(input.getDataProduzione().getTime()));
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

		controlloParametriIngresso(input);
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

		controlloParametriIngresso(input);
		if (input.getId() == null || input.getId() < 1) {
			throw new RuntimeException("inserire elmento valido");
		}
		int result = 0;
		try {
			result = autoQuerySingleUp("delete from televisore where id=?", input.getId());
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

		controlloParametriIngresso(input);
		controlloConnessione();

		List<Televisore> result = new ArrayList<>();
		String query = "select * from televisore where 1=1";
		if (!(input.getMarca() == null || input.getMarca().isBlank())) {
			query += " and marca like '" + input.getMarca() + "%'";
		}
		if (!(input.getModello() == null || input.getModello().isBlank())) {
			query += " and modello like '" + input.getModello() + "%'";
		}
		if (!(input.getPollici() < 1)) {
			query += " and pollici>" + input.getPollici();
		}
		if (!(input.getDataProduzione() == null)) {
			query += " and dataproduzione>'" + conversioneDataSQL(input.getDataProduzione()) + "'";
		}

		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			result = autoComplete(resultSet);
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

		controlloParametriIngresso(after);
		controlloParametriIngresso(before);
		controlloConnessione();

		List<Televisore> result = new ArrayList<>();
		List<Object> dateDaInserire = new ArrayList<>(Arrays.asList(before, after));
		try {
			result = autoComplete(
					autoQueryMultiRs("select * from televisore where dataproduzione between ? and ?", dateDaInserire));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public Televisore findPiuGrande() throws SQLException, Exception {
		
		controlloConnessione();
		
		Televisore result= new Televisore();
		try (Statement statement=connection.createStatement();ResultSet resultSet=statement.executeQuery("select * from televisore order by pollici desc limit 1")){
			result=autoCompleteSingle(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public List<String> findAllDegliUltimi6MesiByMarca() throws SQLException, Exception {
		
		controlloConnessione();
		
		List<String> result=new ArrayList<>();
		try {
			ResultSet resultSet=autoQuerySingleRs("select distinct marca from televisore where dataproduzione>?",stringToDate("22/04/2022"));
			while(resultSet.next()) {
				String temp=resultSet.getString("marca");
				result.add(temp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return result;
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
