package org.tc.osgi.bundle.internal.db.connector.module.activator;

import org.osgi.framework.BundleContext;
import org.tc.osgi.bundle.internal.db.connector.interf.module.service.IInternalDBConnectorService;
import org.tc.osgi.bundle.internal.db.connector.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.internal.db.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.internal.db.connector.module.service.impl.InternalDBConnectorServiceImpl;
import org.tc.osgi.bundle.utils.interf.exception.TcOsgiException;
import org.tc.osgi.bundle.utils.interf.module.service.ILoggerUtilsService;
import org.tc.osgi.bundle.utils.interf.module.service.IUtilsService;
import org.tc.osgi.bundle.utils.interf.module.utils.AbstractTcOsgiActivator;
import org.tc.osgi.bundle.utils.interf.module.utils.TcOsgiProxy;

/**
 * Activator.java.
 *
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class InternalDBConnectorActivator extends AbstractTcOsgiActivator {

	private TcOsgiProxy<ILoggerUtilsService> iLoggerUtilsService;
	private TcOsgiProxy<IUtilsService> iUtilsService;

	@Override
	protected void checkInitBundleUtilsService(BundleContext context) throws TcOsgiException {
		throw new TcOsgiException("checkInitBundleUtilsService not implemented");

	}

	@Override
	protected void initProxys(BundleContext context) throws TcOsgiException {
		this.iLoggerUtilsService = new TcOsgiProxy<ILoggerUtilsService>(context, ILoggerUtilsService.class);
		LoggerServiceProxy.getInstance().setService(this.iLoggerUtilsService.getInstance());
		this.iUtilsService = new TcOsgiProxy<IUtilsService>(context, IUtilsService.class);
		UtilsServiceProxy.getInstance().setService(this.iUtilsService.getInstance());

	}

	@Override
	protected void initServices(BundleContext context) throws TcOsgiException {
		this.getIBundleUtilsService().getInstance().registerService(IInternalDBConnectorService.class, new InternalDBConnectorServiceImpl(), context, this);

	}

	@Override
	protected void detachProxys(BundleContext context) throws TcOsgiException {
		this.iLoggerUtilsService.close();
		this.iUtilsService.close();

	}

	@Override
	protected void detachServices(BundleContext context) throws TcOsgiException {
		this.getIBundleUtilsService().getInstance().unregister(IInternalDBConnectorService.class, this);

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
