# logmob

[![](https://jitci.com/gh/SUB-MOB/logmob/svg)](https://jitci.com/gh/SUB-MOB/logmob)
[![](https://jitpack.io/v/SUB-MOB/logmob.svg)](https://jitpack.io/#SUB-MOB/logmob)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/114b2f31e5c9420b8fe1683cfc290f33)](https://www.codacy.com/gh/SUB-MOB/logmob?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=SUB-MOB/logmob&amp;utm_campaign=Badge_Grade)

## Install

Too install the library please add
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }

            // if you want to add Crashlytics
            google()
		}

        // if you want to add Crashlytics
        // dependencies {
        //     classpath "com.google.gms:google-services:$GSM_GOOGLE_VERSION"
        //     classpath 'com.google.firebase:firebase-crashlytics-gradle:$CRASHLYTICS_VERSION'
        // }
	}
```
to build.gradle (project)

and
```gradle
    // if you want to add Crashlytics
    // apply plugin: 'com.google.gms.google-services'
    // apply plugin: 'com.google.firebase.crashlytics'

	dependencies {
	        implementation 'com.github.SUB-MOB:logmob:VERSION_NUMBER'
	}
```
to build.gradle (app)

## Enable

```kotlin
// Without Crashlytics
initLogMob(this)

// With Crashlytics
initLogMob(this, true)
```

### License
Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
