package org.tc.osgi.bundle.external.db.connector.module.activator;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Values;
import org.osgi.framework.BundleContext;
import org.tc.osgi.bundle.external.db.connector.interf.module.service.INeo4jConnectorService;
import org.tc.osgi.bundle.external.db.connector.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.external.db.connector.module.service.PropertyServiceProxy;
import org.tc.osgi.bundle.external.db.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.external.db.connector.module.service.impl.Neo4jConnectorServiceImpl;
import org.tc.osgi.bundle.utils.interf.module.exception.TcOsgiException;
import org.tc.osgi.bundle.utils.interf.module.service.ILoggerUtilsService;
import org.tc.osgi.bundle.utils.interf.module.service.IPropertyUtilsService;
import org.tc.osgi.bundle.utils.interf.module.service.IUtilsService;
import org.tc.osgi.bundle.utils.interf.module.utils.AbstractTcOsgiActivator;
import org.tc.osgi.bundle.utils.interf.module.utils.TcOsgiProxy;

/**
 * Activator.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class ExternalDBConnectorActivator extends AbstractTcOsgiActivator {

	private TcOsgiProxy<ILoggerUtilsService> iLoggerUtilsService;
	private TcOsgiProxy<IPropertyUtilsService> iPropertyUtilsService;
	private TcOsgiProxy<IUtilsService> iUtilsService;

	private void connect() {

		final Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", ""));

		final Session session = driver.session();
		LoggerServiceProxy.getInstance().getLogger(ExternalDBConnectorActivator.class).debug("DB is connect");
		session.run("CREATE (a:Person {name: {name}, title: {title}})", Values.parameters("name", "Arthur", "title", "King"));

		final StatementResult result = session.run("MATCH (a:Person) WHERE a.name = {name} " + "RETURN a.name AS name, a.title AS title", Values.parameters(
			"name", "Arthur"));
		while (result.hasNext()) {
			final Record record = result.next();
			LoggerServiceProxy.getInstance().getLogger(ExternalDBConnectorActivator.class).debug(
				record.get("title").asString() + " " + record.get("name").asString());
		}

		session.close();
		driver.close();
	}

	@Override
	protected void checkInitBundleUtilsService(BundleContext context) throws TcOsgiException {
		throw new TcOsgiException("checkInitBundleUtilsService not implemented");

	}

	@Override
	protected void initProxys(BundleContext context) throws TcOsgiException {
		this.iPropertyUtilsService = new TcOsgiProxy<IPropertyUtilsService>(context, IPropertyUtilsService.class);
		PropertyServiceProxy.getInstance().setService(this.iPropertyUtilsService.getInstance());
		this.iLoggerUtilsService = new TcOsgiProxy<ILoggerUtilsService>(context, ILoggerUtilsService.class);
		LoggerServiceProxy.getInstance().setService(this.iLoggerUtilsService.getInstance());
		this.iUtilsService = new TcOsgiProxy<IUtilsService>(context, IUtilsService.class);
		UtilsServiceProxy.getInstance().setService(this.iUtilsService.getInstance());

	}

	@Override
	protected void initServices(BundleContext context) throws TcOsgiException {
		this.getIBundleUtilsService().getInstance().registerService(INeo4jConnectorService.class, new Neo4jConnectorServiceImpl(), context, this);

	}

	@Override
	protected void detachProxys(BundleContext context) throws TcOsgiException {
		this.iLoggerUtilsService.close();
		this.iPropertyUtilsService.close();
		this.iUtilsService.close();

	}

	@Override
	protected void detachServices(BundleContext context) throws TcOsgiException {
		this.getIBundleUtilsService().getInstance().unregister(INeo4jConnectorService.class, this);

	}

	@Override
	protected void beforeStart(BundleContext context) throws TcOsgiException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void beforeStop(BundleContext context) throws TcOsgiException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void afterStart(BundleContext context) throws TcOsgiException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void afterStop(BundleContext context) throws TcOsgiException {
		// TODO Auto-generated method stub

	}

}
