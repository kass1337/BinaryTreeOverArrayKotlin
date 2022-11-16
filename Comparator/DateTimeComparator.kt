package Comparator


import usertype.DateTimeClass.*
import usertype.DateTimeClass
import java.io.Serializable

class DateTimeComparator : Comparator, Serializable {
    override fun compare(o1: Any?, o2: Any?): Int {
        val yearL = (o1 as DateTimeClass?)!!.getYear()
        val yearR = (o2 as DateTimeClass?)!!.getYear()
        if (yearL != yearR) {
            return yearL - yearR
        }
        val monthL = o1!!.getMonth()
        val monthR = o2!!.getMonth()
        if (monthL != monthR) {
            return monthL - monthR
        }
        val dayL = o1!!.getDay()
        val dayR = o2!!.getDay()
        if (dayL != dayR) {
            return dayL - dayR
        }
        val hourL = o1!!.getHour()
        val hourR = o2!!.getHour()
        val minuteL = o1!!.getMinute()
        val minuteR = o2!!.getMinute()
        val secondL = o1!!.getSecond()
        val secondR = o2!!.getSecond()
        val timeL = hourL * 60 * 60 + minuteL * 60 + secondL
        val timeR = hourR * 60 * 60 + minuteR * 60 + secondR
        return timeL - timeR
    }
}