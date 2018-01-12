package org.tc.osgi.bundle.internal.db.connector.module.service.impl;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

import org.tc.osgi.bundle.internal.db.connector.berkeley.factory.DatabaseFactory;
import org.tc.osgi.bundle.internal.db.connector.berkeley.factory.EntityStoreFactory;
import org.tc.osgi.bundle.internal.db.connector.berkeley.factory.EnvironmentFactory;
import org.tc.osgi.bundle.internal.db.connector.interf.berkeley.exception.InternalDBConnectorException;
import org.tc.osgi.bundle.internal.db.connector.interf.module.service.IInternalDBConnectorService;
import org.tc.osgi.bundle.internal.db.connector.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.internal.db.connector.module.service.UtilsServiceProxy;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.LockMode;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;

public class InternalDBConnectorServiceImpl implements IInternalDBConnectorService {

	private final DatabaseFactory dataFact = new DatabaseFactory();
	private final EnvironmentFactory envFact = new EnvironmentFactory();
	private final EntityStoreFactory storeFact = new EntityStoreFactory();

	public InternalDBConnectorServiceImpl() {
	}

	@Override
	public void buildDataBase(final String envPath, final String dataBase) throws DatabaseException {
		final Environment env = this.envFact.get(envPath);
		final Database db = this.dataFact.getDatabase(dataBase, env);
		LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Creation/utilisation de l'environnement " + envPath);
		LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Creation de la base de donnée " + dataBase);
	}

	@Override
	public void buildEntityStore(final String envPath, final String dataBase) throws DatabaseException {
		final Environment env = this.envFact.get(envPath);
		final EntityStore es = this.storeFact.getEntityStore(dataBase, env);
		LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Creation/utilisation de l'environnement " + envPath);
		LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Creation de la base de donnée " + dataBase);
	}

	@Override
	public void closeDB(final String database) throws DatabaseException {
		LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Fermeture de la base de donnée " + database);
		final Database db = this.dataFact.get(database);
		db.close();

	}

	@Override
	public void closeEntityStore(final String database) throws DatabaseException {
		LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Fermeture de la base de donnée " + database);
		final EntityStore es = this.storeFact.get(database);
		es.close();
	}

	@Override
	public void closeEnv(final String env) throws DatabaseException {
		LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Fermeture de l'environnement " + env);
		final Environment environ = this.envFact.get(env);

		// LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Liste de BD");
		// for(Database db:this.dataFact.values())
		// {
		// LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug(db.getDatabaseName());
		//
		// // if(environ.getDatabaseNames().contains(db.getDatabaseName()))
		// db.close();
		// }
		// LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Liste de ES");
		// for(EntityStore db:this.storeFact.values())
		// {
		// LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug(db.getStoreName());
		// // if(environ.getDatabaseNames().contains(db.getStoreName()))
		// db.close();
		// }
		//
		// LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Liste des DB connu de l'env");
		//
		// final Iterator it = environ.getDatabaseNames().iterator();
		// for (; it.hasNext();) {
		// final Object o = it.next();
		// LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug(o.toString());
		// LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug(o.getClass());
		// if (o instanceof String) {
		// final String database = (String) o;
		// final Database db = this.dataFact.get(database);
		// final EntityStore es = this.storeFact.get(database);
		// if (es != null) {
		// es.close();
		// }
		// if (db != null) {
		// db.close();
		// }
		// }
		// }
		environ.close();
	}

	@Override
	public void deleteInDataBase(final String database, final String _key) throws DatabaseException, UnsupportedEncodingException {
		LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Suppresion " + _key + " de la base " + database);
		final Database db = this.dataFact.get(database);
		final Environment env = db.getEnvironment();
		final DatabaseEntry key = new DatabaseEntry(_key.getBytes("UTF-8"));
		db.delete(null, key);
	}

