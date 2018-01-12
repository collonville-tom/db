package org.tc.osgi.bundle.external.db.connector.interf.neo4j.visitor;

import java.util.Map;

import org.tc.osgi.bundle.utils.interf.pattern.visitor.IVisitor;

public abstract class AbstractNeo4jVisitor<T extends INeo4jVisitable> implements IVisitor<T> {

	private StringBuilder builder = new StringBuilder();

	
	
	protected abstract void visitProperties(final T o);
	
	
	
	
	
	
	
	public String getBuilderResult() {
		return this.builder.toString();
	}
	
	
	protected StringBuilder getBuilder() {
		return this.builder;
	}

	protected String visitProperty(final String p, final Map<String,String> o) {
		StringBuilder b = new StringBuilder(p);
		b.append(":'");
		b.append(o.get(p));
		b.append("'");
		return b.toString();
	}

}
