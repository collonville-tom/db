package org.tc.osgi.bundle.internal.db.connector.conf;

import org.tc.osgi.bundle.utils.interf.conf.AbstractPropertyFile;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;

/**
 * Neo4jConnectorPropertyFile.java.
 *
 * @author collonville thomas
 * @version 0.0.1
 */
public final class InternalDBConnectorPropertyFile extends AbstractPropertyFile {
	/**
	 * String BUNDLE_RACINE.
	 */
	public final static String BUNDLE_RACINE = "tc.osgi.bundle.internal-db.connector.";

	/**
	 * DefaultConfig conf.
	 */
	private static InternalDBConnectorPropertyFile instance = null;

	/**
	 * String EQUINOXLOADERFILE.
	 */
	public static final String InternalDBConnector_FILE = "internal-db.connector";

	/**
	 * getInstance.
	 *
	 * @return DefaultConfig
	 * @throws EquinoxConfigException
	 * @throws FieldTrackingAssignementException
	 */
	public static InternalDBConnectorPropertyFile getInstance() {
		if (InternalDBConnectorPropertyFile.instance == null) {
			InternalDBConnectorPropertyFile.instance = new InternalDBConnectorPropertyFile();
		}
		return InternalDBConnectorPropertyFile.instance;
	}

	/**
	 * AptConfiguration constructor.
	 */
	private InternalDBConnectorPropertyFile() {
		super(InternalDBConnectorPropertyFile.InternalDBConnector_FILE, InternalDBConnectorPropertyFile.class.getClassLoader());
	}

	/**
	 * @return String
	 * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getBundleRacine()
	 */
	@Override
	public String getBundleRacine() {
		return InternalDBConnectorPropertyFile.BUNDLE_RACINE;
	}

	/**
	 * @return String
	 * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getConfFile()
	 */
	@Override
	public String getConfFile() {
		return InternalDBConnectorPropertyFile.InternalDBConnector_FILE;
	}

	/**
	 * @return String
	 * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getXMLFile()
	 */
	@Override
	public String getXMLFile() {
		return InternalDBConnectorPropertyFile.getInstance().getConfigDirectory() + getConfFile();
	}

	private Integer cachePercent;

	public Integer getCachePercent() {
		if (cachePercent == null) {
			cachePercent = Integer.valueOf(getResourceBundle().getString(getBundleRacine() + "cache.percent"));
		}
		return cachePercent;
	}

	@Override
	public String getYamlFile() {
		// TODO Auto-generated method stub
		return InternalDBConnectorPropertyFile.getInstance().getConfigDirectory() + getConfFile();
	}

}
