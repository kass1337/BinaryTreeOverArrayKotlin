package usertype

import java.io.Serializable

class IntegerClass(var value: Int) : Serializable {
    override fun toString(): String {
        return value.toString()
    }
}