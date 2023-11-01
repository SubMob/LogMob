/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

rootProject.name = "LogMob"
include(":logmob")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}

plugins {
    id("com.gradle.enterprise") version ("3.13")
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()

        obfuscation {
            username { null }
            hostname { null }
            ipAddresses { null }
        }
    }
}
