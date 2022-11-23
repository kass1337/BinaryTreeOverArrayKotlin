package structure

import Comparator.*
import prototype.ProtoType
import structure.*
import java.io.*
import java.lang.ClassNotFoundException
import java.lang.Exception
import java.util.ArrayList
import java.util.Vector

class BinaryTreeArray : Serializable {
    private var arrayTree: ArrayList<Any?>?
    private var comparator: Comparator?
    private var size: Int

    // Инициализация структуры данных
    constructor() {
        size = 10
        arrayTree = ArrayList(size)
        comparator = null
    }

    constructor(comparator: Comparator?) {
        size = 10
        arrayTree = ArrayList(size)
        for (i in 0 until size) arrayTree!!.add(null)
        this.comparator = comparator
    }

    private constructor(size: Int, t: ArrayList<Any?>, c: Comparator?) {
        this.size = size
        comparator = c
        arrayTree = t
    }

    fun save(protoType: ProtoType?) {
        val writer = BufferedWriter(FileWriter("saved"))
        if (protoType != null) {
            writer.write(protoType.typeName() + "\n")
        }
        this.forEach(object : DoWith {
            override fun doWith(obj: Any?) {
                val x = obj
                if (protoType != null) {
                    writer.write(
                        """
                                    ${protoType.toString(x!!)}
                                    
                                    """.trimIndent()
                    )
                }
            }
        })
        if (writer != null) writer.close()
    }



