package org.tc.osgi.bundle.external.db.connector.interf.neo4j.m2;

import java.util.Map;

import org.tc.osgi.bundle.external.db.connector.interf.neo4j.visitor.AbstractNeo4jVisitor;
import org.tc.osgi.bundle.external.db.connector.interf.neo4j.visitor.INeo4jVisitable;
import org.tc.osgi.bundle.utils.interf.pattern.observer.AbstractSubject;



public abstract class AbstractLinkElement<T extends AbstractNeo4jVisitor> extends AbstractSubject implements INeo4jVisitable<T>{

	private String type;
	
	private Map<String,String> properties=new PropertyMap();

	public AbstractLinkElement(String nodeName,String type)
	{
		this.type=type;
		this.properties.put(PropertyMap.ID, nodeName);
	}
	
	public Map<String,String> getProperties() {
		return properties;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getId()
	{
		return this.properties.get(PropertyMap.ID);
	}
}
