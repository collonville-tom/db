package org.tc.osgi.bundle.external.db.connector.neo4j.test.bean;

import org.tc.osgi.bundle.external.db.connector.interf.module.service.INeo4jConnectorService;
import org.tc.osgi.bundle.utils.interf.module.service.IUtilsService;

public class Neo4jConnectorTestBean {

	private INeo4jConnectorService neo4jConnectorService;
	private IUtilsService utilsService;

	public IUtilsService getUtilsService() {
		return utilsService;
	}

	public void setUtilsService(final IUtilsService utilsService) {
		this.utilsService = utilsService;
	}

	public void testTinyDB() {

	}

}
