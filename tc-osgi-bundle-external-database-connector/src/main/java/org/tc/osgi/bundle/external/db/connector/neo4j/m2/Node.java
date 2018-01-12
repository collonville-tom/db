package org.tc.osgi.bundle.external.db.connector.neo4j.m2;


import org.tc.osgi.bundle.external.db.connector.interf.neo4j.m2.AbstractLinkElement;
import org.tc.osgi.bundle.external.db.connector.neo4j.visitor.Neo4jNodeVisitor;


public class Node extends AbstractLinkElement<Neo4jNodeVisitor>{


	public Node(String nodeName,String type)
	{
		super(nodeName,type);
	}
	

	@Override
	public void accept(Neo4jNodeVisitor visitor) {
		visitor.visit(this);
	}
	
}
