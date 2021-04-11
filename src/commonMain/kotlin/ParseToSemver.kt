package kmp_semver

// Regex from https://semver.org/, but modified to add leniency so patch doesn't have to be included
// semVerNum matches a number that can be but can't start with zero
private val semVerNum = """0|[1-9]\d*"""
private val semVerRegex = Regex("""^($semVerNum)\.($semVerNum)(?:\.($semVerNum))?(?:-((?:$semVerNum|\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\.(?:$semVerNum|\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\+([0-9a-zA-Z-]+(?:\.[0-9a-zA-Z-]+)*))?${'$'}""")

fun String.toSemVer(patchRequired: Boolean = false): SemVer? {
    val matches = semVerRegex.matchEntire(this)?.groupValues ?: return null
    return SemVer(
        major = matches[1].toIntOrNull() ?: return null,
        minor = matches[2].toIntOrNull() ?: return null,
        patch = matches[3].toIntOrNull() ?: if (patchRequired) return null else 0,
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