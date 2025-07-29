plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.cisco.spaces.wayfinding.sample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.cisco.spaces.wayfinding.sample"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar", "*.jar"))))

    implementation(libs.google.play.service.location)
    implementation(libs.maplibre)
    implementation(libs.mapbox.android.plugin.localization)
    implementation(libs.mapbox.android.plugin.markerview)


    implementation(libs.google.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)

//    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.recyclerview)
    implementation(libs.gson)

    implementation(libs.glide)
    implementation(libs.cardview)
    implementation(libs.lifecycle)

}