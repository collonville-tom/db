package org.tc.osgi.bundle.internal.db.connector.berkeley.factory;

import java.util.HashMap;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;

public class DatabaseFactory extends HashMap<String, Database> {

	
	
    /**
     * long serialVersionUID.
     */
    private static final long serialVersionUID = 4051361114136102789L;

    public Database getDatabase(final String databaseName, final Environment env) throws DatabaseException {
        if (!containsKey(databaseName)) {
            final Database database = env.openDatabase(null, databaseName, getDefaultDatabaseConfig());
            put(databaseName, database);
        }
        return get(databaseName);
    }

    public DatabaseConfig getDefaultDatabaseConfig() {
        final DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setAllowCreate(true);
        databaseConfig.setSortedDuplicates(false);
        return databaseConfig;
    }

}
