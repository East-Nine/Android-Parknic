import com.android.build.gradle.TestedExtension
import org.gradle.api.JavaVersion
import java.io.File
import java.util.*

fun TestedExtension.setAndroidExtention(rootDirectory: String, properties: Properties?) {
    compileSdkVersion(Apps.compileSdk)
    buildToolsVersion(Apps.buildTools)
    
    defaultConfig {
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    
    buildTypes {
        getByName(Build.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            consumerProguardFiles(File("$rootDirectory/buildSrc/consumerRules/consumer-rules.pro"))

            if (properties != null) {
                resValue("string", "key_seoul_api", properties.getProperty("seoul.key"))
            }
        }
        getByName(Build.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            consumerProguardFiles(File("$rootDirectory/buildSrc/consumerRules/consumer-rules.pro"))
        }
    }
    
    sourceSets.getByName("main") {
        java.srcDir("src/main/java")
        java.srcDir("src/main/kotlin")
    }
    
    sourceSets.getByName("test") {
        java.srcDir("src/test/java")
        java.srcDir("src/test/kotlin")
    }
    
    sourceSets.getByName("androidTest") {
        java.srcDir("src/androidTest/java")
        java.srcDir("src/androidTest/kotlin")
    }
    
    packagingOptions {
        exclude("META-INF/LICENSE*")
    }
}