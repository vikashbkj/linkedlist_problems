package org.example

fun main() {
    val list = LinkedListTest<Int>()

//    insertItemsAtBeginning(list)
    insertItemsAtEnd(list)
    list.display()
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
