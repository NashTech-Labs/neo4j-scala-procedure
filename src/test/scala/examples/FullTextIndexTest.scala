package examples

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.{Rule, Test}
import org.neo4j.driver.v1._
import org.neo4j.harness.junit.Neo4jRule


class FullTextIndexTest {
  @Rule
  var neo4j: Neo4jRule = new Neo4jRule().withProcedure(classOf[FullTextIndex])

  @Test
  def shouldAllowIndexingAndFindingANode {
    val driver: Driver = GraphDatabase.driver(neo4j.boltURI, Config.build.withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig)
    try {
      val session: Session = driver.session
      val nodeId: Long = session.run("CREATE (p:User {name:'Brookreson'}) RETURN id(p)").single.get(0).asLong
      val result: StatementResult = session.run("CALL example.search('User', 'name:Brook*')")
      assertThat(result.single.get("nodeId").asLong, equalTo(nodeId))
    } finally {
      if (driver != null) driver.close()
    }
  }

}
