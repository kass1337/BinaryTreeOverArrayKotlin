package prototype

import Comparator.Comparator
import Comparator.DateTimeComparator
import usertype.DateTimeClass
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.util.*
import java.util.stream.Collectors

class DateTimeType : ProtoType {
    override fun typeName(): String? {
        return "DateTime"
    }

    override fun create(): Any? {

        //генерация случайных чисел
        // дата [1; 31]
        val minDay = 1
        val maxDay = 31
        // месяц [1; 12]
        val minMonth = 1
        val maxMonth = 12
        // год [988; 2048]
        val minYear = 988
        val maxYear = 2048
        // часы [0; 23]
        val minHour = 0
        val maxHour = 23
        // минуты, секунды [0 ; 59]
        val minTime = 0
        val maxTime = 59
        val rand = Random()
        val day = rand.nextInt(maxDay - minDay) + 1
        val month = rand.nextInt(maxMonth - minMonth) + 1
        val year = rand.nextInt(maxYear - minYear) + 1
        val hour = rand.nextInt(maxHour - minHour)
        val minute = rand.nextInt(maxTime - minTime)
        val second = rand.nextInt(maxTime - minTime)
        var dateTimeValue: DateTimeClass?
        //Если рандом нам сгенерировал дату, которой быть не может, то генерируем со статичными значениями
        try {
            dateTimeValue = DateTimeClass(day, month, year, hour, minute, second)
        } catch (ex: Exception) {
            println("Bad date, generating using a static values")
            dateTimeValue = DateTimeClass()
        }
        return dateTimeValue
    }

    override fun clone(obj: Any?): Any? {
        var copyDateTime: DateTimeClass?
        try {
            copyDateTime = (obj as DateTimeClass?)?.let {
                (obj as DateTimeClass?)?.let { it1 ->
                    (obj as DateTimeClass?)?.let { it2 ->
                        (obj as DateTimeClass?)?.let { it3 ->
                            (obj as DateTimeClass?)?.let { it4 ->
                                (obj as DateTimeClass?)?.let { it5 ->
                                    DateTimeClass(
                                        it.getDay(),
                                        it1.getHour(), it2.getYear(),
                                        it3.getHour(), it4.getMinute(),
                                        it5.getSecond()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            copyDateTime = DateTimeClass()
        }
        return copyDateTime
    }

    override fun readValue(inputStream: InputStream?): Any? {
        return parseValue(
            BufferedReader(InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"))
        )
    }

    override fun parseValue(someString: String?): Any {
        val words = someString!!.split(" ".toRegex()).toTypedArray()
        val dateStr = words[0].split("/".toRegex()).toTypedArray()
        val timeStr = words[1].split(":".toRegex()).toTypedArray()
        val dateInt = arrayOfNulls<Int>(3)
        val timeInt = arrayOfNulls<Int>(3)
        for (i in 0..2) {
            dateInt[i] = dateStr[i].toInt()
            timeInt[i] = timeStr[i].toInt()
        }
        var dateTimeValue: DateTimeClass
        try {
            dateTimeValue = dateInt[1]?.let {
                dateInt[0]?.let { it1 ->
                    dateInt[2]?.let { it2 ->
                        timeInt[0]?.let { it3 ->
                            timeInt[1]?.let { it4 ->
                                timeInt[2]?.let { it5 ->
                                    DateTimeClass(
                                        it1,
                                        it, it2, it3, it4, it5
                                    )
                                }
                            }
                        }
                    }
                }
            }!!
        } catch (ex: Exception) {
            println("Bad date, generating using a static values")
            dateTimeValue = DateTimeClass()
        }
        return dateTimeValue
    }

    override val typeComparator: Comparator
        get() = DateTimeComparator()
    override fun toString(`object`: Any): String {
        return `object`.toString()
    }
}