package com.ryan.dao;

import com.ryan.util.ConnectionFactory;

public class DaoFactory {
	private Dao dao;
	
	public Dao getDao() {
		if (dao == null) {
			dao = new DaoImpl(ConnectionFactory.getConnection());
		}
		return dao;
	}

}
