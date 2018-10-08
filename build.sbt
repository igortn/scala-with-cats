name := "scala-with-cats"

version := "0.1"

scalaVersion := "2.12.7"

scalacOptions := Seq(
  "-Ypartial-unification",
  "-language:higherKinds"
)

val catsVersion = "1.4.0"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core",
  "org.typelevel" %% "cats-free"
).map(_ % catsVersion)

libraryDependencies ++= Seq(
  "com.github.mpilquist" %% "simulacrum" % "0.13.0",
  
  // Includes cats-laws and scalatest
  "org.typelevel" %% "cats-testkit" % catsVersion % Test
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
