package factory

import prototype.AvailableTypes
import prototype.ProtoType
import prototype.DateTimeType
import prototype.IntegerType
import java.util.ArrayList

class FactoryType {
    val typeNameList: ArrayList<String>
        get() {
            val list = ArrayList<String>()
            for (at in AvailableTypes.values()) {
                list.add(at.toString())
            }
            return list
        }

    fun getBuilderByName(name: String?): ProtoType? {
        when (name) {
            "Integer" -> {
                return IntegerType()
            }
            "DateTime" -> {
                return DateTimeType()
            }
        }
        return null
    }
}