plugins {
    id(BuildPluginsConfig.androidLibrary)
    kotlin(BuildPluginsConfig.kotlinAndroid)
    kotlin(BuildPluginsConfig.kotlinKapt)
    id(BuildPluginsConfig.kotlinParcelize)
}

android {
    compileSdk = (BuildAndroidConfig.COMPILE_SDK_VERSION)
    buildToolsVersion = (BuildAndroidConfig.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
        testInstrumentationRunner = BuildAndroidConfig.androidTestInstrumentation
    }
    viewBinding {
        android.buildFeatures.viewBinding = true
    }
    buildFeatures {
        this.dataBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(DependenciesManager.kotlinImplementation)
    implementation(DependenciesManager.lifeCycleKtxImplementation)
    implementation(DependenciesManager.androidxImplementation)
    implementation(DependenciesManager.thirdPartyImplementation)
    api(project(":core"))

    implementation(TestDependencies.ANDROIDX_ARCH_CORE)
    testImplementation(DependenciesManager.testingImplementation)
    androidTestImplementation(DependenciesManager.androidTestImplementation)
}