    fun load(protoType: ProtoType?): BinaryTreeArray? {
         val fileName = "saved"
        val bts = BinaryTreeArray(this.comparator)
        try {
            BufferedReader(FileReader(fileName)).use { br ->
                var line: String?
                line = br.readLine()
                if (protoType != null) {
                    if (!protoType.typeName().equals(line)) {
                        throw Exception("Wrong file structure")
                    }
                }
                while (br.readLine().also { line = it } != null) {
                    if (protoType != null) {

                            bts.addValue(protoType.parseValue(line!!))


                    }
                }
            }
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
        return bts;
    }

    // Вcпомогательный метод вставки значения в массив
    private fun insertRecursive(current: Int, obj: Any) {
        if (current >= size) { // увеличение размерности при выходе
            size *= 2 // за пределы массива
            for (i in size / 2..size)  // с обнулением новой части
                arrayTree!!.add(null)
        }
        if (arrayTree!![current] == null) {
            arrayTree!![current] = obj
            return
        }
        if (comparator!!.compare(obj, arrayTree!![current]) < 0) insertRecursive(
            2 * current + 1,
            obj
        ) else insertRecursive(2 * current + 2, obj)
    }

    // Вставка значения в дерево
    fun addValue(value: Any) {
        insertRecursive(0, value)
    }

    private fun findRecursive(current: Int, value: Any): Any? {
        if (current > size) {
            return null
        }
        if (comparator!!.compare(value, arrayTree!![current]) == 0) return arrayTree!![current]
        return if (comparator!!.compare(value, arrayTree!![current]) < 0) findRecursive(
            2 * current + 1,
            value
        ) else findRecursive(2 * current + 2, value)
    }

    @Throws(Exception::class)
    fun findByValue(value: Any): Any {
        return findRecursive(0, value) ?: throw Exception("Binary tree has no such value")
    }

    private fun scan(current: Int, level: Int, boolTree: Boolean) {
        if (current >= size) return
        if (arrayTree!![current] == null) return
        scan(2 * current + 1, level + 1, boolTree)
        if (boolTree) {
            for (i in 0 until level) print("\t")
            print(
                """
                    ${arrayTree!![current].toString()}
                    
                    """.trimIndent()
            )
        } else print(arrayTree!![current].toString() + " ")
        scan(2 * current + 2, level + 1, boolTree)
    }
    private fun scanForSave(current: Int, level: Int, boolTree: Boolean, values: ArrayList<String>) {
        if (current >= size) return
        if (arrayTree!![current] == null) return
        scanForSave(2 * current + 1, level + 1, boolTree, values)
        if (boolTree) {
            for (i in 0 until level)

                    values.add(arrayTree!![current].toString())


        }
        scanForSave(2 * current + 2, level + 1, boolTree, values)
    }
    fun printTree() {
        scan(0, 0, true)
    }

    fun printArray() {
        scan(0, 0, false)
    }

    // Число вершин в поддереве
    private fun getSize(num: Int): Int {
        return if (num >= size || arrayTree!![num] == null) 0 else 1 + getSize(2 * num + 1) + getSize(2 * num + 2)
    }

    private fun getDataAtIndexRecursive(searchIndex: Int, help: Int): Any? {
        var searchIndex = searchIndex
        if (searchIndex >= size || searchIndex >= getSize(help)) return null
        val cntL = getSize(2 * help + 1) // число вершин в левом поддереве
        if (searchIndex < cntL) return getDataAtIndexRecursive(
            searchIndex,
            2 * help + 1
        ) // Логический номер в левом поддереве
        searchIndex -= cntL // отбросить вершины левого поддерева
        return if (searchIndex-- == 0) arrayTree!![help] else getDataAtIndexRecursive(
            searchIndex,
            2 * help + 2
        ) // Логический номер – номер текущей вершины
        // в правое поддерево с остатком Логического номера
    }

    //нумерация "слева-направо", начинается с 0, см. cprog 8.5
    fun getDataAtIndex(searchIndex: Int): Any? {
        return getDataAtIndexRecursive(searchIndex, 0)
    }

    fun removeNodeByIndex(index: Int) {
        val obj = getDataAtIndex(index)
        removeNodeByValue(0, obj)
    }

    // Функция для удаления узла из BST (array implementation)
    fun removeNodeByValue(current: Int, key: Any?) {
        if (current >= size) return

        // базовый случай: ключ не найден в дереве
        if (arrayTree!![current] == null) {
            return
        }

        // если заданный ключ меньше корневого узла, повторить для левого поддерева
        if (comparator!!.compare(key, arrayTree!![current]) < 0) {
            removeNodeByValue(2 * current + 1, key)
        } else if (comparator!!.compare(key, arrayTree!![current]) > 0) {
            removeNodeByValue(2 * current + 2, key)
        } else {
            // Случай 1: удаляемый узел не имеет потомков (это листовой узел)
            if (2 * current + 1 > size && 2 * current + 2 > size) {
                // обновить узел до null
                arrayTree!![current] = null
                return
            } else if (arrayTree!![2 * current + 1] == null && arrayTree!![2 * current + 2] == null) {
                // обновить узел до null
                arrayTree!![current] = null
                return
            } else if (arrayTree!![2 * current + 1] != null && arrayTree!![2 * current + 2] != null) {
                // найти его неупорядоченный узел-предшественник
                val helpObj = Any()
                val predecessor = findMaximumKey(2 * current + 1, helpObj)

                // копируем значение предшественника в текущий узел
                arrayTree!![current] = predecessor

                // рекурсивно удаляем предшественника
                removeNodeByValue(2 * current + 1, predecessor)
            } else {
                // выбираем дочерний узел
                if (arrayTree!![2 * current + 1] != null) { // если удаляемый узел имеет потомка в левом поддереве
                    // смещаем элементы в массиве
                    arrayShiftRecursive(current, 2 * current + 1)
                } else { // если удаляемый узел имеет потомка в правом поддереве
                    // смещаем элементы в массиве
                    arrayShiftRecursive(current, 2 * current + 2)
                }
            }
        }
    }

    private fun arrayShiftRecursive(rootIdx: Int, index: Int) {
        if (rootIdx > size || index > size) return
        if (arrayTree!![index] == null) return
        arrayTree!![rootIdx] = arrayTree!![index]
        arrayTree!![index] = null
        if (2 * index + 1 >= size || 2 * rootIdx + 2 >= size) return
        if (arrayTree!![2 * index + 1] != null) // смещаем левое поддерево
            arrayShiftRecursive(2 * rootIdx + 1, 2 * index + 1)
        if (arrayTree!![2 * index + 2] != null) // смещаем правое поддерево
            arrayShiftRecursive(2 * rootIdx + 2, 2 * index + 2)
    }

    private fun findMaximumKey(index: Int, obj: Any?): Any? {
        var obj = obj
        if (index >= size) return obj
        if (arrayTree!![index] == null) return obj
        obj = findMaximumKey(2 * index + 2, arrayTree!![index])
        return obj
    }

    // итератор forEach
    fun forEach(func: DoWith) {
        if (arrayTree == null || size <= 0) return
        val sz = getSize(0)
        val v = Vector<Int>(size)
        setHelp(v, 0)
        for (i in 0 until sz) {
            func.doWith(arrayTree!![v[i]])
        }
    }

    //рекурсивная балансировка
    private fun balance(t: Vector<Any?>, a: Int, b: Int, r: ArrayList<Any?>) {
        if (a > b) return
        if (a == b) return
        val m = a + b ushr 1 // взять строку из середины интервала
        insertRecursive(r, 0, t[m])
        balance(t, m + 1, b, r) // рекурсивно выполнить для левой и
        balance(t, a, m, r) // правой частей
    }

    //вставка для нового аррайлист при балансировке
    private fun insertRecursive(t: ArrayList<Any?>, current: Int, obj: Any?) {
        if (current >= size) { // увеличение размерности при выходе
            size *= 2 // за пределы массива
            for (i in size / 2..size)  // с обнулением новой части
                t.add(null)
        }
        if (t[current] == null) {
            t[current] = obj
            return
        }
        if (comparator!!.compare(obj, t[current]) < 0) insertRecursive(t, 2 * current + 1, obj) else insertRecursive(
            t,
            2 * current + 2,
            obj
        )
    }

    //главный метод балансировки
    fun balance(): BinaryTreeArray {
        val sz1 = getSize(0)
        val newArray = Vector<Any?>(size) //вектор индексов
        val newArrayTree = ArrayList<Any?>(size)
        for (i in 0 until size) {
            newArrayTree.add(null)
        }
        set(newArray, 0)
        balance(newArray, 0, sz1, newArrayTree)
        return BinaryTreeArray(size, newArrayTree, comparator)
    }

    //метод для добавления индексов в вектор
    private operator fun set(t: Vector<Any?>, n: Int) {
        if (n >= size || arrayTree!![n] == null) return
        set(t, 2 * n + 1)
        t.add(arrayTree!![n])
        set(t, 2 * n + 2)
    }

    //Вспомогательный метод обхода для forEach
    private fun setHelp(t: Vector<Int>, n: Int) {
        if (n >= size || arrayTree!![n] == null) return
        setHelp(t, 2 * n + 1)
        t.add(n)
        setHelp(t, 2 * n + 2)
    }

    private fun scan(current: Int, level: Int, str: String): String {
        var str = str
        if (current >= size) return str
        if (arrayTree!![current] == null) return str
        var helpStrL = String()
        helpStrL = scan(2 * current + 1, level + 1, helpStrL)
        for (i in 0 until level) helpStrL += "           "
        helpStrL += """
            ${arrayTree!![current].toString()}
            
            """.trimIndent()
        var helpStrR = String()
        helpStrR = scan(2 * current + 2, level + 1, helpStrR)
        str = helpStrL + helpStrR
        return str
    }

    override fun toString(): String {
        val str = ""
        return scan(0, 0, str)
    }
}