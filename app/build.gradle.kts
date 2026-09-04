import java.io.File

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.primerprojecto"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.primerprojecto"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.register("fixResources") {
    doLast {
        val imgDir = File(projectDir, "src/main/res/img")
        val memeFile = File(imgDir, "meme.jpeg")
        val drawableDir = File(projectDir, "src/main/res/drawable")
        
        if (memeFile.exists()) {
            drawableDir.mkdirs()
            val destFile = File(drawableDir, "meme.jpeg")
            if (memeFile.renameTo(destFile)) {
                println("Moved meme.jpeg to drawable")
                imgDir.delete()
            } else {
                throw GradleException("Failed to move file")
            }
        } else if (imgDir.exists()) {
            imgDir.delete()
        }
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
