plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.sergionaude.cleanarchitecturenotesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sergionaude.cleanarchitecturenotesapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":core"))

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Lifecycle
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")
    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.6")
    // Annotation processor
    kapt("androidx.lifecycle:lifecycle-compiler:2.8.6")
    // alternately - if using Java8, use the following instead of lifecycle-compiler
//    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Navigation component
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.3")

    // Feature module support for Fragments
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.8.3")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:2.8.3")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:2.6.1")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.6.1")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:2.6.1")

    // Hilt dependency injection
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
