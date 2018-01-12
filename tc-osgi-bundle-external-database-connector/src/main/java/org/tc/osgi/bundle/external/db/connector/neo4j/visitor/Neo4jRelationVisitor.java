package org.tc.osgi.bundle.external.db.connector.neo4j.visitor;

import java.util.Iterator;

import org.tc.osgi.bundle.external.db.connector.interf.neo4j.visitor.AbstractNeo4jVisitor;
import org.tc.osgi.bundle.external.db.connector.neo4j.m2.Relation;

public class Neo4jRelationVisitor extends AbstractNeo4jVisitor<Relation> {



    @Override
    public void visit(final Relation o) {
        this.getBuilder().append("(");
        this.getBuilder().append(o.getSource().getId());
        this.getBuilder().append(")-[:");
        this.getBuilder().append(o.getType());
        this.visitProperties(o);
        this.getBuilder().append("]->(");
        this.getBuilder().append(o.getTarget().getId());
        this.getBuilder().append(")");
    }

    protected void visitProperties(final Relation o) {
        if (!o.getProperties().isEmpty()) {
            final Iterator<String> it = o.getProperties().keySet().iterator();
            boolean hasNext = true;
            for (; hasNext;) {
                this.getBuilder().append(" {");
                this.getBuilder().append(this.visitProperty(it.next(),o.getProperties()));
                this.getBuilder().append("}");
                hasNext = it.hasNext();
                if (hasNext) {
                    this.getBuilder().append(",");
                }
            }

        }
    }



 

}
