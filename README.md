# SemVer-KMP
I needed a SemVer library for a Kotlin project but wanted something different than the other ones already out there and because almost all of were JVM only or didn't target all platforms.

Example
```
val semver = SemVer("1.0.0")
val semverRange = Semver("0.5.0")..Semver("2.0.0")

println(semver in semverRange) //prints true
println(SemVer("0.5.0-alpha").isPreRelease) //prints true
```

Credits to [swiftzer's semver](https://github.com/swiftzer/semver) project because I learned how to use SemVer from there