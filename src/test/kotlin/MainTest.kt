import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MainTest {

    @Test
    fun `should handle 2 candidates with different vote count`() {
        assertEquals(listOf("Bob", "Jane"), Main().voteCount(listOf(
            listOf("Bob", "Jane", ""),
            listOf("Bob", "Jane", "")
        )))
    }

    @Test
    fun `should handle candidates with no names`() {
        assertEquals(listOf<String>(), Main().voteCount(listOf(
            listOf("", "", ""),
            listOf("", "", "")
        )))
    }

    @Test
    fun `should win first across the line for equal votes`() {
        assertEquals(listOf("B", "J", "C"), Main().voteCount(listOf(
            listOf("C", "J", "C"),
            listOf("B", "J", "C"),
            listOf("B", "J", "C"),
        )))
    }
}