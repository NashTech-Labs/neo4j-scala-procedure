# neo4j-scala-procedures

#Strating creating Neo4j Pocedure with Scala

This repository contains the basic starter app for creating Neo4j procedure With Scala

## Prerequisites:

Neo4j database must have been installed on your system and the password can be set in the Neo4j.scala file. If yopu have not downloaded it See the link [here](https://neo4j.com/download/)

## How to run this :

There are two ways to run this project 
  
    sbt clean compile test

    sbt clean compile package

Now copy jar file from "$Template_Home/target/scala-2.12.0-M5" to "$Neo4j_Home/plugins/ " and restart the server.

