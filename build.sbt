name := "scala-exercises"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.9.1" % "test")

scalacOptions in Test ++= Seq("-Yrangepos")