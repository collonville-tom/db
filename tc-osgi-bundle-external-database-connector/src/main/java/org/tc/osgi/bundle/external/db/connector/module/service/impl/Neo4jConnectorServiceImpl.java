package org.tc.osgi.bundle.external.db.connector.module.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.tc.osgi.bundle.external.db.connector.interf.module.service.INeo4jConnectorService;

public class Neo4jConnectorServiceImpl implements INeo4jConnectorService {

	private Map<String,Driver> neo4jDrivers=new HashMap<String,Driver>();
	
	
	public boolean buildDriver(String database,String host,String port,String name,String passwd)
	{
		if(neo4jDrivers.containsKey(database))
			return false;
		StringBuffer buff=new StringBuffer("bolt://");
		buff.append(host);
		buff.append(":");
		buff.append(port);

		Driver driver = GraphDatabase.driver( buff.toString(), AuthTokens.basic( name, passwd) );
		this.neo4jDrivers.put(database, driver);
		return true;
	}
	
	
	public Session getSession(String database)
	{
		return this.neo4jDrivers.get(database).session();
	}
	
	public boolean exist(String database)
	{
		return this.neo4jDrivers.containsKey(database);
	}
	
}
