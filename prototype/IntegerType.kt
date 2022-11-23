package prototype

import Comparator.Comparator
import Comparator.IntegerComparator
import usertype.IntegerClass
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import java.util.stream.Collectors


class IntegerType : ProtoType {
    override fun typeName(): String? {
        return "Integer"
    }

    override fun create(): Any? {
        // генерация случайных чисел [-1000; 1000]
        val min = -1000
        val max = 1000
        val rand = Random()
        return IntegerClass(rand.nextInt(max - min) + min)
    }

    override fun clone(obj: Any?): Any? {
        return (obj as IntegerClass?)?.let { IntegerClass(it.value) }
    }

    override fun readValue(inputStream: InputStream?): Any? {
        return IntegerClass(
            BufferedReader(InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n")).toInt()
        )
    }

    override fun parseValue(someString: String?): Any {
        return IntegerClass(someString!!.toInt())
    }

    override val typeComparator: Comparator
        get() = IntegerComparator()
    override fun toString(`object`: Any): String {
        return `object`.toString()
    }
}
