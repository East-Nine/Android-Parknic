plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinParcelize()
    kotlinKapt()
    daggerHilt()
}

android {
    setAndroidExtention(rootProject.rootDir.absolutePath, null)
}

dependencies {
    kotlin()
    hilt()
    rxjava3()
}