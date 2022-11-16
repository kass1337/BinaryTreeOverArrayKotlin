package usertype

import usertype.DateTimeClass
import java.io.Serializable
import java.lang.Exception


class DateTimeClass : Serializable {
    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0
    private var second = 0

    constructor(day: Int, month: Int, year: Int, hour: Int, minute: Int, second: Int) {
        try {
            setDay(day)
            setMonth(month)
            setYear(year)
            setHour(hour)
            setMinute(minute)
            setSecond(second)
        } catch (ex: Exception) {
            println("Bad Date")
        }
        if (!isDayValid) throw Exception("Bad date")
    }

    constructor() {
        try {
            setDay(15)
            setMonth(11)
            setYear(2022)
            setHour(22)
            setMinute(52)
            setSecond(24)
        } catch (ex: Exception) {
            println("Bad Date")
        }
    }

    fun getDay(): Int {
        return day
    }

    @Throws(Exception::class)
    fun setDay(day: Int) {
        if (day < 1 || day > 31) {
            throw Exception("Bad day")
        }
        this.day = day
    }

    fun getMonth(): Int {
        return month
    }

    @Throws(Exception::class)
    fun setMonth(month: Int) {
        if (month < 1 || month > 12) {
            throw Exception("Bad month")
        }
        this.month = month
    }

    fun getYear(): Int {
        return year
    }

    @Throws(Exception::class)
    fun setYear(year: Int) {
        if (year <= 0) {
            throw Exception("Bad year")
        }
        this.year = year
    }

    fun getHour(): Int {
        return hour
    }

    @Throws(Exception::class)
    fun setHour(hour: Int) {
        if (hour < 0 || hour > 23) {
            throw Exception("Bad hour")
        }
        this.hour = hour
    }

    fun getMinute(): Int {
        return minute
    }

    @Throws(Exception::class)
    fun setMinute(minute: Int) {
        if (minute < 0 || minute > 59) {
            throw Exception("Bad minute")
        }
        this.minute = minute
    }

    fun getSecond(): Int {
        return second
    }

    @Throws(Exception::class)
    fun setSecond(second: Int) {
        if (second < 0 || second > 59) {
            throw Exception("Bad second")
        }
        this.second = second
    }

    val isLeapYear: Boolean
        get() = if (year > changeYear) {
            year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
        } else year % 4 == 0
    private val isDayValid: Boolean
        private get() = if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
            false
        } else if (month == 2 && isLeapYear && day > 29) {
            false
        } else if (month == 2 && day > 28) {
            false
        } else {
            true
        }

    override fun toString(): String {
        var total = "$day/$month/$year "
        if (hour < 10) total += "0"
        total += hour.toString()
        total += ":"
        if (minute < 10) total += "0"
        total += minute.toString()
        total += ":"
        if (second < 9) total += "0"
        total += second.toString()
        return total
    }

    companion object {
        private const val changeYear = 1582
    }
}