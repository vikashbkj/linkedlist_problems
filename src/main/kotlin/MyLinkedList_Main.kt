package org.example

fun main() {
    val list = MyLinkedList<Int>()

//    insertItemsAtBeginning(list)
//    insertItemsAtEnd(list)
//    insertItemsAtIndex(list)

//    deleteItemsAtBeginning(list)
//    deleteItemsAtEnd(list)
//    deleteItemsAtIndex(list)

//    reverseLinkedList(list)
//    rotateLinkedListLeftBy(list)
    rotateLinkedListRightBy(list)
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
    insertItemsAtBeginning(list)

    println("Inserting items at index")

    list.insertAtIndex(1, 9)
    list.insertAtIndex(1, 19)
    list.insertAtIndex(0, 29)

    println("insertItemsAtIndex :: List size is :: ${list.size}")
    list.display()
}


private fun deleteItemsAtBeginning(list: MyLinkedList<Int>) {
    insertItemsAtIndex(list)

    println("Deleting items at beginning")
    val deletedNode1 = list.deleteAtFirst()
    println("Deleted deletedNode1 :: $deletedNode1")

    while (list.size > 0) {
        val deletedNode = list.deleteAtFirst()
        println("Deleted Node :: $deletedNode")
    }
    list.display()
}

private fun deleteItemsAtEnd(list: MyLinkedList<Int>) {
    insertItemsAtIndex(list)

    println("Deleting items at end")

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

    val deletedNodeAtIndex_LAST_INDEX = list.deleteAtIndex(list.size - 1)
    println("Deleted deletedNodeAtIndex_1 :: $deletedNodeAtIndex_LAST_INDEX")

    list.display()
}

private fun reverseLinkedList(list: MyLinkedList<Int>) {
    insertItemsAtIndex(list)

    println("Reversing linked list.....")
    list.reverseLinkedList()
    list.display()
}


private fun rotateLinkedListLeftBy(list: MyLinkedList<Int>) {
    insertItemsAtBeginning(list)

    println("Rotating linked list LEFT by key...")
//    list.rotateLeftBy(0)
//    list.display()

//    list.rotateLeftBy(1)
//    list.display()

    list.rotateLeftBy(list.size + 2)
    list.display()

//    list.rotateLeftBy(2)
//    list.display()

//    list.rotateLeftBy(list.size-1)
//    list.display()
}

private fun rotateLinkedListRightBy(list: MyLinkedList<Int>) {
    insertItemsAtBeginning(list)

    println("Rotating linked list RIGHT by key...")
//    list.rotateLeftBy(0)
//    list.display()

//    list.rotateRightBy(1)
//    list.display()

//    list.rotateRightBy(list.size+2)
//    list.display()

//    list.rotateLeftBy(2)
//    list.display()

//    list.rotateRightBy(list.size-1)
//    list.display()

    /**
     * Rotate Right By Second
     */
//    list.rotateRightBySecond(list.size-1)
//    list.display()

//    list.rotateRightBySecond(1)
//    list.display()

//    list.rotateRightBySecond(list.size+2)
//    list.display()

    list.rotateRightBySecond(2)
    list.display()
}