// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath(BuildClassesConfig.ANDROID_GRADLE_PLUGIN)
        classpath(BuildClassesConfig.KOTLIN_GRADLE_PLUGIN)
        classpath(BuildClassesConfig.HILT_GRADLE_PLUGIN)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}