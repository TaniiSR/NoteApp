plugins {
    id(BuildPluginsConfig.androidLibrary)
    id(BuildPluginsConfig.androidHilt)
    kotlin(BuildPluginsConfig.kotlinAndroid)
    kotlin(BuildPluginsConfig.kotlinKapt)
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

    viewBinding {
        android.buildFeatures.viewBinding = true
    }
    buildFeatures {
        this.dataBinding = true
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(DependenciesManager.kotlinImplementation)
    implementation(DependenciesManager.lifeCycleKtxImplementation)
    implementation(DependenciesManager.androidxImplementation)
    implementation(DependenciesManager.hiltImplementation)
    implementation(DependenciesManager.navigationImplementation)
    implementation(DataDependencies.ROOM_KTX)
    implementation(DataDependencies.ROOM_RUNTIME)
    kapt(DataDependencies.ROOM_COMPILER)
    testImplementation(DataDependencies.ROOM_TEST )

    implementation(DependenciesManager.thirdPartyImplementation)
    api(project(":data"))
    api(project(":core"))
    api(project(":uikit"))
    kapt(HiltDaggerDependencies.DAGGER_COMPILER)
    implementation(TestDependencies.ANDROIDX_ARCH_CORE)

    testImplementation(DependenciesManager.testingImplementation)
    androidTestImplementation(DependenciesManager.androidTestImplementation)
    implementation(TestDependencies.ANDROIDX_ARCH_CORE)
    implementation(TestDependencies.COROUTINES)
    implementation(TestDependencies.ANDROIDX_ARCH_CORE)
}