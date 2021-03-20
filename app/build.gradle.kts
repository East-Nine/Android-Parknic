import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    androidApp()
    kotlinAndroid()
    kotlinParcelize()
    kotlinKapt()
    daggerHilt()
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

android {
    setAndroidExtention(rootProject.rootDir.absolutePath, properties)

    defaultConfig {
        applicationId = "com.eastnine.parknic"
        addManifestPlaceholders(mapOf("kakaoMapKey" to properties.getProperty("kakao.key")))

        externalNativeBuild {
            cmake {
                cppFlags("")
            }
        }
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.10.2"
        }
    }
    ndkVersion = "22.0.7026061"

    @Suppress("UnstableApiUsage")
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":util"))
    kotlin()
    androidX()
    activity()
    fragment()
    view()
    lifeCycle()
    hilt()
    hiltViewModel()
    retrofit()
    gson()
    rxjava3()

    test()
}
