name := "scala-with-cats"

version := "0.1"

scalaVersion := "2.12.4"

scalacOptions += "-Ypartial-unification"

libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core" % "1.0.1"
  , "org.typelevel" %% "cats-laws" % "1.0.1"
  , "org.typelevel" %% "cats-free" % "1.0.1"
  , "org.typelevel" %% "cats-effect" % "0.9"
  , "org.scalatest" %% "scalatest" % "3.0.4" % "test"
)