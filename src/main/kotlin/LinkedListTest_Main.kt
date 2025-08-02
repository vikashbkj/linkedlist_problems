package org.example

fun main() {
    val list = LinkedListTest<Int>()

//    insertItemsAtBeginning(list)
//    insertItemsAtEnd(list)

    insertItemsAtIndex(list)
    list.display()
}

fun insertItemsAtIndex(list: LinkedListTest<Int>) {
    list.insertAtIndex(0, 10)
    list.insertAtIndex(0, 20)
//    list.insertAtIndex(1, 17)
//    list.insertAtIndex(list.lastIndex, 30)
    list.insertAtIndex(0, 40)
    list.insertAtIndex(2, 50)
    list.insertAtIndex(1, 17)
    list.insertAtIndex(list.size, 100)
}

fun insertItemsAtBeginning(list: LinkedListTest<Int>) {
    println("Inserting items at beginning")

    list.insertAtBeginning(10)
    list.insertAtBeginning(20)
    list.insertAtBeginning(30)
    list.insertAtBeginning(40)
    list.insertAtBeginning(50)

    println("Size of list :: ${list.size}")
}

fun insertItemsAtEnd(list: LinkedListTest<Int>) {
    println("Inserting items at beginning")

    list.insertAtEnd(10)
    list.insertAtEnd(20)
    list.insertAtEnd(30)
    list.insertAtEnd(40)
    list.insertAtEnd(50)

    println("Size of list :: ${list.size}")
}
