
import java.util.Properties;

plugins {
    id("com.android.application")
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.example.forforeiner"
    compileSdk = 34


    defaultConfig {
        android.buildFeatures.buildConfig=true
        buildConfigField("String","AI_API_KEY","\"${properties.getProperty("ai_api_key")}\"")
        buildConfigField("String","NAVER_CLIENT_ID","\"${properties.getProperty("naver_client_id")}\"")
        buildConfigField("String","NAVER_SECRET_KEY","\"${properties.getProperty("naver_secret_key")}\"")
        buildConfigField("String","GOOGLE_API_KEY","\"${properties.getProperty("google_api_key")}\"")
        buildConfigField("String","NAVER_NATIVE_KEY","\"${properties.getProperty("naver_native_key")}\"")

        manifestPlaceholders["NAVER_NATIVE_KEY"] = "${properties.getProperty("naver_native_key")}"

        applicationId = "com.example.forforeiner"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation ("com.kakao.maps.open:android:2.6.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.1.0")
    implementation ("com.google.code.gson:gson:2.8.7")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.8.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation ("androidx.appcompat:appcompat:1.5.1")
    implementation ("com.google.android.material:material:1.7.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation ("com.google.android.gms:play-services-maps:18.1.0")
    implementation ("com.google.android.libraries.places:places:3.0.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
