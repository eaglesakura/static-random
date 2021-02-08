# What is this repository?

Library for android applications with Kotlin.

armyknife is small library, but it can be more small. If you have to shrink to application, then proguard-options set to
enable.

# how to implementation into your project

```groovy
// build.gradle
dependencies {
    implementation 'io.github.eaglesakura.armyknife-runtime:armyknife-runtime:${replace version}'
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
implementation 'io.github.eaglesakura.armyknife-runtime:armyknife-runtime:${replace version}'
```
