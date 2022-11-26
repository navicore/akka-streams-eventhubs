name := "Akkastreamseventhubs"

fork := true
javaOptions in test ++= Seq(
  "-Xms512M", "-Xmx2048M",
  "-XX:MaxPermSize=2048M",
  "-XX:+CMSClassUnloadingEnabled"
)

parallelExecution in test := false

version := "1.0"

scalaVersion := "2.12.4"
ensimeScalaVersion in ThisBuild := "2.12.4"
val akkaVersion = "2.5.6"

resolvers += Resolver.sonatypeRepo("snapshots") // for tensorflow

libraryDependencies ++=
  Seq(
    "ch.qos.logback" % "logback-classic" % "1.1.7",
    "com.typesafe" % "config" % "1.2.1",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",

    "com.chuusai" %% "shapeless" % "2.3.10",

    "org.platanios" %% "tensorflow" % "0.1.0-SNAPSHOT" classifier "darwin-cpu-x86_64",
    //"org.platanios" %% "tensorflow" % "0.1.0-SNAPSHOT" classifier "linux-cpu-x86_64",

    "org.scalanlp" %% "breeze" % "0.13.2",
    "org.scalanlp" %% "breeze-viz" % "0.13.2",

    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,

    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )

dependencyOverrides ++= Seq(
  "com.typesafe.akka" %% "akka-actor"  % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion
)

mainClass in assembly := Some("onextent.akka.eventhubs.Main")
assemblyJarName in assembly := "Akkastreamseventhubs.jar"

assemblyMergeStrategy in assembly := {
  case PathList("reference.conf") => MergeStrategy.concat
  case x if x.endsWith("io.netty.versions.properties") => MergeStrategy.first
  case PathList("META-INF", _ @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

