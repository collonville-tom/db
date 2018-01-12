package org.tc.osgi.bundle.external.db.connector.conf;

import org.tc.osgi.bundle.utils.interf.conf.AbstractPropertyFile;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;

/**
 * Neo4jConnectorPropertyFile.java.
 * @author collonville thomas
 * @version 0.0.1
 */
public final class ExternalDBConnectorPropertyFile extends AbstractPropertyFile {
    /**
     * String BUNDLE_RACINE.
     */
    public final static String BUNDLE_RACINE = "tc.osgi.bundle.external.db.";

    /**
     * DefaultConfig conf.
     */
    private static ExternalDBConnectorPropertyFile instance = null;

    /**
     * String EQUINOXLOADERFILE.
     */
    public static final String Neo4jConnector_FILE = "external.db-connector";

    /**
     * getInstance.
     * @return DefaultConfig
     * @throws EquinoxConfigException
     * @throws FieldTrackingAssignementException
     */
    public static ExternalDBConnectorPropertyFile getInstance() {
        if (ExternalDBConnectorPropertyFile.instance == null) {
            ExternalDBConnectorPropertyFile.instance = new ExternalDBConnectorPropertyFile();
        }
        return ExternalDBConnectorPropertyFile.instance;
    }

    /**
     * AptConfiguration constructor.
     */
    private ExternalDBConnectorPropertyFile() {
        super(ExternalDBConnectorPropertyFile.Neo4jConnector_FILE, ExternalDBConnectorPropertyFile.class.getClassLoader());
    }

    /**
     * @return String
     * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getBundleRacine()
     */
    @Override
    public String getBundleRacine() {
        return ExternalDBConnectorPropertyFile.BUNDLE_RACINE;
    }

    /**
     * @return String
     * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getConfFile()
     */
    @Override
    public String getConfFile() {
        return ExternalDBConnectorPropertyFile.Neo4jConnector_FILE;
    }

    /**
     * @return String
     * @see org.tc.osgi.bundle.utils.conf.AbstractPropertyFile#getXMLFile()
     */
    @Override
    public String getXMLFile() {
        return ExternalDBConnectorPropertyFile.getInstance().getConfigDirectory() + getConfFile();
    }

}
