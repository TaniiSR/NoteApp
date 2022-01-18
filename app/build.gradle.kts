plugins {
    id(BuildPluginsConfig.androidApplication)
    id(BuildPluginsConfig.androidHilt)
    kotlin(BuildPluginsConfig.kotlinAndroid)
    kotlin(BuildPluginsConfig.kotlinKapt)
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = (BuildAndroidConfig.COMPILE_SDK_VERSION)
    buildToolsVersion = (BuildAndroidConfig.BUILD_TOOLS_VERSION)
    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME
        multiDexEnabled = true
        testInstrumentationRunner = BuildAndroidConfig.androidTestInstrumentation
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            isDebuggable = true
        }

        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

    }

    flavorDimensions("version")
    productFlavors {
        create("live") {
            applicationId = "com.task.noteapp"
            dimension = "version"
//            resValue("string", "app_name", "Note App")
        }
        create("dev") {
            applicationIdSuffix = ".dev"
            dimension = "version"
//            resValue("string", "app_name", "Note App")
        }

    }
    viewBinding {
        android.buildFeatures.viewBinding = true
    }
    buildFeatures {
        this.dataBinding = true
    }

    packagingOptions {
        exclude("LICENSE.txt")
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE")
        exclude("META-INF/*.kotlin_module")
        exclude("META-INF/gradle/incremental.annotation.processors")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    configurations {
        implementation.get().exclude(mapOf("group" to "org.jetbrains", "module" to "annotations"))
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(DependenciesManager.kotlinImplementation)
    implementation(DependenciesManager.lifeCycleKtxImplementation)
    implementation(DependenciesManager.androidxImplementation)
    implementation(DependenciesManager.hiltImplementation)
    implementation(ThirdPartyDependencies.SDP)
    implementation(ThirdPartyDependencies.SSP)
    kapt(HiltDaggerDependencies.DAGGER_COMPILER)
    implementation(DataDependencies.ROOM_KTX)
    implementation(DataDependencies.ROOM_RUNTIME)
    kapt(DataDependencies.ROOM_COMPILER)
    api(project(":mynoteapp"))

    implementation(DependenciesManager.navigationImplementation)
    implementation("com.intellij:annotations:+@jar")
    testImplementation(DependenciesManager.testingImplementation)
    androidTestImplementation(DependenciesManager.androidTestImplementation)

}