import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.initialization.dsl.ScriptHandler.CLASSPATH_CONFIGURATION

object DependenciesManager {
    val kotlinImplementation = arrayListOf<String>().apply {
        add(KotlinDependencies.KOTLIN_REFLECTION)
        add(KotlinDependencies.KOTLIN_STD_LIB)
        add(KotlinDependencies.COROUTINE_CORE)
        add(KotlinDependencies.COROUTINE_ANDROID)
    }

    val lifeCycleKtxImplementation = arrayListOf<String>().apply {
        add(LifeCycleKtxDependencies.CORE_KTX)
        add(LifeCycleKtxDependencies.LIFECYCL_EEXTENSIONS)
        add(LifeCycleKtxDependencies.Collection_KTX)
        add(LifeCycleKtxDependencies.VIEW_MODEL_KTX)
        add(LifeCycleKtxDependencies.VIEW_MODEL_SAVE_STATE_KTX)
        add(LifeCycleKtxDependencies.LIVEDATA_KTX)
        add(LifeCycleKtxDependencies.LIFECYCLE_RUNTIME)
        add(LifeCycleKtxDependencies.LIFECYCLE_RUNTIME_KTX)
        add(LifeCycleKtxDependencies.LIFECYCLE_COMMON_JAVA)
        add(LifeCycleKtxDependencies.REACTIVE_STREAMS)
    }
    val androidxImplementation = arrayListOf<String>().apply {
        add(AndroidxDependencies.APPCOMPAT)
        add(AndroidxDependencies.FRAGMENT_KTX)
        add(AndroidxDependencies.ACTIVITY_KTX)
        add(AndroidxDependencies.CONSTRAINT_LAYOUT)
        add(AndroidxDependencies.CARD_VIEW)
        add(AndroidxDependencies.RECYCLERVIEW)
        add(AndroidxDependencies.VECTOR_DRAWABLE)
        add(AndroidxDependencies.VECTOR_DRAWABLE_ANIMATED)
        add(AndroidxDependencies.VIEWPAGER2)
        add(AndroidxDependencies.MATERIAL)
        add(AndroidxDependencies.PAGING_RUNTIME)
        add(AndroidxDependencies.SECURITY)

    }
    val navigationImplementation = arrayListOf<String>().apply {
        add(NavigationDependencies.NAVIGATION_RUNTIME)
        add(NavigationDependencies.NAVIGATION_FRAGMENT_KTX)
        add(NavigationDependencies.NAVIGATION_UI_KTX)
    }

    val thirdPartyImplementation = arrayListOf<String>().apply {
        add(ThirdPartyDependencies.SDP)
        add(ThirdPartyDependencies.SSP)
        add(ThirdPartyDependencies.INLINE_ACTIVITY_RESULT)
    }


    val hiltImplementation = arrayListOf<String>().apply {
        add(HiltDaggerDependencies.DAGGER_HILT)
        add(HiltDaggerDependencies.HILT_FRAGMENT)
    }
    val hiltKapt = arrayListOf<String>().apply {
        add(HiltDaggerDependencies.DAGGER_COMPILER)
    }
    val testingImplementation = arrayListOf<String>().apply {
        add(TestDependencies.JUNIT4)
        add(TestDependencies.MOCKK)
        add(TestDependencies.ROBOLECTRIC)
        add(TestDependencies.JUPITER)
        add(TestDependencies.JUPITER_ENGINE)
        add(TestDependencies.MOCK_JSON)
        add(TestDependencies.MOCK_WEB_SERVER)
        add(TestDependencies.MOCKITO_INLINE)

    }

    val androidTestImplementation = arrayListOf<String>().apply {
        add(TestDependencies.TESTEXTENSIONS)
        add(TestDependencies.ESPRESSOCORE)
        add(TestDependencies.TESTRUNNER)
        add(TestDependencies.TESTRULES)
        add(TestDependencies.ESPRESSOINTENTS)
        add(TestDependencies.HILTTESTING)

    }

    val dataImplementation = arrayListOf<String>().apply {
        add(DataDependencies.ROOM_COMPILER)
        add(DataDependencies.ROOM_KTX)
        add(DataDependencies.ROOM_RUNTIME)
        add(DataDependencies.GSON)
        add(DataDependencies.GLIDE)
        add(DataDependencies.GLIDE_COMPILER)
    }

}

//util functions for adding the different type dependencies from build.gradle file
//fun DependencyHandler.kapt(list: String) {
//    list.forEach { dependency ->
//        add("kapt", dependency)
//    }
//}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.classpath(list: List<String>) {
    list.forEach { classPath ->
        this.add(CLASSPATH_CONFIGURATION, classPath)
    }
}
