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
    implementation(project(":domain"))
    implementation(project(":util"))
    kotlin()
    hilt()
    retrofit()
    okHttp()
    gson()
    rxjava3()
}