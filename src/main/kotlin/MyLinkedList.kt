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
}

fun main() {
    val list = MyLinkedList<Int>()

//    insertItemsAtBeginning(list)
//    insertItemsAtEnd(list)
//    insertItemsAtIndex(list)

//    deleteItemsAtBeginning(list)
//    deleteItemsAtEnd(list)
    deleteItemsAtIndex(list)
}

private fun insertItemsAtBeginning(list: MyLinkedList<Int>) {
    println("Inserting items at beginning")

    list.insertAtFirst(10)
    list.insertAtFirst(20)
    list.insertAtFirst(30)
    list.insertAtFirst(40)
    list.insertAtFirst(50)

    println("insertItemsAtBeginning :: List size is :: ${list.size}")
    list.display()
}

private fun insertItemsAtEnd(list: MyLinkedList<Int>) {
    println("Inserting items at beginning")

    list.insertAtLast(10)
    list.insertAtLast(20)
    list.insertAtLast(30)
    list.insertAtLast(40)
    list.insertAtLast(50)

    println("insertItemsAtEnd :: List size is :: ${list.size}")
    list.display()
}

private fun insertItemsAtIndex(list: MyLinkedList<Int>) {
    println("Inserting items at index")

    insertItemsAtBeginning(list)

    list.insertAtIndex(1, 9)
    list.insertAtIndex(1, 19)
    list.insertAtIndex(0, 29)

    println("insertItemsAtIndex :: List size is :: ${list.size}")
    list.display()
}


private fun deleteItemsAtBeginning(list: MyLinkedList<Int>) {
    println("Deleting items at beginning")

    insertItemsAtIndex(list)

    val deletedNode1 = list.deleteAtFirst()
    println("Deleted deletedNode1 :: $deletedNode1")

    while (list.size > 0) {
        val deletedNode = list.deleteAtFirst()
        println("Deleted Node :: $deletedNode")
    }
    list.display()
}

private fun deleteItemsAtEnd(list: MyLinkedList<Int>) {
    println("Deleting items at end")

    insertItemsAtIndex(list)

    val deletedNode1 = list.deleteAtLast()
    println("Deleted deletedNode1 :: $deletedNode1")

    while (list.size > 0) {
        val deletedNode = list.deleteAtLast()
        println("Deleted Node :: $deletedNode")
    }
    list.display()
}

private fun deleteItemsAtIndex(list: MyLinkedList<Int>) {
    println("Deleting items at index")

    insertItemsAtIndex(list)

    val deletedNodeAtIndex_0 = list.deleteAtIndex(0)
    println("Deleted deletedNodeAtIndex_0 :: $deletedNodeAtIndex_0")

    list.display()

    val deletedNodeAtIndex_1 = list.deleteAtIndex(1)
    println("Deleted deletedNodeAtIndex_1 :: $deletedNodeAtIndex_1")

    list.display()

    val deletedNodeAtIndex_LAST_INDEX = list.deleteAtIndex(list.size-1)
    println("Deleted deletedNodeAtIndex_1 :: $deletedNodeAtIndex_LAST_INDEX")

    list.display()
}