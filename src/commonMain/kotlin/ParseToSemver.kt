fun String.toSemVer(): SemVer {
    // Regex from https://semver.org/
    val semVerRegex = Regex("""^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)(?:-((?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\.(?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\+([0-9a-zA-Z-]+(?:\.[0-9a-zA-Z-]+)*))?${'$'}""")
    val result = semVerRegex.matchEntire(this) ?: throw IllegalArgumentException("Invalid version string [$this]")

    fun takeIntOrZero(index: Int) = if (result.groupValues[index].isEmpty()) 0 else result.groupValues[index].toInt()
    fun takeStringOrNull(index: Int) = if (result.groupValues[index].isEmpty()) null else result.groupValues[index]
    return SemVer(
        major = takeIntOrZero(1),
        minor = takeIntOrZero(2),
        patch = takeIntOrZero(3),
        preRelease = takeStringOrNull(4),
        buildMetadata = takeStringOrNull(5),
    )
}