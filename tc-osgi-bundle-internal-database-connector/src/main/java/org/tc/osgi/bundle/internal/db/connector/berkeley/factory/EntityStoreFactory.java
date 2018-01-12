package org.tc.osgi.bundle.internal.db.connector.berkeley.factory;

import java.util.HashMap;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.StoreConfig;

public class EntityStoreFactory extends HashMap<String, EntityStore> {

    /**
     * long serialVersionUID.
     */
    private static final long serialVersionUID = 555303510610737159L;

    public StoreConfig getDefaultEntityStoreConfig() {
        final StoreConfig storeConfig = new StoreConfig();
        storeConfig.setAllowCreate(true);
        return storeConfig;
    }

    public EntityStore getEntityStore(final String databaseName, final Environment env) throws DatabaseException {
        if (!containsKey(databaseName)) {
            final EntityStore store = new EntityStore(env, databaseName, getDefaultEntityStoreConfig());
            put(databaseName, store);
        }
        return get(databaseName);
    }

}
