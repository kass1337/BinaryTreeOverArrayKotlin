package Comparator

import usertype.IntegerClass
import java.io.Serializable

class IntegerComparator : Comparator, Serializable {
    override fun compare(o1: Any?, o2: Any?): Int {
        return (o1 as IntegerClass?)!!.value - (o2 as IntegerClass?)!!.value
    }
}