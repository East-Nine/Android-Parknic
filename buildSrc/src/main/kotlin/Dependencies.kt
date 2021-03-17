object Apps {
    const val compileSdk = 30
    const val buildTools = "30.0.2"
    const val minSdk = 23
    const val targetSdk = 30
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val jvmVersion = "1.8"
}

object Versions {
    const val gradle = "4.1.2"

}

object Kotlin {
    const val version = "1.4.31"

    const val kotlinStdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"

    object Coroutine {
        private const val group = "org.jetbrains.kotlinx"
        const val android = "$group:kotlinx-coroutines-android:1.4.2"
        const val core = "$group:kotlinx-coroutines-core:1.4.2"
    }
}

object AndroidX {
    const val appcompat = "androidx.appcompat:appcompat:1.2.0"
    const val core = "androidx.core:core-ktx:1.3.2"

    object Activity {
        private const val version = "1.2.0-rc01"
        const val activity = "androidx.activity:activity:$version"
        const val activityKtx = "androidx.activity:activity-ktx:$version"
    }

    object Fragment {
        private const val version = "1.3.0-rc01"
        const val fragment = "androidx.fragment:fragment:$version"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
    }

    object View {
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
        const val material = "com.google.android.material:material:1.2.1"
        const val cardview = "androidx.cardview:cardview:1.0.0"
    }

    object HiltViewModel {
        private const val group = "androidx.hilt"
        private const val version = "1.0.0-alpha03"

        const val common = "$group:hilt-common:$version"
        const val viewModel = "$group:hilt-lifecycle-viewmodel:$version"
        const val compiler = "$group:hilt-compiler:$version"
    }

    object Lifecycle {
        private const val group = "androidx.lifecycle"
        private const val version = "2.2.0"

        const val viewModel = "$group:lifecycle-viewmodel:$version"
        const val viewModelKtx = "$group:lifecycle-viewmodel-ktx:$version"
        const val liveData = "$group:lifecycle-livedata:$version"
        const val liveDataKtx = "$group:lifecycle-livedata-ktx:$version"
        const val lifeCycleExtension = "$group:lifecycle-extensions:$version"
        const val LifecycleScope = "$group:lifecycle-runtime-ktx:$version"
    }
}

object Hilt {
    private const val group = "com.google.dagger"
    const val version = "2.33-beta"

    const val hiltAndroid = "$group:hilt-android:$version"
    const val hiltCompiler = "$group:hilt-android-compiler:$version"
}

object Retrofit {
    private const val group = "com.squareup.retrofit2"
    const val retrofit = "$group:retrofit:2.9.0"
    const val converterGson = "$group:converter-gson:2.7.1"
    const val adapterRxJava3 = "$group:adapter-rxjava3:2.9.0"
}

object OkHttp {
    private const val group = "com.squareup.okhttp3"
    const val logging = "$group:logging-interceptor:4.8.1"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:4.11.0"
}

object Gson {
    const val gson = "com.google.code.gson:gson:2.8.6"
}

object RxJava3 {
    private const val group = "io.reactivex.rxjava3"
    const val rxjava = "$group:rxjava:3.0.9"
    const val rxandroid = "$group:rxandroid:3.0.0"
    const val rxkotlin = "$group:rxkotlin:3.0.1"
}

object Test {
    const val junit = "junit:junit:4.13"
    const val extJunit = "androidx.test.ext:junit:1.1.2"
    const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
}