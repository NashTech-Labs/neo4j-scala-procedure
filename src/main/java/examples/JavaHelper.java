package examples;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.logging.Log;
import org.neo4j.procedure.Context;

/**
 * This is an example showing how you could expose Neo4j's full text indexes as
 * two procedures - one for updating indexes, and one for querying by label and
 * the lucene query language.
 */
public class JavaHelper {

    @Context
    public GraphDatabaseService db;

    @Context
    public Log log;

    public static class SearchHit {
        public long nodeId;

        public SearchHit(Node node) {
            this.nodeId = node.getId();
        }
    }

}