package commons

import AndroidConfigs
import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = AndroidConfigs.COMPILE_SDK

    defaultConfig {
        applicationId = AndroidConfigs.APPLICATION_ID
        minSdk = AndroidConfigs.MIN_SDK
        targetSdk = AndroidConfigs.TARGET_SDK
        versionCode = AndroidConfigs.VERSION_CODE
        versionName = AndroidConfigs.VERSION_NAME
        testInstrumentationRunner = AndroidConfigs.TEST_INSTRUMENTATION_RUNNER
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }


    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    // see this ref: https://developer.android.com/kotlin/add-kotlin#source
    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }

    /* lint {
         lintConfig = rootProject.file(".lint/config.xml")
         checkAllWarnings = true
         warningsAsErrors = true
     } */

    configurations.all {
        resolutionStrategy.force("com.google.code.findbugs:jsr305:1.3.9")
    }
}

// Allow references to generated code for dagger
kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.DAGGER_ANDROID)
    implementation(Dependencies.DAGGER_ANDROID_SUPPORT)
    kapt(AnnotationProcessorsDependencies.DAGGER_COMPILER)
    kapt(AnnotationProcessorsDependencies.DAGGER_ANDROID_PROCESSOR)
}
