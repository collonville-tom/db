package org.tc.osgi.bundle.external.db.connector.neo4j.m2;

import java.util.ArrayList;
import java.util.List;

import org.tc.osgi.bundle.external.db.connector.interf.neo4j.m2.PropertyMap;
import org.tc.osgi.bundle.external.db.connector.neo4j.observer.Neo4jObserver;
import org.tc.osgi.bundle.external.db.connector.neo4j.observer.event.Neo4jAddNode;
import org.tc.osgi.bundle.external.db.connector.neo4j.observer.event.Neo4jAddRelation;


public class Graph extends Node {

	private List<Node> nodes=new ArrayList<Node>();
	private List<Relation> relations=new ArrayList<Relation>();
	public static final String GRAPH_RELATION="GRAPH";
	
	

	public Graph(String name,Neo4jObserver objserver) {
		super(name,"Graph");
		this.addObserver(objserver);
		this.addNode(this);
	}
	
	
	public boolean addNode(Node n) 
	{
		if(!this.nodes.contains(n))
		{	
			boolean tmp= this.nodes.add(n);
			if(tmp)
			{
				StringBuilder relationName=new StringBuilder(this.getProperties().get(PropertyMap.ID)).append("_").append(this.getRelations().size());
				this.addRelation(new Relation(relationName.toString(),GRAPH_RELATION,this,n));
				this.notifyObservers(new Neo4jAddNode(n));
			}
			return tmp;
		}
		return false;
	}
	
	
	
	public boolean addRelation(Relation r)
	{
		if(!this.relations.contains(r))
		{	
			boolean tmp= this.relations.add(r);
			if(tmp)
			{
				this.notifyObservers(new Neo4jAddRelation(r));
			}
			return tmp;
		}
		return false;
	}


	public List<Node> getNodes() {
		return nodes;
	}


	public List<Relation> getRelations() {
		return relations;
	}
	

	


}
