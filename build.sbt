name := "FirstScala"

version := "0.1"

fork := true

// only relevant for Java sources --
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-unchecked", "-deprecation")

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.0"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.2.0"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.0"

libraryDependencies += "org.apache.spark" %% "spark-hive" % "2.2.0"

libraryDependencies += "org.apache.spark" %% "spark-graphx" % "2.2.0"

// needed to make the hiveql examples run at least on Linux
javaOptions in run += "-XX:MaxPermSize=128M"

scalacOptions += "-target:jvm-1.8"
