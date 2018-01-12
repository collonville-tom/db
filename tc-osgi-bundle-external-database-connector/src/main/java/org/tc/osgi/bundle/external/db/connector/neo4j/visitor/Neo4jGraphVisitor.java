package org.tc.osgi.bundle.external.db.connector.neo4j.visitor;

import java.util.Iterator;

import org.tc.osgi.bundle.external.db.connector.neo4j.m2.Graph;
import org.tc.osgi.bundle.external.db.connector.neo4j.m2.Node;
import org.tc.osgi.bundle.external.db.connector.neo4j.m2.Relation;


public class Neo4jGraphVisitor extends Neo4jNodeVisitor{

	private StringBuilder builder=new StringBuilder();
	
	public String getBuilderResult() {
		return builder.toString();
	}
	
	@Override
	public void visit(Node o) {
		if(o instanceof Graph)
		{
			this.visitNodes((Graph)o);
			builder.append(",");
			this.visitRelations((Graph)o);
		}
	}

	private void visitNodes(Graph o) {
		if (!o.getNodes().isEmpty()) {
			Iterator<Node> it= o.getNodes().iterator();
			boolean hasNext=true;
			for (;hasNext;) {
				builder.append(this.visitNode(it.next()));
				hasNext=it.hasNext();
				if(hasNext)
				{
					builder.append(",");
				}
			}
			
		}
	}
	private void visitRelations(Graph o) {
		if (!o.getRelations().isEmpty()) {
			Iterator<Relation> it= o.getRelations().iterator();
			boolean hasNext=true;
			for (;hasNext;) {
				builder.append(this.visitRelation(it.next()));
				hasNext=it.hasNext();
				if(hasNext)
				{
					builder.append(",");
				}
			}
			
		}
	}
	

	private String visitNode(Node p) {
		Neo4jNodeVisitor v = new Neo4jNodeVisitor();
		p.accept(v);
		return v.getBuilderResult().toString();
	}
	
	private String visitRelation(Relation p) {
		Neo4jRelationVisitor v = new Neo4jRelationVisitor();
		p.accept(v);
		return v.getBuilderResult().toString();
	}

}
