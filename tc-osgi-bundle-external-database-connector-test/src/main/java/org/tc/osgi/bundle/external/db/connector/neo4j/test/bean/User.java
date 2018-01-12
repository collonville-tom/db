package org.tc.osgi.bundle.external.db.connector.neo4j.test.bean;

import java.util.List;

import org.tc.osgi.bundle.external.db.connector.interf.neo4j.annot.Neo4jNode;
import org.tc.osgi.bundle.external.db.connector.interf.neo4j.annot.Neo4jPrimary;
import org.tc.osgi.bundle.external.db.connector.interf.neo4j.annot.Neo4jRelation;

@Neo4jNode
public class User {

	@Neo4jPrimary
	private String name;
	
	@Neo4jRelation(name="FRIEND")
	private List<User> friends;
	
	
	
	public User(String name)
	{
		this.name=name;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> elements) {
		this.friends = elements;
	}
}
