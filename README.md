# What is this repository?

Library for android applications with Kotlin.

armyknife is small library, but it can be more small.
If you have to shrink to application, then proguard-options set to enable.

# how to implementation into your project

```groovy
// /build.gradle
allprojects {
    repositories {
        // add the below line into build.gradle.
        maven { url 'https://dl.bintray.com/eaglesakura/maven/' }
    }
}

// /app/build.gradle
dependencies {
    // check versions
    // https://github.com/eaglesakura/army-knife/releases
    implementation 'com.eaglesakura.armyknife:armyknife-runtime:${replace version}'
}
```

## Dev / LocalInstall

```sh
./gradlew -Pinstall_snapshot build uploadArchives
```

```groovy
repositories {
    mavenLocal()
}

// replace version("major.minor.99999")
implementation 'com.eaglesakura.armyknife:armyknife-runtime:${replace version}'
```
