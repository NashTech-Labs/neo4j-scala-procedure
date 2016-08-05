package examples

import java.util.stream.Stream

import examples.JavaHelper.SearchHit
import org.neo4j.graphdb.Node
import org.neo4j.procedure.{Name, PerformsWrites, Procedure}

class FullTextIndex extends JavaHelper {
  @Procedure("example.search")
  @PerformsWrites
  def search(@Name("label") label: String, @Name("query") query: String): Stream[SearchHit] = {
    val index: String = indexName(label)
    if (!db.index.existsForNodes(index)) {
      log.debug("Skipping index query since index does not exist: `%s`", index)
      Stream.empty
    }
    val nodes: Stream[Node] = db.index.forNodes(index).query(query).stream

    val newFunction: java.util.function.Function[Node, SearchHit] = (node: Node) => new SearchHit(node)
    nodes.map {
      newFunction
    }
  }

  private def indexName(label: String): String = {
    "label-" + label
  }
}

