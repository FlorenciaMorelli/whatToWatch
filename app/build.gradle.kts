import java.io.FileNotFoundException
import java.util.Properties

//  Load properties from apikeys.properties file
val properties = Properties().apply {
    val file = rootProject.file("apikeys.properties")
    if (file.exists()) {
        load(file.inputStream())
    } else {
        throw FileNotFoundException("apikeys.properties file not found.")
    }
}
//  Define API Key from properties
val tmdbApiKey: String = properties["TMDB_API_KEY"] as String?
    ?: throw IllegalArgumentException("TMDB_API_KEY key not found on apikeys.properties")


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.whattowatch"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.whattowatch"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //  Add API Key as an environment variable
        buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"")
        //  REMINDER: You can call it using
        //  val apiKey = BuildConfig.TMDB_API_KEY
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        enable = true
    }

    buildFeatures{
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.picasso:picasso:2.5.2")

    implementation("androidx.activity:activity-ktx:1.9.2")

    //  ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    //  Fragment
    implementation("androidx.fragment:fragment-ktx:1.6.1")

    //  Room
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    //  Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")
}