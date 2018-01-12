package org.tc.osgi.bundle.external.db.connector.interf.neo4j.observer;

import org.neo4j.driver.v1.Transaction;
import org.tc.osgi.bundle.external.db.connector.interf.neo4j.m2.AbstractLinkElement;
import org.tc.osgi.bundle.utils.interf.pattern.observer.IObserverEvent;

public abstract class AbstractNeo4jEvent implements IObserverEvent {

	
	public abstract String processResquest(AbstractLinkElement g,Transaction tx);
	
	public abstract String preprocessRequest(AbstractLinkElement g,Transaction tx);
	
}
