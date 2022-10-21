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

public class TelevisoreDAOImp<T> extends AbstractMySQLDAO implements TelevisoreDAO {

	public TelevisoreDAOImp() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void controlloConnessione() throws SQLException, Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
	}

	private static void controlloParametriIngrsso(Object input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");
	}
	
	private static java.sql.Date conversioneDataSQL(Date input) throws ParseException {
		java.sql.Date result=new java.sql.Date(input.getTime());
		return result;
	}
	
	private ResultSet autoQueryRs(String query,T input) throws Exception {
		ResultSet result=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			if (input instanceof String) {
				preparedStatement.setString(1, (String) input);
			}
			if (input instanceof Long) {
				preparedStatement.setLong(1, (Long)input);
			}
			if (input instanceof Date) {
				preparedStatement.setDate(1, conversioneDataSQL((Date)input));
			}
			result=preparedStatement.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	private static List<Televisore> autoComplete(ResultSet resultSet) throws Exception {
		List<Televisore> result=new ArrayList<>();
		while(resultSet.next()) {
			Televisore temp=new Televisore();
			temp.setId(resultSet.getLong("id"));
			temp.setMarca(resultSet.getString("marca"));
			temp.setModello(resultSet.getString("modello"));
			temp.setPollici(resultSet.getInt("pollici"));
			temp.setDataProduzione(resultSet.getDate("dataproduzione"));
			result.add(temp);
		}
		return result;
	}
	@Override
	public List<Televisore> list() throws Exception {

		controlloConnessione();
		
		List<Televisore> result=new ArrayList<>();
		try (Statement statement=connection.createStatement();ResultSet resultSet=statement.executeQuery("select * from televisore")){
			result=autoComplete(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection=connection;
		// TODO Auto-generated method stub
		
	}

}
