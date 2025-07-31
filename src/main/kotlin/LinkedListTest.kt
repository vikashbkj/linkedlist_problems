package org.example

data class ListNode<T>(val value: T, var next: ListNode<T>? = null) {
    override fun toString(): String {
        return " $value "
    }
}

class LinkedListTest<T> {
    var head: ListNode<T>? = null
    var size: Int = 0

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
        val lastIndex = size-1
        val node = ListNode(value)
        val previous = getPreviousNodeOfIndex(lastIndex)
        previous?.next = node
        size++
    }

}