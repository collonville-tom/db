package org.tc.osgi.bundle.external.db.connector.neo4j.observer;

import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;
import org.tc.osgi.bundle.external.db.connector.interf.neo4j.observer.AbstractNeo4jEvent;
import org.tc.osgi.bundle.external.db.connector.neo4j.m2.Graph;
import org.tc.osgi.bundle.utils.interf.pattern.observer.IObserver;
import org.tc.osgi.bundle.utils.interf.pattern.observer.IObserverEvent;
import org.tc.osgi.bundle.utils.interf.pattern.observer.ISubject;


public class Neo4jObserver implements IObserver {
	
	public Session session;
	
	
	public Neo4jObserver(Session s) {
		this.session=s;
	}

	@Override
	public void update(ISubject _subject) {
		
		
	}

	@Override
	public void update(ISubject _subject, IObserverEvent _event) {
		
		if( _subject instanceof Graph && _event instanceof AbstractNeo4jEvent)
		{
			try (Transaction tx=session.beginTransaction())
			{
				((AbstractNeo4jEvent)_event).processResquest((Graph)_subject,tx);
			}
		}
		
	}
}
