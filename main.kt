import factory.FactoryType;
import structure.BinaryTreeArray;
import prototype.ProtoType;

import structure.DoWith
import view.GUI





fun main(args: Array<String>) {
    // Здесь выполняются все операции одним потоком
    // Здесь выполняются все операции одним потоком
    val factoryType = FactoryType()
    var protoType: ProtoType?
    var btsArray: BinaryTreeArray

    //СД для ТД Integer

    //СД для ТД Integer
    println("--------------TEST FOR INTEGER-------------")
    protoType = factoryType.getBuilderByName("Integer")
    btsArray = BinaryTreeArray(protoType!!.typeComparator)

    btsArray.addValue(protoType!!.create()!!)
    btsArray.addValue(protoType!!.create()!!)
    btsArray.addValue(protoType!!.create()!!)
    btsArray.addValue(protoType!!.create()!!)
    btsArray.addValue(protoType!!.create()!!)
    btsArray.addValue(protoType!!.create()!!)
    btsArray.addValue(protoType!!.create()!!)

    println("---------PRINT TREE---------")
    btsArray.printTree()

    println("---------PRINT ARRAY--------")
    btsArray.printArray()

    println("\n----GET VALUE BY INDEX 2----")
    println("value = " + btsArray.getDataAtIndex(2).toString())

    println("---DELETE VALUE BY INDEX 2--")
    btsArray.removeNodeByIndex(2)
    btsArray.printTree()

    println("-----SAVE IN BINARY FILE----")
    btsArray.save()

    println("-----------BALANCE----------")
    btsArray = btsArray.balance()
    btsArray.printTree()

    println("---LOAD FROM BINARY FILE----")
    btsArray = btsArray.load()!!
    btsArray.printTree()

    println("---------FOR EACH-----------")
    btsArray.forEach(object : DoWith {
        override fun doWith(obj: Any?) {
            val x = obj
            println(x)
        }
    })

    //СД для ТД DateTime

    //СД для ТД DateTime
    println("----------TEST FOR DATETIME-----------")
    protoType = factoryType.getBuilderByName("DateTime")
    btsArray = BinaryTreeArray(protoType!!.typeComparator)

    btsArray.addValue(protoType!!.create()!!)
    btsArray.addValue(protoType!!.create()!!)
    btsArray.addValue(protoType!!.create()!!)
    btsArray.addValue(protoType!!.create()!!)
    btsArray.addValue(protoType!!.create()!!)
    btsArray.addValue(protoType!!.create()!!)
    btsArray.addValue(protoType!!.create()!!)

    println("---------PRINT TREE---------")
    btsArray.printTree()

    println("---------PRINT ARRAY--------")
    btsArray.printArray()

    println("\n----GET VALUE BY INDEX 2----")
    println("value = " + btsArray.getDataAtIndex(2).toString())

    println("---DELETE VALUE BY INDEX 2--")
    btsArray.removeNodeByIndex(2)
    btsArray.printTree()

    println("-----SAVE IN BINARY FILE----")
    btsArray.save()

    println("-----------BALANCE----------")
    btsArray = btsArray.balance()
    btsArray.printTree()

    println("---LOAD FROM BINARY FILE----")
    btsArray = btsArray.load()!!
    btsArray.printTree()

    println("---------FOR EACH-----------")
    btsArray.forEach(object : DoWith {
        override fun doWith(obj: Any?) {
            val x = obj
            println(x)
        }
    })
    val gui = GUI()
    gui.showGui()
}