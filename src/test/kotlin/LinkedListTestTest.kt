import org.example.LinkedListTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class LinkedListTestTest {
    private lateinit var list: LinkedListTest<Int>

    @BeforeEach
    fun setUp() {
        // Create a new list before each test to ensure isolation
        list = LinkedListTest()
    }

    @Test
    fun `insertAtBeginning on an empty list should make it the head`() {
        list.insertAtBeginning(10)

        assertEquals(1, list.size)
        assertNotNull(list.head)
        assertEquals(10, list.head?.value)
        assertNull(list.head?.next)
    }

    @Test
    fun `insertAtBeginning on a non-empty list should become the new head`() {
        list.insertAtBeginning(10) // list is 10
        list.insertAtBeginning(20) // list is 20 -> 10

        assertEquals(2, list.size)
        assertEquals(20, list.head?.value)
        assertEquals(10, list.head?.next?.value)
    }

    @Test
    fun `insertAtEnd on an empty list should work correctly`() {
        list.insertAtEnd(10)

        assertEquals(1, list.size)
        assertEquals(10, list.head?.value)
    }

    @Test
    fun `insertAtEnd on a non-empty list should add element to the end`() {
        list.insertAtBeginning(10)
        list.insertAtEnd(20) // list is 10 -> 20

        assertEquals(2, list.size)
        assertEquals(10, list.head?.value)
        assertEquals(20, list.head?.next?.value)
        assertNull(list.head?.next?.next)
    }

    @Test
    fun `insertAtIndex 0 should behave like insertAtBeginning`() {
        list.insertAtBeginning(20) // list is 20
        list.insertAtIndex(0, 10)  // list should be 10 -> 20

        assertEquals(2, list.size)
        assertEquals(10, list.head?.value)
        assertEquals(20, list.head?.next?.value)
    }

    @Test
    fun `insertAtIndex at lastIndex should behave like insertAtEnd`() {
        list.insertAtBeginning(10) // list is 10, lastIndex is 0
        list.insertAtIndex(0, 20)  // list should be 20 -> 10

        assertEquals(2, list.size)
        assertEquals(20, list.head?.value)
    }

    @Test
    fun `insertAtIndex in the middle of the list should insert correctly`() {
        list.insertAtEnd(10)
        list.insertAtEnd(30) // list is 10 -> 30
        list.insertAtIndex(1, 20) // list should be 10 -> 20 -> 30

        assertEquals(3, list.size)
        assertEquals(10, list.head?.value)
        assertEquals(20, list.head?.next?.value)
        assertEquals(30, list.head?.next?.next?.value)
    }

    @Test
    fun `getPreviousNodeOfIndex should throw exception for negative index`() {
        val exception = assertThrows<IndexOutOfBoundsException> {
            list.getPreviousNodeOfIndex(-1)
        }
        assertEquals("Index &-1 is negative", exception.message)
    }

    @Test
    fun `getPreviousNodeOfIndex should throw exception for out-of-bounds index`() {
        list.insertAtEnd(10) // size = 1, lastIndex = 0
        val exception = assertThrows<IndexOutOfBoundsException> {
            // Try to get index 1, which is > lastIndex
            list.getPreviousNodeOfIndex(1)
        }
        assertEquals("Index 1 passed is more than lastIndex 0. Hence it is not reachable", exception.message)
    }

    @Test
    fun `getPreviousNodeOfIndex should return correct node for valid index`() {
        list.insertAtEnd(10)
        list.insertAtEnd(20)
        list.insertAtEnd(30) // list is 10 -> 20 -> 30

        val nodeAtIndex1 = list.getPreviousNodeOfIndex(1)
        assertEquals(20, nodeAtIndex1?.value)

        val nodeAtLastIndex = list.getPreviousNodeOfIndex(list.lastIndex)
        assertEquals(30, nodeAtLastIndex?.value)
    }
}