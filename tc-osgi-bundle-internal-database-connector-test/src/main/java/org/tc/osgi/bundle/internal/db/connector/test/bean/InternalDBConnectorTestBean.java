package org.tc.osgi.bundle.internal.db.connector.test.bean;

import java.io.UnsupportedEncodingException;

import org.tc.osgi.bundle.internal.db.connector.interf.berkeley.exception.InternalDBConnectorException;
import org.tc.osgi.bundle.internal.db.connector.interf.module.service.IInternalDBConnectorService;
import org.tc.osgi.bundle.utils.interf.module.service.ILoggerUtilsService;

import com.sleepycat.je.DatabaseException;

public class InternalDBConnectorTestBean {

	private IInternalDBConnectorService internalDBConnectorService;
	private ILoggerUtilsService utilsService;

	public InternalDBConnectorTestBean() {

	}

	public IInternalDBConnectorService getInternalDBConnectorService() {
		return this.internalDBConnectorService;
	}

	public ILoggerUtilsService getUtilsService() {
		return this.utilsService;
	}

	public void setInternalDBConnectorService(final IInternalDBConnectorService internalDBConnectorService) {
		this.internalDBConnectorService = internalDBConnectorService;
	}

	public void setUtilsService(final ILoggerUtilsService utilsService) {
		this.utilsService = utilsService;
	}

	public void testTinyDB() {
		this.utilsService.getLogger(InternalDBConnectorTestBean.class).info("Debut test InternalDBConnectorTest");
		try {
			this.internalDBConnectorService.buildDataBase("/var/testdb", "dbtestbase");
			this.internalDBConnectorService.push("dbtestbase", "1", "Toto");
			this.internalDBConnectorService.push("dbtestbase", "2", "Tata");

			this.utilsService.getLogger(InternalDBConnectorTestBean.class)
				.debug("Test recup donnée 1" + this.internalDBConnectorService.get("dbtestbase", "1"));
			this.utilsService.getLogger(InternalDBConnectorTestBean.class)
				.debug("Test recup donnée 2" + this.internalDBConnectorService.get("dbtestbase", "2"));

			this.internalDBConnectorService.push("dbtestbase", "2", "Trrrrr");
			this.internalDBConnectorService.deleteInDataBase("dbtestbase", "1");

			this.utilsService.getLogger(InternalDBConnectorTestBean.class)
				.debug("Test recup donnée 2" + this.internalDBConnectorService.get("dbtestbase", "2"));
			this.utilsService.getLogger(InternalDBConnectorTestBean.class).debug(
				"Test 1 est toujours la" + this.internalDBConnectorService.contains("dbtestbase", "1"));
			this.internalDBConnectorService.closeDB("dbtestbase");
		} catch (final DatabaseException e) {
			this.utilsService.getLogger(InternalDBConnectorTestBean.class).error("Error ", e);
		} catch (UnsupportedEncodingException e) {
			this.utilsService.getLogger(InternalDBConnectorTestBean.class).error("Error ", e);
		} catch (InternalDBConnectorException e) {
			this.utilsService.getLogger(InternalDBConnectorTestBean.class).error("Error ", e);
		}

		try {
			this.internalDBConnectorService.buildEntityStore("/var/testdb", "entityStoreTestbase");
			Employee emp = new Employee(2, "Tutu", "Titi", null);
			this.internalDBConnectorService.putNoReturn("entityStoreTestbase", new Employee(1, "Toto", "Tata", null));
			this.internalDBConnectorService.putNoReturn("entityStoreTestbase", emp);

			this.utilsService.getLogger(InternalDBConnectorTestBean.class).debug(
				"Test recup empl 2 :" + this.internalDBConnectorService.get("entityStoreTestbase", 2, Employee.class).toString());
			this.utilsService.getLogger(InternalDBConnectorTestBean.class).debug(
				"Test recup empl 1 :" + this.internalDBConnectorService.get("entityStoreTestbase", emp, Employee.class));

			emp.setName("vrai nom");
			this.internalDBConnectorService.putNoReturn("entityStoreTestbase", emp);

			this.utilsService.getLogger(InternalDBConnectorTestBean.class).debug(this.internalDBConnectorService.get("entityStoreTestbase", 2, Employee.class));
			this.utilsService.getLogger(InternalDBConnectorTestBean.class).debug(
				this.internalDBConnectorService.get("entityStoreTestbase", emp, Employee.class));

			this.internalDBConnectorService.deleteInEntityStore("entityStoreTestbase", this.internalDBConnectorService.get("entityStoreTestbase", 2,
				Employee.class));
			this.internalDBConnectorService.deleteInEntityStore("entityStoreTestbase", emp);

			this.utilsService.getLogger(InternalDBConnectorTestBean.class).debug(
				"Test presence empl 1 :" + this.internalDBConnectorService.contains("entityStoreTestbase", emp, Employee.class));
			this.utilsService.getLogger(InternalDBConnectorTestBean.class).debug(
				"Test presence empl 2 :" + this.internalDBConnectorService.contains("entityStoreTestbase", 2, Employee.class));

			this.internalDBConnectorService.closeEntityStore("entityStoreTestbase");
		} catch (final DatabaseException e) {
			this.utilsService.getLogger(InternalDBConnectorTestBean.class).error("Error ", e);
		} catch (InternalDBConnectorException e) {
			this.utilsService.getLogger(InternalDBConnectorTestBean.class).error("Error ", e);
		} finally {
			this.internalDBConnectorService.closeEnv("/var/testdb");
		}

	}

}
