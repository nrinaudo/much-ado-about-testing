enablePlugins(TutPlugin, GhpagesPlugin)

organization := "com.nrinaudo"
scalaVersion := "2.13.0"

val tutDirName = settingKey[String]("tut output directory")
tutDirName := "./"

val graphvizDirName = settingKey[String]("graphviz output directory")
graphvizDirName := "./img"

val graphviz = taskKey[Seq[(File, String)]]("compile all dot files")
graphviz := {
  import scala.sys.process._

  val files = (sourceDirectory.value / "graphviz") ** "*.dot"
  val outDir = target.value / "graphviz"

  outDir.mkdirs

  files.get.map { file =>
    val outFile = file.getName().replace(".dot", ".svg")
    val outPath = outDir / outFile

    s"dot -Tsvg $file -o $outPath" !

    (outPath, outFile)
  }
}

addMappingsToSiteDir(tut, tutDirName)
addMappingsToSiteDir(graphviz, graphvizDirName)

SitePlugin.autoImport.makeSite / includeFilter :=
    "*.yml" | "*.md" | "*.html" | "*.css" | "*.png" | "*.jpg" | "*.gif" | "*.js" | "*.eot" | "*.svg" | "*.ttf" |
    "*.woff" | "*.woff2" | "*.otf"

git.remoteRepo := "git@github.com:nrinaudo/much-ado-about-testing.git"

libraryDependencies ++= Seq(
  "org.scalatest"  %% "scalatest"  % Versions.scalatest,
  "org.scalacheck" %% "scalacheck" % Versions.scalacheck
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
