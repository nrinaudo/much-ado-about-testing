enablePlugins(TutPlugin, GhpagesPlugin)

organization := "com.nrinaudo"
scalaVersion := "2.13.0"
graphvizStylesheet := Some(graphvizSourceDirectory.value / "style.dss")

Tut / siteSubdirName := "./"

addMappingsToSiteDir(tut, Tut / siteSubdirName)

SitePlugin.autoImport.makeSite / includeFilter :=
    "*.yml" | "*.md" | "*.html" | "*.css" | "*.png" | "*.jpg" | "*.gif" | "*.js" | "*.eot" | "*.svg" | "*.ttf" |
    "*.woff" | "*.woff2" | "*.otf"

git.remoteRepo := "git@github.com:nrinaudo/much-ado-about-testing.git"

libraryDependencies ++= Seq(
  "org.scalatest"      %% "scalatest"       % Versions.scalatest,
  "org.scalatestplus"  %% "scalacheck-1-15" % Versions.scalatestplus,
  "org.scalacheck"     %% "scalacheck"      % Versions.scalacheck
)
