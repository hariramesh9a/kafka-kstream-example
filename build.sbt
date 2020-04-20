name := "kafka-ksql-kstream"

version := "0.1"

scalaVersion := "2.12.6"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.4.1"
// https://mvnrepository.com/artifact/org.apache.kafka/kafka-streams
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "2.4.1"
// https://mvnrepository.com/artifact/org.apache.kafka/kafka-streams-scala
libraryDependencies += "org.apache.kafka" %% "kafka-streams-scala" % "2.4.1"
// https://mvnrepository.com/artifact/com.goyeau/kafka-streams-circe
libraryDependencies += "com.goyeau" %% "kafka-streams-circe" % "0.6"
