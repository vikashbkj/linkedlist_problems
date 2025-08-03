import org.example.MyLinkedList
import org.example.Node
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class MyLinkedListTest {
    private lateinit var list: MyLinkedList<Int>

    @BeforeEach
    fun setUp() {
        list = MyLinkedList()
    }

    @Test
    fun `new list should be empty`() {
        assertEquals(0, list.size)
        // Internal properties can be checked via reflection, but for this case,
        // behavior testing is sufficient. `deleteAtFirst` returning null implies an empty list.
        assertNull(list.deleteAtFirst(), "Deleting from an empty list should return null")
    }

    @Test
    fun `insertAtFirst on empty list should set head and tail`() {
        list.insertAtFirst(10)
        assertEquals(1, list.size)
        val deleted = list.deleteAtFirst()
        assertEquals(10, deleted?.value)
        assertEquals(0, list.size)
    }

    @Test
    fun `insertAtFirst on non-empty list should update head only`() {
        list.insertAtFirst(10)
        list.insertAtFirst(20)
        assertEquals(2, list.size)
        assertEquals(20, list.deleteAtFirst()?.value)
        assertEquals(10, list.deleteAtFirst()?.value)
    }

    @Test
    fun `insertAtLast on empty list should behave like insertAtFirst`() {
        list.insertAtLast(10)
        assertEquals(1, list.size)
        assertEquals(10, list.deleteAtFirst()?.value)
    }

    @Test
    fun `insertAtLast on non-empty list should update tail`() {
        list.insertAtLast(10)
        list.insertAtLast(20)
        assertEquals(2, list.size)
        assertEquals(10, list.deleteAtFirst()?.value)
        assertEquals(20, list.deleteAtFirst()?.value)
    }

    @Test
    fun `insertAtIndex at 0 should be like insertAtFirst`() {
        list.insertAtLast(20)
        list.insertAtIndex(0, 10)
        assertEquals(2, list.size)
        assertEquals(10, list.deleteAtFirst()?.value)
        assertEquals(20, list.deleteAtFirst()?.value)
    }

    @Test
    fun `insertAtIndex at size should be like insertAtLast`() {
        list.insertAtFirst(10)
        list.insertAtIndex(1, 20)
        assertEquals(2, list.size)
        assertEquals(10, list.deleteAtFirst()?.value)
        assertEquals(20, list.deleteAtFirst()?.value)
    }

    @Test
    fun `insertAtIndex in the middle should place element correctly`() {
        list.insertAtLast(10)
        list.insertAtLast(30)
        list.insertAtIndex(1, 20)
        assertEquals(3, list.size)
        assertEquals(10, list.deleteAtFirst()?.value)
        assertEquals(20, list.deleteAtFirst()?.value)
        assertEquals(30, list.deleteAtFirst()?.value)
    }

    @Test
    fun `insertAtIndex with invalid index should throw IndexOutOfBoundsException`() {
        assertThrows<IndexOutOfBoundsException> { list.insertAtIndex(-1, 10) }
        assertThrows<IndexOutOfBoundsException> { list.insertAtIndex(1, 10) }
    }

    @Test
    fun `deleteAtFirst on empty list should return null`() {
        assertNull(list.deleteAtFirst())
        assertEquals(0, list.size)
    }

    @Test
    fun `deleteAtFirst on list with one element should make list empty`() {
        list.insertAtFirst(10)
        val deleted = list.deleteAtFirst()
        assertEquals(10, deleted?.value)
        assertEquals(0, list.size)
        assertNull(list.deleteAtFirst(), "List should be empty after deleting the only element")
    }

    @Test
    fun `deleteAtLast on empty list should return null`() {
        assertNull(list.deleteAtLast())
    }

    @Test
    fun `deleteAtLast on list with one element should make list empty`() {
        list.insertAtFirst(10)
        val deleted = list.deleteAtLast()
        assertEquals(10, deleted?.value)
        assertEquals(0, list.size)
    }

    @Test
    fun `deleteAtLast on list with multiple elements should remove last element`() {
        list.insertAtLast(10)
        list.insertAtLast(20)
        list.insertAtLast(30)
        val deleted = list.deleteAtLast()
        assertEquals(30, deleted?.value)
        assertEquals(2, list.size)
        // Check that the new last element is correct
        assertEquals(20, list.deleteAtLast()?.value)
        assertEquals(1, list.size)
    }

    @Test
    fun `deleteAtIndex at 0 should be like deleteAtFirst`() {
        list.insertAtLast(10)
        list.insertAtLast(20)
        val deleted = list.deleteAtIndex(0)
        assertEquals(10, deleted?.value)
        assertEquals(1, list.size)
        assertEquals(20, list.deleteAtFirst()?.value)
    }

    @Test
    fun `deleteAtIndex at last index should be like deleteAtLast`() {
        list.insertAtLast(10)
        list.insertAtLast(20)
        val deleted = list.deleteAtIndex(1)
        assertEquals(20, deleted?.value)
        assertEquals(1, list.size)
        assertEquals(10, list.deleteAtFirst()?.value)
    }

    @Test
    fun `deleteAtIndex in the middle should remove correct element`() {
        list.insertAtLast(10)
        list.insertAtLast(20)
        list.insertAtLast(30)
        val deleted = list.deleteAtIndex(1)
        assertEquals(20, deleted?.value)
        assertEquals(2, list.size)
        assertEquals(10, list.deleteAtFirst()?.value)
        assertEquals(30, list.deleteAtFirst()?.value)
    }

    @Test
    fun `deleteAtIndex with invalid index should throw IndexOutOfBoundsException`() {
        list.insertAtFirst(10)
        assertThrows<IndexOutOfBoundsException> { list.deleteAtIndex(-1) }
        assertThrows<IndexOutOfBoundsException> { list.deleteAtIndex(1) } // index >= size
        assertThrows<IndexOutOfBoundsException> { list.deleteAtIndex(2) }
    }

    @Test
    fun `getPreviousNodeOfIndex should return correct node`() {
        list.insertAtLast(10) // index 0
        list.insertAtLast(20) // index 1
        list.insertAtLast(30) // index 2

        assertNull(list.getPreviousNodeOfIndex(0))
        assertEquals(10, list.getPreviousNodeOfIndex(1)?.value)
        assertEquals(20, list.getPreviousNodeOfIndex(2)?.value)
    }

    @Test
    fun `getPreviousNodeOfIndex with invalid index should throw IndexOutOfBoundsException`() {
        list.insertAtLast(10)
        assertThrows<IndexOutOfBoundsException> { list.getPreviousNodeOfIndex(-1) }
        assertThrows<IndexOutOfBoundsException> { list.getPreviousNodeOfIndex(2) } // index > size
    }

    @Test
    fun `node toString should format correctly`() {
        val node = Node(100)
        assertEquals(" 100 ", node.toString())
    }

    @Nested
    @DisplayName("reverseLinkedList()")
    inner class ReverseLinkedList {

        @Test
        fun `reversing an empty list should do nothing`() {
            // Act
            list.reverseLinkedList()

            // Assert
            assertEquals(0, list.size)
            assertNull(list.deleteAtFirst(), "List should remain empty")
        }

        @Test
        fun `reversing a single-element list should not change the list`() {
            // Arrange
            list.insertAtFirst(10)

            // Act
            list.reverseLinkedList()

            // Assert
            assertEquals(1, list.size)
            val node = list.deleteAtFirst()
            assertEquals(10, node?.value)
            assertTrue(list.isEmpty(), "List should be empty after deleting the only element")
        }
        @Test
        fun `reversing a two-element list should swap the elements`() {
            // Arrange
            list.insertAtLast(10)
            list.insertAtLast(20) // List: 10 -> 20

            // Act
            list.reverseLinkedList() // List should be: 20 -> 10

            // Assert
            assertEquals(2, list.size)
            assertEquals(20, list.deleteAtFirst()?.value, "New head should be 20")
            assertEquals(10, list.deleteAtFirst()?.value, "New tail should be 10")
        }

        @Test
        fun `reversing a multi-element list should reverse the order completely`() {
            // Arrange
            list.insertAtLast(10)
            list.insertAtLast(20)
            list.insertAtLast(30)
            list.insertAtLast(40) // List: 10 -> 20 -> 30 -> 40

            // Act
            list.reverseLinkedList() // List should be: 40 -> 30 -> 20 -> 10

            // Assert
            assertEquals(4, list.size)
            assertEquals(40, list.deleteAtFirst()?.value)
            assertEquals(30, list.deleteAtFirst()?.value)
            assertEquals(20, list.deleteAtFirst()?.value)
            assertEquals(10, list.deleteAtFirst()?.value)
            assertTrue(list.isEmpty())
        }
    }
}