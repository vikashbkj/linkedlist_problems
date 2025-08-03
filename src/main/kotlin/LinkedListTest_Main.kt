package org.example

fun main() {
    val list = LinkedListTest<Int>()

//    insertItemsAtBeginning(list)
//    insertItemsAtEnd(list)
//    insertItemsAtIndex(list)

//    deleteAtItemsBeginning(list)
    deleteAtItemsAtEnd(list)
    list.display()
}

private fun insertItemsAtBeginning(list: LinkedListTest<Int>) {
    println("Inserting items at beginning")

    list.insertAtFirst(10)
//    list.insertAtFirst(20)
//    list.insertAtFirst(30)
//    list.insertAtFirst(40)
//    list.insertAtFirst(50)

    println("Size of list :: ${list.size}")
}

private fun insertItemsAtEnd(list: LinkedListTest<Int>) {
    println("Inserting items at beginning")

    list.insertAtEnd(10)
    list.insertAtEnd(20)
    list.insertAtEnd(30)
    list.insertAtEnd(40)
    list.insertAtEnd(50)

    println("Size of list :: ${list.size}")
}


private fun insertItemsAtIndex(list: LinkedListTest<Int>) {
    println("Inserting items at index")

    list.insertAtIndex(0, 10)
//    list.insertAtIndex(0, 20)
    list.insertAtIndex(1, 17)
    list.insertAtIndex(2, 50)
    list.insertAtIndex(list.size, 100)
    list.insertAtIndex(0, 40)
    list.insertAtIndex(2, 38)
    list.insertAtIndex(1, 68)
    list.insertAtIndex(list.size, 189)
    list.insertAtIndex(list.size, 105)
}

fun deleteAtItemsBeginning(list: LinkedListTest<Int>) {
    println("Deleting items at beginning")

    insertItemsAtBeginning(list)
    list.display()

    val deletedNode1 = list.deleteAtFirst()
    println("deletedNode_1 :: $deletedNode1")

    while (list.head != null) {
        val deletedNode = list.deleteAtFirst()
        println("DeletedNode :: $deletedNode")
    }
}

fun deleteAtItemsAtEnd(list: LinkedListTest<Int>) {
    println("Deleting items at end")

//    insertItemsAtBeginning(list)
    list.display()

    val deletedNode1 = list.deleteAtLast()
    println("deletedNode_1 :: $deletedNode1")

    while (list.head != null) {
        val deletedNode = list.deleteAtLast()
        println("DeletedNode :: $deletedNode")
    }
}
