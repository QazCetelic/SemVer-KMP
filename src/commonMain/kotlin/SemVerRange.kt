data class SemVerRange(val start: SemVer, val end: SemVer) {
    init {
        // An inverse range is not allowed
        if (start >= end) throw IllegalArgumentException()
    }
}

// Creation function
operator fun SemVer.rangeTo(endSemVer: SemVer) = SemVerRange(this, endSemVer)

operator fun SemVerRange.contains(semVer: SemVer): Boolean {
    return this.start <= semVer && this.end >= semVer
}

operator fun SemVerRange.contains(semVerRange: SemVerRange) = semVerRange.start in this && semVerRange.end in this