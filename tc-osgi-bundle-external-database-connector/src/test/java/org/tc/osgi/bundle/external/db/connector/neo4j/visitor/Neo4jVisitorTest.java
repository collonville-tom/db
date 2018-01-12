package org.tc.osgi.bundle.external.db.connector.neo4j.visitor;


public class Neo4jVisitorTest {

    // @Test
    // public void testGraph() {
    //
    // final Node a = new Node("a", "A");
    // final Node b = new Node("b", "B");
    // final Node c = new Node("c", "C");
    //
    // final Relation r1 = new Relation("1","R", a, b);
    // final Relation r2 = new Relation("2","R", a, c);
    // final Relation r3 = new Relation("3","O", a, c);
    //
    // a.getProperties().put("tmp", "val");
    // b.getProperties().put("tmp2", "val");
    // c.getProperties().put("tmp3", "val");
    //
    // Graph g = new Graph("test",null);
    // Graph g2 = new Graph("test2",null);
    // g.addNode(a);
    // g2.addNode(a);
    // g.addNode(b);
    // g2.addNode(b);
    // g.addNode(c);
    //
    // g.getRelations().add(r1);
    // g2.getRelations().add(r1);
    // g.getRelations().add(r2);
    // g.getRelations().add(r3);
    //
    // Neo4jGraphVisitor visitor = new Neo4jGraphVisitor();
    // g.accept(visitor);
    //
    // Neo4jGraphVisitor visitor2 = new Neo4jGraphVisitor();
    // g2.accept(visitor2);
    //
    // System.out.println(visitor.getBuilderResult());
    // Assert.assertEquals("(test:Graph {ID:'test'}),(a:A {ID:'a'}, {tmp:'val'}),(b:B {tmp2:'val'}, {ID:'b'}),(c:C {tmp3:'val'}, {ID:'c'}),(test)-[:GRAPH {ID:'test_0'}]->(test),(test)-[:GRAPH {ID:'test_1'}]->(a),(test)-[:GRAPH {ID:'test_2'}]->(b),(test)-[:GRAPH {ID:'test_3'}]->(c),(a)-[:R {ID:'1'}]->(b),(a)-[:R {ID:'2'}]->(c),(a)-[:O {ID:'3'}]->(c)",visitor.getBuilderResult());
    //
    //
    // }
    //
    // @Test
    // public void testNode() {
    //
    // final Node n = new Node("a", "A");
    //
    // final Neo4jNodeVisitor visitor = new Neo4jNodeVisitor();
    // n.accept(visitor);
    // System.out.println(visitor.getBuilderResult());
    // Assert.assertEquals("(a:A {ID:'a'})", visitor.getBuilderResult());
    //
    // final Neo4jNodeVisitor visitor2 = new Neo4jNodeVisitor();
    // n.getProperties().put("tmp", "val");
    // n.getProperties().put("tmp2", "val");
    // n.getProperties().put("tmp3", "val");
    //
    // n.accept(visitor2);
    // System.out.println(visitor2.getBuilderResult());
    // Assert.assertEquals("(a:A {tmp2:'val'}, {tmp3:'val'}, {ID:'a'}, {tmp:'val'})",
    // visitor2.getBuilderResult());
    //
    // }
    //
    // @Test
    // public void testRelation() {
    //
    // final Relation r = new Relation("1","R");
    // r.setSource(new Node("a", "A"));
    // r.setTarget(new Node("b", "B"));
    //
    // final Neo4jRelationVisitor visitor = new Neo4jRelationVisitor();
    //
    // r.accept(visitor);
    // System.out.println(visitor.getBuilderResult());
    // Assert.assertEquals("(a)-[:R {ID:'1'}]->(b)",
    // visitor.getBuilderResult());
    //
    // }

}
