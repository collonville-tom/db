package org.tc.osgi.bundle.external.db.connector.neo4j.m2;


import org.tc.osgi.bundle.external.db.connector.interf.neo4j.m2.AbstractLinkElement;
import org.tc.osgi.bundle.external.db.connector.neo4j.visitor.Neo4jRelationVisitor;


public class Relation extends AbstractLinkElement<Neo4jRelationVisitor>{

	
	private Node source,target;

	public Relation(String relationName,String typeName)
	{
		super(relationName,typeName);
	}
	
	public Relation(String relationName,String typeName,Node s, Node t) {
		super(relationName,typeName);
		this.source=s;
		this.target=t;
	}
	


	@Override
	public void accept(Neo4jRelationVisitor visitor) {
		visitor.visit(this);
		
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public Node getTarget() {
		return target;
	}

	public void setTarget(Node target) {
		this.target = target;
	}
	
	
}
