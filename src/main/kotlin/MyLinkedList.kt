package org.example

internal class Node<T>(val value: T, var next: Node<T>? = null) {
    override fun toString(): String {
        return " $value "
    }
}

class MyLinkedList<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    var size: Int = 0

    internal fun isEmpty(): Boolean {
        return this.size == 0
    }

    internal fun display() {
        println("Displaying list................")
        if (size == 0) {
            println("List is empty!")
        }
        var temp = head
        while (temp != null) {
            print(temp)
            temp = temp.next
        }
        println()
    }

    internal fun insertAtFirst(value: T) {
        val node = Node(value)
        node.next = head
        head = node
        if (tail == null) {
            tail = head
        }
        size++
    }

    internal fun insertAtLast(value: T) {
        if (head == null) {
            insertAtFirst(value)
            return
        }
        val node = Node(value)
        tail?.next = node
        tail = node
        size++
    }

    internal fun getPreviousNodeOfIndex(index: Int): Node<T>? {
        if (index == 0) {
            return null
        }
        if (index < 0 || index > size) {
            throw IndexOutOfBoundsException("Invalid index $index was passed. Size is $size. Index is not reachable")
        }
        var counter = 0
        var temp = head
        while (counter < index - 1) {
            temp = temp?.next
            counter++
        }
        return temp
    }

    /**
     * A function should handle its validation directly rather than depending on helper function for validation.
     */
    internal fun insertAtIndex(index: Int, value: T) {
        if (index < 0 || index > size) {
            throw IndexOutOfBoundsException("Invalid index $index was passed. Size is $size. Index is not reachable")
        }
        if (index == 0) {
            insertAtFirst(value)
            return
        }
        if (index == size) {
            insertAtLast(value)
            return
        }
        val previous = getPreviousNodeOfIndex(index)
        val current = previous?.next
        val node = Node(value)
        node.next = current
        previous?.next = node
        size++
    }

    internal fun deleteAtFirst(): Node<T>? {
        if (head == null) {
            return null
        }
        val temp = head
        head = head?.next
        if (head == null) {
            tail = null
        }
        size--
        return temp
    }

    /**
     *  A function should ideally prepare all its values before committing state changes.
     */
    internal fun deleteAtLast(): Node<T>? {
        if (head == null || tail == null) {
            return null
        }
        if (size == 1) {
            return deleteAtFirst()
        }
        val previous = getPreviousNodeOfIndex(size-1)
        tail = previous
        val current = previous?.next
        previous?.next = null
        size--
        return current
    }

    internal fun deleteAtIndex(index: Int): Node<T>? {
        if (index == 0) {
            return deleteAtFirst()
        }
        if (index == size -1) {
            return deleteAtLast()
        }
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Invalid index $index was passed. Size is $size. Index is not reachable")
        }
        val previous = getPreviousNodeOfIndex(index)
        val temp = previous?.next
        previous?.next = temp?.next
        size--
        return temp
    }

    /**
     * It's efficient (O(n) time, O(1) space
     */
    internal fun reverseLinkedList() {
        println("Reversing the linked list.....")

        var previous: Node<T>? = null
        var current = head
        while (current != null) {
            val nextNode = current.next
            current.next = previous
            previous = current
            current = nextNode
        }
        val tempHead = head
        head = previous
        tail = tempHead
    }

    /**
     * Rotate linked list left by k elements
     *  Time complexity of O(k), which is the best possible for this operation.
     *  The space complexity is O(1) as it uses only a few variables for pointers.
      */
    internal fun rotateLeftBy(key: Int) {
        if (size < 2) {
            return
        }
        val mKey = key % size
        if (mKey == 0) {
            return
        }

        val originalHead = head
        var previous: Node<T>? = null
        var current = head
        var counter = 0
        while (counter < mKey) {
            val nextNode = current?.next
            previous = current
            current = nextNode
            counter++
        }
        previous?.next = null
        head = current
        tail?.next = originalHead
        tail = previous
    }

    /**
     * Rotate linked list right by k elements
     */
    internal fun rotateRightBy(key: Int) {
        if (size < 2) {
            return
        }
        val mKey = key % size
        if (mKey == 0) {
            return
        }
        val originalTail = tail
        val previous = getPreviousNodeOfIndex(size-mKey)
        val current = previous?.next

        previous?.next = null
        tail = previous

        originalTail?.next = head
        head = current
    }

    /**
     * a right rotation by k is mathematically identical to a left rotation by n - k.
     */
    internal fun rotateRightBySecond(key : Int) {
        if (size < 2) {
            return
        }
        val mKey = key % size
        if (mKey == 0) {
            return
        }
        rotateLeftBy(size-mKey)
    }
}