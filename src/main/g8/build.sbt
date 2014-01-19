val originalJvmOptions = sys.process.javaVmArguments.filter(
  a => Seq("-Xmx","-Xms", "-XX").exists(a.startsWith)
)

val baseSettings = Seq(
  scalaVersion := "$scala_version$",
  scalacOptions := Seq("-language:_", "-deprecation", "-unchecked", "-Xlint"),
  watchSources ~= { _.filterNot(f => f.getName.endsWith(".swp") || f.getName.endsWith(".swo") || f.isDirectory) },
  javaOptions ++= originalJvmOptions,
  incOptions := incOptions.value.withNameHashing(true),
  resolvers ++= Seq(Opts.resolver.sonatypeReleases)
)

val main = play.Project("$name$", "$version$", Seq()).settings(
  baseSettings : _*
)

