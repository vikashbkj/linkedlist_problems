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
//        if (size == 0) {
        if (head == null) {
            println("List is empty")
            return
        }

        var temp = head
        while (temp != null) {
            print(temp)
            temp = temp.next
        }
        println()
    }

    internal fun insertAtFirst(value: T) {
      val node = ListNode(value)
        node.next = head
        head = node
        size++
    }

    internal fun getPreviousNodeOfIndex(index: Int): ListNode<T>? {
        if (index < 0) {
            throw IndexOutOfBoundsException("Negative index $index is passed")
        }
        if (index > size) {
            throw IndexOutOfBoundsException("Index $index passed is more than size $size. Hence it is not reachable.")
        }
        var temp = head
        var counter = 0
        while (counter < index-1) {
            temp = temp?.next
            counter++
        }
        return temp
    }

    internal fun insertAtEnd(value: T) {
        if (size == 0) { // if the list is empty lastIndex = -1
            insertAtFirst(value)
            return
        }
        val previous = getPreviousNodeOfIndex(size)
        val node = ListNode(value)
        previous?.next = node
        size++
    }

    /**
     * A function should always be responsible for validating its own inputs.
     * That is why validations should be handles inside insertAtIndex() functions rather than relying on
     * validation from getPreviousNodeOfIndex() function
     */
    internal fun insertAtIndex(index: Int, value: T) {
       if (index < 0) {
           throw IndexOutOfBoundsException("Negative index $index is passed")
       }
        /**
         * while inserting, user can always insert at size i.e. lastIndex + 1.
         * Because element at size index needs to be referenced by node.next at lastIndex
         * and node at lastIndex is easily accessible by calling getNodeAtIndex(lastIndex)
         */

        if (index > size) {
            throw IndexOutOfBoundsException("Index $index passed is more than size $size. Hence it is not reachable.")
        }
        if (index == 0) {
            insertAtFirst(value)
            return
        }
        if (index == size) {
            insertAtEnd(value)
            return
        }
        val previous = getPreviousNodeOfIndex(index)
        val current = previous?.next

        val node = ListNode(value)
        previous?.next = node
        node.next = current
        size++
    }
}