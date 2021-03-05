class SemVer(
    val major: Int = 0,
    val minor: Int = 0,
    val patch: Int = 0,
    val preRelease: String? = null,
    val buildMetadata: String? = null,
) : Comparable<SemVer> {
    companion object {
        /**
         * Allows creating a SemVer class with a string as parameter
         */
        operator fun invoke(string: String) = string.toSemVer()
    }

    override fun compareTo(other: SemVer): Int {
        when {
            major > other.major -> return 1
            major < other.major -> return -1
            minor > other.minor -> return 1
            minor < other.minor -> return -1
            patch > other.patch -> return 1
            patch < other.patch -> return -1

            preRelease == null && other.preRelease == null -> return 0
            preRelease != null && other.preRelease == null -> return -1
            preRelease == null && other.preRelease != null -> return 1

            else -> {
                val parts = preRelease.orEmpty().split(".")
                val otherParts = other.preRelease.orEmpty().split(".")

                val endIndex = parts.size.coerceAtMost(otherParts.size)
                for (i in 0 until endIndex) {
                    val part = parts[i]
                    val otherPart = otherParts[i]
                    if (part == otherPart) continue

                    fun String.isNumeric() = this.matches(Regex("""\d+"""))
                    val partIsNumeric = part.isNumeric()
                    val otherPartIsNumeric = otherPart.isNumeric()

                    when {
                        // lower priority
                        partIsNumeric && !otherPartIsNumeric -> return -1
                        // higher priority
                        !partIsNumeric && otherPartIsNumeric -> return 1
                        !partIsNumeric && !otherPartIsNumeric -> {
                            if (part > otherPart) return 1
                            if (part < otherPart) return -1
                        }
                        else -> {
                            val partInt = part.toInt()
                            val otherPartInt = otherPart.toInt()
                            if (partInt > otherPartInt) return 1
                            if (partInt < otherPartInt) return -1
                        }
                    }
                }

                return when {
                    // parts is ended and otherParts is not ended
                    parts.size == endIndex && otherParts.size > endIndex -> -1
                    // parts is not ended and otherParts is ended
                    parts.size > endIndex && otherParts.size == endIndex -> 1
                    else -> 0
                }
            }
        }
    }

    val isPreRelease
        get() = preRelease != null

    override fun toString(): String = buildString {
        append("$major.$minor.$patch")
        if (preRelease != null) append("-$preRelease")
        if (buildMetadata != null) append("+$buildMetadata")
    }
}