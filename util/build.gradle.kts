plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinParcelize()
    kotlinKapt()
}

android {
    setAndroidExtention(rootProject.rootDir.absolutePath)
    
    @Suppress("UnstableApiUsage")
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    kotlin()
    androidX()
    activity()
    fragment()
    rxjava3()
}