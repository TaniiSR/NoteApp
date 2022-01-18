plugins {
    id(BuildPluginsConfig.androidLibrary)
    id(BuildPluginsConfig.androidHilt)
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
    implementation(DataDependencies.ROOM_KTX)
    implementation(DataDependencies.ROOM_RUNTIME)
    kapt(DataDependencies.ROOM_COMPILER)
    implementation(DependenciesManager.hiltImplementation)

    kapt(HiltDaggerDependencies.DAGGER_COMPILER)
    implementation(TestDependencies.ANDROIDX_ARCH_CORE)
    testImplementation(DependenciesManager.testingImplementation)
    androidTestImplementation(DependenciesManager.androidTestImplementation)
}