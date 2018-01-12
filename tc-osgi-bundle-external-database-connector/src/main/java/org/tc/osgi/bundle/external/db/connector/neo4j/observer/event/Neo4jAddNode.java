package org.tc.osgi.bundle.external.db.connector.neo4j.observer.event;

import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.tc.osgi.bundle.external.db.connector.interf.neo4j.m2.AbstractLinkElement;
import org.tc.osgi.bundle.external.db.connector.interf.neo4j.observer.AbstractNeo4jEvent;
import org.tc.osgi.bundle.external.db.connector.neo4j.m2.Node;
import org.tc.osgi.bundle.external.db.connector.neo4j.visitor.Neo4jNodeVisitor;



public class Neo4jAddNode extends AbstractNeo4jEvent{

	private Node n;
	
	public Neo4jAddNode(Node n) {
		this.n=n;
	}



	@Override
	public String processResquest(AbstractLinkElement g, Transaction tx) {
		preprocessRequest(g,tx);
		
		
		return null;
	}




	@Override
	public String preprocessRequest(AbstractLinkElement g, Transaction tx) {
		Neo4jNodeVisitor visitor=new Neo4jNodeVisitor();
		this.n.accept(visitor);
		
		
		StringBuilder b=new StringBuilder("MATCH ");
		b.append(visitor.getBuilderResult());
		b.append("RETURN (");
		b.append(this.n.getId());
		b.append(")");
		System.out.println(b.toString());
		StatementResult result=tx.run(b.toString());
		tx.success();
		System.out.println(result.toString());
		
		
		
		return null;
	}




	
	
	
}
