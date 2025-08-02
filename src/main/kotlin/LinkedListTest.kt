package org.example

data class ListNode<T>(val value: T, var next: ListNode<T>? = null) {
    override fun toString(): String {
        return " $value "
    }
}

class LinkedListTest<T> {
    var head: ListNode<T>? = null
    var size: Int = 0
    val lastIndex get() = size - 1

    internal fun display() {
        println("Displaying linked list")

        var temp = head
        while (temp != null) {
            print(temp)
            temp = temp.next
        }
    }

    internal fun insertAtBeginning(value: T) {
        val node = ListNode(value)
        node.next = head
        head = node
        size++
    }

    internal fun getPreviousNodeOfIndex(index: Int): ListNode<T>? {
        if (index < 0) {
            throw IndexOutOfBoundsException("Index &$index is negative")
        }
        if (index > lastIndex) {
            throw IndexOutOfBoundsException("Index $index passed is more than lastIndex $lastIndex. Hence it is not reachable")
        }
        var temp = head
        var counter = 0
        while (counter < index) {
            temp = temp?.next
            counter++
        }
        return temp
    }

    internal fun insertAtEnd(value: T) {
        if (size == 0) {
            insertAtBeginning(value)
            return
        }
        val node = ListNode(value)
        val previous = getPreviousNodeOfIndex(lastIndex)
        previous?.next = node
        size++
    }

    /**
     * A function should always be responsible for validating its own inputs.
     * That is why validations should be handles inside insertAtIndex() functions rather than relying on
     * validation from getPreviousNodeOfIndex() function
     */
    internal fun insertAtIndex(index: Int, value: T) {
        if (index < 0 || index > size) {
            throw IndexOutOfBoundsException("Invalid index $index")
        }
        if (index == 0) {
            insertAtBeginning(value)
            return
        }
        if (index == size) {
            insertAtEnd(value)
            return
        }
        val previous = getPreviousNodeOfIndex(index-1)
        val current = previous?.next
        val node = ListNode(value)
        previous?.next = node
        node.next = current
        size++
    }

}