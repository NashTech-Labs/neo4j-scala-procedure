import sbtassembly.AssemblyPlugin.autoImport._

name := """procedure_template"""

version := "1.0"

val neo4j = "org.neo4j" % "neo4j" % "3.0.2"
val neo4j_driver = "org.neo4j.driver" % "neo4j-java-driver" % "1.0.4"
val neo4j_test = "org.neo4j.test" % "neo4j-harness" % "3.0.2"
val junit = "junit" % "junit" % "4.12"
val findBugs = "com.google.code.findbugs" % "jsr305" % "1.3.+"

assemblyMergeStrategy in assembly := {
  case m if m.toUpperCase.endsWith("META-INF/LICENSES.TXT") => MergeStrategy.filterDistinctLines
  case m if m.toUpperCase.matches("META-INF") => MergeStrategy.filterDistinctLines
  case m if m.toUpperCase.matches("META-INF/MANIFEST.MF") => MergeStrategy.filterDistinctLines
  case _ => MergeStrategy.filterDistinctLines
}

assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("org.neo4j.cypher.internal.compiler.v2_3.planner.**" -> "shadeio.@1", "org.mozilla.javascript.**" -> "shadeio.@1").inAll
)

lazy val commonSettings = Seq(
  organization := "org.com",
  version := "0.1.0",
  scalaVersion := "2.12.0-M5"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "procedure_template",
    assemblyJarName in assembly := "search.jar",
    libraryDependencies ++= Seq(neo4j, neo4j_driver, neo4j_test, junit, findBugs)
  )
