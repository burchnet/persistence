name := """persistence"""

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Spring Maven MILESTONE Repository" at "http://maven.springframework.org/milestone"

val springDataMongo = Seq("org.mongodb" % "mongo-java-driver" % "2.6",
        "org.springframework" % "spring-core" % "3.0.5.RELEASE",
        "org.springframework" % "spring-context" % "3.0.5.RELEASE",
        "org.springframework.data" % "spring-data-mongodb" % "1.0.0.M4")

val reactiveMongo = Seq("org.reactivemongo" %% "reactivemongo" % "0.11.9")

val scalaz = Seq("org.scalaz" %% "scalaz-core" % "7.2.0")

val scalaTest = Seq("org.scalatest" %% "scalatest" % "2.2.4" % "test")

libraryDependencies ++= scalaTest ++ springDataMongo ++ scalaz



fork in run := true