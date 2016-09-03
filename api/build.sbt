name := "eXtraWidgets-API"

scalaVersion := "2.11.8"

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-Xfatal-warnings",
  "-encoding", "UTF8")

exportJars := true

artifactName in Compile := { (_, _, artifact: Artifact) => artifact.name + "." + artifact.extension }

artifactName in Test := { (_, _, artifact: Artifact) => artifact.name + "-test." + artifact.extension }

libraryDependencies ++= Seq(
  "org.nlogo" % "netlogo" % "6.0.0-BETA1" intransitive
)

//site.settings

//site.includeScaladoc("/")

//ghpages.settings

//git.remoteRepo := "git@github.com:CRESS-Surrey/eXtraWidgets.git"
