import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.kotlin() {
    implementation(Kotlin.kotlinStdlibJdk8)
    implementation(Kotlin.Coroutine.android)
    implementation(Kotlin.Coroutine.core)
}

fun DependencyHandlerScope.activity() {
    implementation(AndroidX.Activity.activity)
    implementation(AndroidX.Activity.activityKtx)
}

fun DependencyHandlerScope.fragment() {
    implementation(AndroidX.Fragment.fragment)
    implementation(AndroidX.Fragment.fragmentKtx)
}

fun DependencyHandlerScope.androidX() {
    implementation(AndroidX.appcompat)
    implementation(AndroidX.core)
}

fun DependencyHandlerScope.view() {
    implementation(AndroidX.View.constraintLayout)
    implementation(AndroidX.View.recyclerview)
    implementation(AndroidX.View.material)
    implementation(AndroidX.View.cardview)
}

fun DependencyHandlerScope.lifeCycle() {
    implementation(AndroidX.Lifecycle.viewModel)
    implementation(AndroidX.Lifecycle.viewModelKtx)
    implementation(AndroidX.Lifecycle.liveData)
    implementation(AndroidX.Lifecycle.liveDataKtx)
    implementation(AndroidX.Lifecycle.lifeCycleExtension)
    implementation(AndroidX.Lifecycle.LifecycleScope)
}

fun DependencyHandlerScope.hiltViewModel() {
    implementation(AndroidX.HiltViewModel.common)
    implementation(AndroidX.HiltViewModel.compiler)
    implementation(AndroidX.HiltViewModel.viewModel)
}

fun DependencyHandlerScope.hilt() {
    implementation(Hilt.hiltAndroid)
    kapt(Hilt.hiltCompiler)
}

fun DependencyHandlerScope.retrofit() {
    implementation(Retrofit.retrofit)
    implementation(Retrofit.converterGson)
    implementation(Retrofit.adapterRxJava3)
}

fun DependencyHandlerScope.okHttp() {
    implementation(OkHttp.logging)
}

fun DependencyHandlerScope.glide() {
    implementation(Glide.glide)
}

fun DependencyHandlerScope.gson() {
    implementation(Gson.gson)
}

fun DependencyHandlerScope.rxjava3() {
    implementation(RxJava3.rxjava)
    implementation(RxJava3.rxandroid)
    implementation(RxJava3.rxkotlin)
}

fun DependencyHandlerScope.test() {
    testImplementation(Test.junit)
    androidTestImplementation(Test.extJunit)
    androidTestImplementation(Test.espresso)
}

private fun DependencyHandler.implementation(depName: String) =
    add("implementation", depName)

private fun DependencyHandler.kapt(depName: String) =
    add("kapt", depName)

private fun DependencyHandler.testImplementation(depName: String) =
    add("testImplementation", depName)

private fun DependencyHandler.androidTestImplementation(depName: String) =
    add("androidTestImplementation", depName)