	private Integer getPrimaryKey(Object _value) throws InternalDBConnectorException {
		try {
			Field[] f = _value.getClass().getDeclaredFields();
			for (int i = 0; i < f.length; i++) {
				if (UtilsServiceProxy.getInstance().contains(f[i].getAnnotations(), PrimaryKey.class)) {
					if (!f[i].isAccessible()) {
						f[i].setAccessible(true);
					}
					return (Integer) f[i].get(_value);
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).error("Erreur de suppression de l'object", e);
			throw new InternalDBConnectorException("Impossible d'acceder a la valeur de la clef primaire", e);
		}
		throw new InternalDBConnectorException("type " + _value.getClass() + " ne contient pas de clef primaire");

	}

	@Override
	public void deleteInEntityStore(final String entityStore, final Object _value) throws DatabaseException, InternalDBConnectorException {
		final EntityStore es = this.storeFact.get(entityStore);
		final PrimaryIndex<Integer, ?> primaryIndex = es.getPrimaryIndex(Integer.class, _value.getClass());

		if (UtilsServiceProxy.getInstance().contains(_value.getClass().getAnnotations(), Entity.class)) {
			LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Suppresion " + _value + " de la base " + entityStore);
			primaryIndex.delete(this.getPrimaryKey(_value));
		}
	}

	@Override
	public <V> V get(final String entityStore, final Integer id, final Class<V> c) throws DatabaseException {
		final EntityStore es = this.storeFact.get(entityStore);
		final PrimaryIndex<Integer, V> primaryIndex = es.getPrimaryIndex(Integer.class, c);
		final V v = primaryIndex.get(null, id, LockMode.DEFAULT);
		LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Extraction données :" + v);
		return v;
	}

	@Override
	public <V> V get(final String entityStore, final Object instance, final Class<V> c) throws DatabaseException, InternalDBConnectorException {
		final EntityStore es = this.storeFact.get(entityStore);
		final PrimaryIndex<Integer, V> primaryIndex = es.getPrimaryIndex(Integer.class, c);
		if (UtilsServiceProxy.getInstance().contains(instance.getClass().getAnnotations(), Entity.class)) {
			LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Extraction données :" + instance);
			return primaryIndex.get(this.getPrimaryKey(instance));
		}
		throw new InternalDBConnectorException("type " + instance.getClass() + " n'est pas storable");
	}

	@Override
	public <V> boolean contains(final String entityStore, final Object instance, final Class<V> c) throws DatabaseException, InternalDBConnectorException {
		final EntityStore es = this.storeFact.get(entityStore);
		final PrimaryIndex<Integer, V> primaryIndex = es.getPrimaryIndex(Integer.class, c);
		if (UtilsServiceProxy.getInstance().contains(instance.getClass().getAnnotations(), Entity.class)) {
			return primaryIndex.contains(this.getPrimaryKey(instance));
		}
		throw new InternalDBConnectorException("Le type " + instance.getClass() + " n'est pas storable");
	}

	@Override
	public <V> boolean contains(final String entityStore, final Integer primary, final Class<V> c) throws DatabaseException {
		final EntityStore es = this.storeFact.get(entityStore);
		final PrimaryIndex<Integer, V> primaryIndex = es.getPrimaryIndex(Integer.class, c);
		return primaryIndex.contains(primary);

	}

	@Override
	public String get(final String database, final String _key) throws DatabaseException, UnsupportedEncodingException, InternalDBConnectorException {
		final Database db = this.dataFact.get(database);
		final Environment env = db.getEnvironment();
		final DatabaseEntry key = new DatabaseEntry(_key.getBytes("UTF-8"));
		final DatabaseEntry searchEntry = new DatabaseEntry();
		db.get(null, key, searchEntry, LockMode.DEFAULT);
		if (searchEntry.getData() == null)
			throw new InternalDBConnectorException("Data for " + _key + " not found");
		String found = new String(searchEntry.getData());
		LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Extraction données :" + _key + "," + found);
		return found;
	}

	@Override
	public boolean contains(final String database, final String _key) throws UnsupportedEncodingException {
		final Database db = this.dataFact.get(database);
		final Environment env = db.getEnvironment();
		final DatabaseEntry key = new DatabaseEntry(_key.getBytes("UTF-8"));
		final DatabaseEntry searchEntry = new DatabaseEntry();
		db.get(null, key, searchEntry, LockMode.DEFAULT);

		if (searchEntry.getData() == null)
			return false;
		return true;
	}

	@Override
	public void push(final String database, final String _key, final String _value) throws DatabaseException, UnsupportedEncodingException {
		final Database db = this.dataFact.get(database);
		final Environment env = db.getEnvironment();
		final DatabaseEntry key = new DatabaseEntry(_key.getBytes("UTF-8"));
		final DatabaseEntry data = new DatabaseEntry(_value.getBytes("UTF-8"));
		LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Insersion données :" + _key + "," + _value);
		db.put(null, key, data);

	}

	@Override
	public <V> void putNoReturn(final String entityStore, final V object) throws DatabaseException {
		final EntityStore es = this.storeFact.get(entityStore);
		final PrimaryIndex<Integer, V> primaryIndex = (PrimaryIndex<Integer, V>) es.getPrimaryIndex(Integer.class, object.getClass());
		LoggerServiceProxy.getInstance().getLogger(InternalDBConnectorServiceImpl.class).debug("Insersion données :" + object);
		primaryIndex.putNoReturn(object);
	}

}
