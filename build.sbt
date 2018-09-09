name := "scala-with-cats"

version := "0.1"

scalaVersion := "2.12.6"

scalacOptions := Seq(
  "-Ypartial-unification",
  "-language:higherKinds"
)

val catsVersion = "1.3.1"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core",
  "org.typelevel" %% "cats-free"
).map(_ % catsVersion)

// Includes cats-laws and scalatest.
libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-testkit" % catsVersion % Test
)
