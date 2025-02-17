plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.manager.appbanhang"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.manager.appbanhang"
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
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    packagingOptions {
        exclude ("META-INF/DEPENDENCIES")
    }
}

repositories {
//    mavenCentral()
    //maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.messaging)

    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.6.0")
    implementation("androidx.activity:activity-ktx:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation(libs.firebase.firestore)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    // RxJava
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation("io.reactivex.rxjava3:rxjava:3.0.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")
    // Badge
    implementation("com.nex3z:notification-badge:1.0.4")
    // EventBus
    implementation("org.greenrobot:eventbus:3.3.1")
    // PaperDB
    implementation("io.github.pilgr:paperdb:2.7.2")
    // Gson
    implementation("com.google.code.gson:gson:2.11.0")
    // Lottie
    implementation("com.airbnb.android:lottie:6.4.1")
    // Neumorphism
    implementation("com.github.fornewid:neumorphism:0.3.2")
    // Image Picker
    implementation("com.github.dhaval2404:imagepicker:2.1")
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation ("com.google.auth:google-auth-library-oauth2-http:1.19.0")
    implementation("com.google.firebase:firebase-messaging")
    //fcm
    implementation("com.google.firebase:firebase-messaging")


}
