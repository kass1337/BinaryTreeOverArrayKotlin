package factory

import prototype.AvailableTypes
import prototype.ProtoType
import prototype.DateTimeType
import prototype.IntegerType
import java.util.ArrayList

class FactoryType {
    private var typeNameList : List<ProtoType> = listOf(IntegerType(), DateTimeType())

    fun getTypeNameList(): List<String?>{
        var listOfType: List<String?> = listOf()
        for (t in typeNameList){
            listOfType +=  t.typeName()
        }
        return listOfType
    }

    fun getBuilderByName(name: String): ProtoType{
        if (name == null) throw NullPointerException()
        for (userType in typeNameList){
            if (name == userType.typeName())
                return userType
        }
        throw IllegalArgumentException()
    }

}