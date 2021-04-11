package kmp_semver

// Regex from https://semver.org/
private val semVerRegex = Regex("""^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)(?:-((?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\.(?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\+([0-9a-zA-Z-]+(?:\.[0-9a-zA-Z-]+)*))?${'$'}""")

fun String.toSemVer(): SemVer? {
    val matches = semVerRegex.matchEntire(this)?.groupValues ?: return null
    return SemVer(
        major = matches[1].toIntOrNull() ?: return null,
        minor = matches[2].toIntOrNull() ?: return null,
        patch = matches[3].toIntOrNull() ?: 0,
        preRelease = matches[4].let {
            // Sets it to null if the string is empty
            if (it.isEmpty()) null
            else it
        },
        buildMetadata = matches[5].let {
            // Sets it to null if the string is empty
            if (it.isEmpty()) null
            else it
        },
    )
}

fun String.isSemVer(): Boolean {
    return semVerRegex.matchEntire(this) != null
}