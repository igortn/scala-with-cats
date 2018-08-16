name := "scala-with-cats"

version := "0.1"

scalaVersion := "2.12.4"

scalacOptions := Seq(
  "-Ypartial-unification",
  "-language:higherKinds"
)

val catsVersion = "1.2.0"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core",
  "org.typelevel" %% "cats-free"
).map(_ % catsVersion)

// Includes cats-laws and scalatest.
libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-testkit" % catsVersion % Test
)