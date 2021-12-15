# Semantic Versioning Library for Kotlin Multi-Platform
A Kotlin library for Semantic Versioning with ranges and other features such as specifying if a patch is required. It was made because the existing libraries weren't Kotlin Multiplatform Projects, didn't have ranges and several other features.

```kotlin
val semver = SemVer("1.0.0")
val semverRange = Semver("0.5.0")..Semver("2.0.0")

"1.18".toSemVerOrNull(patchRequired = false)

assertTrue { semver in semverRange }
assertTrue { SemVer("0.5.0-alpha").isPreRelease }
```

Credits to [Swiftzer's SemVer library](https://github.com/swiftzer/semver) for inspiring the creation of this project.
