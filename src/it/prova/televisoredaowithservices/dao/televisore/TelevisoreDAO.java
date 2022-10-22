package it.prova.televisoredaowithservices.dao.televisore;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.IBaseDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public interface TelevisoreDAO extends IBaseDAO<Televisore> {

	public int removeAll() throws Exception;

	@Override
	default void setConnection(Connection connection) {
		// TODO Auto-generated method stub

	}

	@Override
	default List<Televisore> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Televisore get(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default int update(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	default int insert(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	default int delete(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	default List<Televisore> findByExample(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Televisore> findAllBetweenDate(Date before, Date after) throws Exception;

	public Televisore findPiuGrande() throws Exception;

	public List<String> findAllDegliUltimi6MesiByMarca() throws Exception;

}
