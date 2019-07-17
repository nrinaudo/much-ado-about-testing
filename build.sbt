enablePlugins(TutPlugin, GhpagesPlugin)

organization := "com.nrinaudo"
scalaVersion := "2.12.8"

val tutDirName = settingKey[String]("tut output directory")
tutDirName := "./"

addMappingsToSiteDir(tut, tutDirName)
includeFilter in SitePlugin.autoImport.makeSite :=
    "*.yml" | "*.md" | "*.html" | "*.css" | "*.png" | "*.jpg" | "*.gif" | "*.js" | "*.eot" | "*.svg" | "*.ttf" |
    "*.woff" | "*.woff2" | "*.otf"

git.remoteRepo := "git@github.com:nrinaudo/much-ado-about-testing.git"

libraryDependencies ++= Seq(
  "org.scalatest"  %% "scalatest"                % Versions.scalatest,
  "org.scalacheck" %% "scalacheck"               % Versions.scalacheck,
  "com.nrinaudo"   %% "kantan.dtables-scalatest" % Versions.kantanDTables,
  "com.nrinaudo"   %% "kantan.dtables-csv"       % Versions.kantanDTables
)

// Bit of a hack to work around scalacheckout adding odd \r characters to its output, which are translated to unwanted
// line breaks in the resulting slides.
val sanitizeTut: TaskKey[Unit] = taskKey[Unit]("Drops line feeds from tut output")
sanitizeTut := {
  import scala.io._
  import java.io._

  (tutTargetDirectory.value ** "*.html").get.foreach { file =>

    val in = Source.fromFile(file).filter(_ != '\r').toArray

    val out = new OutputStreamWriter(new FileOutputStream(file), "utf-8")
    out.write(in)
    out.close()
  }
}

makeSite := makeSite.dependsOn(sanitizeTut.dependsOn(tut)).value
ghpagesPushSite := ghpagesPushSite.dependsOn(makeSite).value
