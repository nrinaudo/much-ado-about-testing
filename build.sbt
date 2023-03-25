enablePlugins(GhpagesPlugin)

organization := "com.nrinaudo"
scalaVersion := "3.1.3"
graphvizStylesheet := Some(graphvizSourceDirectory.value / "style.dss")

SitePlugin.autoImport.makeSite / includeFilter :=
    "*.yml" | "*.md" | "*.html" | "*.css" | "*.png" | "*.jpg" | "*.gif" | "*.js" | "*.eot" | "*.svg" | "*.ttf" |
    "*.woff" | "*.woff2" | "*.otf"

git.remoteRepo := "git@github.com:nrinaudo/much-ado-about-testing.git"

libraryDependencies ++= Seq(
  "org.scalatest"      %% "scalatest"       % Versions.scalatest,
  "org.scalatestplus"  %% "scalacheck-1-17" % Versions.scalatestplus
)
