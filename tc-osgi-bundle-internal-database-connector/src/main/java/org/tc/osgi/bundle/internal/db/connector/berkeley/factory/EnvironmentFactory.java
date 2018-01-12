package org.tc.osgi.bundle.internal.db.connector.berkeley.factory;

import java.io.File;
import java.util.HashMap;

import org.tc.osgi.bundle.internal.db.connector.conf.InternalDBConnectorPropertyFile;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.TransactionConfig;

public class EnvironmentFactory extends HashMap<String, Environment> {

	/**
	 * long serialVersionUID.
	 */
	private static final long serialVersionUID = 6112733940289397987L;

	public Environment get(final String envPath) throws DatabaseException {
		if (!containsKey(envPath)) {
			final Environment env = new Environment(new File(envPath), getDefaultEnvironmentConfig());
			this.put(envPath, env);
		}
		return super.get(envPath);
	}

	public EnvironmentConfig getDefaultEnvironmentConfig() {
		final EnvironmentConfig environmentConfig = new EnvironmentConfig();
		environmentConfig.setAllowCreate(true);
		environmentConfig.setTransactional(true);
		environmentConfig.setCachePercent(InternalDBConnectorPropertyFile.getInstance().getCachePercent());
		return environmentConfig;
	}

	public TransactionConfig getDefaultTransactionConfig() {
		final TransactionConfig transactionConfig = new TransactionConfig();
		transactionConfig.setReadCommitted(true);
		return transactionConfig;
	}

}
