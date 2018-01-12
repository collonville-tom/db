package org.tc.osgi.bundle.external.db.connector.neo4j.observer.event;

import org.neo4j.driver.v1.Transaction;
import org.tc.osgi.bundle.external.db.connector.interf.neo4j.m2.AbstractLinkElement;
import org.tc.osgi.bundle.external.db.connector.interf.neo4j.observer.AbstractNeo4jEvent;
import org.tc.osgi.bundle.external.db.connector.neo4j.m2.Relation;

public class Neo4jAddRelation extends AbstractNeo4jEvent {

	private Relation n;

	public Neo4jAddRelation(Relation n) {
		this.n = n;
	}

	@Override
	public String processResquest(AbstractLinkElement g, Transaction tx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String preprocessRequest(AbstractLinkElement g, Transaction tx) {
		// TODO Auto-generated method stub
		return null;
	}

}
