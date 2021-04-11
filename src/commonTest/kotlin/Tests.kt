package kmp_semver

import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertTrue

class Tests {
    @Test
    fun semver() {
        val complicatedSemVer = SemVer("5.21.3-Alpha+aes163")
        assertTrue { complicatedSemVer.isPreRelease }

        val semver = SemVer("1.2.3")
        assertTrue { semver.major == 1 }
        assertTrue { semver.minor == 2 }
        assertTrue { semver.patch == 3 }

        assertFails { SemVer("012.123.122") }
    }

    @Test
    fun semverRange() {
        val semVerRange = SemVer("1.0.0")..SemVer("2.0.0")

        val semver1 = SemVer("2.0.1")
        val semver2 = SemVer("1.25.0")
        val semver3 = SemVer("0.4.326")

        assertTrue { semver1 !in semVerRange }
        assertTrue { semver2 in semVerRange }
        assertTrue { semver3 !in semVerRange }

        val smallerSemVerRange = SemVer("1.5.0")..SemVer("1.6.0")
        assertTrue { smallerSemVerRange in semVerRange }
    }
}