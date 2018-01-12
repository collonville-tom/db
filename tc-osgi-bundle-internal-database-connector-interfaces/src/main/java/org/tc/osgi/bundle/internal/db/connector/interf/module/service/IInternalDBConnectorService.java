package org.tc.osgi.bundle.internal.db.connector.interf.module.service;



import java.io.UnsupportedEncodingException;

import org.tc.osgi.bundle.internal.db.connector.interf.berkeley.exception.InternalDBConnectorException;

import com.sleepycat.je.DatabaseException;

public interface IInternalDBConnectorService {

    public void buildDataBase(String envPath, String dataBase) throws DatabaseException;

    public void buildEntityStore(String envPath, String dataBase) throws DatabaseException;

    public void closeDB(String database) throws DatabaseException ;

    public void closeEntityStore(String database) throws DatabaseException ;

    public void closeEnv(String env) throws DatabaseException ;

    public void deleteInDataBase(String database, String _key) throws DatabaseException, UnsupportedEncodingException;

    public void deleteInEntityStore(final String entityStore, final Object _value) throws DatabaseException, InternalDBConnectorException;

    public <V> V get(String entityStore, Integer id, Class<V> c) throws DatabaseException;
    
    public <V> V get(final String entityStore, final Object instance, final Class<V> c) throws DatabaseException, InternalDBConnectorException;
    
    public String get(final String database, final String _key) throws DatabaseException, UnsupportedEncodingException, InternalDBConnectorException  ;

    public void push(String database, String _key, String _value) throws DatabaseException, UnsupportedEncodingException;

    public <V> void putNoReturn(String entityStore, V object) throws DatabaseException;
    
    public boolean contains(final String database, final String _key) throws UnsupportedEncodingException;
    
    public <V> boolean contains(final String entityStore, final Object instance, final Class<V> c) throws DatabaseException, InternalDBConnectorException ;
    
    public <V> boolean contains(final String entityStore, final Integer primary, final Class<V> c) throws DatabaseException ;


}
