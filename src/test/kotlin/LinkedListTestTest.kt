package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LinkedListTestTest {

    private lateinit var list: LinkedListTest<Int>

    @BeforeEach
    fun setUp() {
        // A new, empty list is created before each test to ensure isolation
        list = LinkedListTest()
    }

    @Test
    @DisplayName("A new list should be empty")
    fun `new list should be empty`() {
        assertEquals(0, list.size)
        assertNull(list.head)
        assertEquals(-1, list.lastIndex)
    }

    @Nested
    @DisplayName("insertAtFirst()")
    inner class InsertAtFirst {
        @Test
        fun `should add element as head when list is empty`() {
            list.insertAtFirst(10)

            assertEquals(1, list.size)
            assertNotNull(list.head)
            assertEquals(10, list.head?.value)
            assertNull(list.head?.next)
            assertEquals(0, list.lastIndex)
        }

        @Test
        fun `should add element as new head when list is not empty`() {
            list.insertAtFirst(10) // List: 10
            list.insertAtFirst(20) // List: 20 -> 10

            assertEquals(2, list.size)
            assertEquals(20, list.head?.value)
            assertEquals(10, list.head?.next?.value)
            assertEquals(1, list.lastIndex)
        }
    }

    @Nested
    @DisplayName("insertAtEnd()")
    inner class InsertAtEnd {
        @Test
        fun `should add element as head when list is empty`() {
            list.insertAtEnd(10)

            assertEquals(1, list.size)
            assertEquals(10, list.head?.value)
            assertEquals(0, list.lastIndex)
        }

        @Test
        fun `should add element to the end of a non-empty list`() {
            list.insertAtFirst(10)
            list.insertAtEnd(20) // List: 10 -> 20

            assertEquals(2, list.size)
            assertEquals(10, list.head?.value)
            assertEquals(20, list.head?.next?.value)
            assertNull(list.head?.next?.next)
            assertEquals(1, list.lastIndex)
        }
    }

    @Nested
    @DisplayName("insertAtIndex()")
    inner class InsertAtIndex {
        @Test
        fun `should throw IndexOutOfBoundsException for negative index`() {
            assertThrows<IndexOutOfBoundsException> { list.insertAtIndex(-1, 99) }
        }

        @Test
        fun `should throw IndexOutOfBoundsException for index greater than size`() {
            list.insertAtEnd(10) // size = 1
            assertThrows<IndexOutOfBoundsException> { list.insertAtIndex(2, 99) }
        }

        @Test
        fun `should insert at beginning when index is 0`() {
            list.insertAtEnd(20)
            list.insertAtIndex(0, 10) // List: 10 -> 20

            assertEquals(2, list.size)
            assertEquals(10, list.head?.value)
            assertEquals(20, list.head?.next?.value)
        }

        @Test
        fun `should insert at end when index is equal to size`() {
            list.insertAtEnd(10)
            list.insertAtEnd(20) // List: 10 -> 20, size = 2
            list.insertAtIndex(2, 30) // Insert at index 2

            assertEquals(3, list.size)
            assertEquals(30, list.head?.next?.next?.value)
        }

        @Test
        fun `should insert in the middle of the list`() {
            list.insertAtEnd(10)
            list.insertAtEnd(30) // List: 10 -> 30
            list.insertAtIndex(1, 20) // Insert 20 at index 1 -> 10 -> 20 -> 30

            assertEquals(3, list.size)
            assertEquals(10, list.head?.value)
            assertEquals(20, list.head?.next?.value)
            assertEquals(30, list.head?.next?.next?.value)
        }
    }

    @Nested
    @DisplayName("getPreviousNodeOfIndex() [Internal Helper]")
    inner class GetPreviousNodeOfIndex {
        @Test
        fun `should return correct previous node for a middle index`() {
            list.insertAtEnd(10)
            list.insertAtEnd(20)
            list.insertAtEnd(30) // List: 10 -> 20 -> 30

            // To get node at index 2 (value 30), we need the node at index 1 (value 20)
            val previous = list.getPreviousNodeOfIndex(2)
            assertEquals(20, previous?.value)
        }

        @Test
        fun `should return the last node when index is equal to size`() {
            list.insertAtEnd(10)
            list.insertAtEnd(20) // List: 10 -> 20, size = 2

            // This is used by insertAtEnd
            val lastNode = list.getPreviousNodeOfIndex(2) // index == size
            assertEquals(20, lastNode?.value)
        }

        @Test
        fun `should return head when index is 1`() {
            list.insertAtEnd(10)
            list.insertAtEnd(20)

            val previous = list.getPreviousNodeOfIndex(1)
            assertEquals(10, previous?.value)
        }

        @Test
        fun `should exhibit buggy behavior for index 0 by returning head`() {
            // This test documents a bug/quirk in the current implementation.
            // getPreviousNodeOfIndex(0) should ideally return null, but it returns head.
            // The public methods work because they have guards that prevent calling this with index 0.
            list.insertAtEnd(10)
            val result = list.getPreviousNodeOfIndex(0)
            assertEquals(list.head, result, "Warning: getPreviousNodeOfIndex(0) incorrectly returns head.")
        }
    }
}