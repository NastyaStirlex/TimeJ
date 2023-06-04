package com.example.timej.utils

import android.text.format.DateFormat
import java.util.*

fun getCurrentWeekStart(mCalendar: Calendar): String {
    val date = Date()
    mCalendar.time = date

    // 1 = Sunday, 2 = Monday, etc.
    val day_of_week: Int = mCalendar.get(Calendar.DAY_OF_WEEK)
    val monday_offset: Int
    if (day_of_week == 1) {
        monday_offset = -6
    } else {
        monday_offset = 2 - day_of_week // need to minus back
    }
    mCalendar.add(Calendar.DAY_OF_YEAR, monday_offset)
    val mDateMonday: Date = mCalendar.time

    // return 5 the next days of current day (object cal save current day)
    mCalendar.add(Calendar.DAY_OF_YEAR, 5)
    val mDateSunday: Date = mCalendar.time

    //Get format date
    val monday: String = DateFormat.format("yyyy-MM-dd", mDateMonday).toString()
    val sunday: String = DateFormat.format("yyyy-MM-dd", mDateSunday).toString()

    return monday
}

fun getCurrentWeekEnd(mCalendar: Calendar): String {
    val date = Date()
    mCalendar.time = date

    // 1 = Sunday, 2 = Monday, etc.
    val day_of_week: Int = mCalendar.get(Calendar.DAY_OF_WEEK)
    val monday_offset: Int
    if (day_of_week == 1) {
        monday_offset = -6
    } else {
        monday_offset = 2 - day_of_week // need to minus back
    }
    mCalendar.add(Calendar.DAY_OF_YEAR, monday_offset)
    val mDateMonday: Date = mCalendar.time

    // return 5 the next days of current day (object cal save current day)
    mCalendar.add(Calendar.DAY_OF_YEAR, 5)
    val mDateSunday: Date = mCalendar.time

    //Get format date
    val monday: String = DateFormat.format("yyyy-MM-dd", mDateMonday).toString()
    val sunday: String = DateFormat.format("yyyy-MM-dd", mDateSunday).toString()

    return sunday
}

fun getCurrentWeekEndDay(mCalendar: Calendar): String {
    val date = Date()
    mCalendar.time = date

    // 1 = Sunday, 2 = Monday, etc.
    val day_of_week: Int = mCalendar.get(Calendar.DAY_OF_WEEK)
    val monday_offset: Int
    if (day_of_week == 1) {
        monday_offset = -6
    } else {
        monday_offset = 2 - day_of_week // need to minus back
    }
    mCalendar.add(Calendar.DAY_OF_YEAR, monday_offset)

    // return 5 the next days of current day (object cal save current day)
    mCalendar.add(Calendar.DAY_OF_YEAR, 5)
    val mDateSunday: Date = mCalendar.time

    //Get format date
    val saturday: String = DateFormat.format("dd", mDateSunday).toString()

    return saturday
}

fun getCurrentWeekEndMonth(mCalendar: Calendar): String {
    val date = Date()
    mCalendar.time = date

    // 1 = Sunday, 2 = Monday, etc.
    val day_of_week: Int = mCalendar.get(Calendar.DAY_OF_WEEK)
    val monday_offset: Int
    if (day_of_week == 1) {
        monday_offset = -6
    } else {
        monday_offset = 2 - day_of_week // need to minus back
    }
    mCalendar.add(Calendar.DAY_OF_YEAR, monday_offset)

    // return 5 the next days of current day (object cal save current day)
    mCalendar.add(Calendar.DAY_OF_YEAR, 5)
    val mDateSunday: Date = mCalendar.time

    //Get format date
    val saturday: String = DateFormat.format("MMM", mDateSunday).toString()

    return saturday
}

fun getCurrentWeekStartDay(mCalendar: Calendar): String {
    val date = Date()
    mCalendar.time = date

    // 1 = Sunday, 2 = Monday, etc.
    val day_of_week: Int = mCalendar.get(Calendar.DAY_OF_WEEK)
    val monday_offset: Int
    if (day_of_week == 1) {
        monday_offset = -6
    } else {
        monday_offset = 2 - day_of_week // need to minus back
    }
    mCalendar.add(Calendar.DAY_OF_YEAR, monday_offset)
    val mDateMonday: Date = mCalendar.time

    // return 5 the next days of current day (object cal save current day)
    mCalendar.add(Calendar.DAY_OF_YEAR, 5)

    //Get format date
    val mondayDay: String = DateFormat.format("dd", mDateMonday).toString()

    return mondayDay
}

fun getCurrentWeekStartMonth(mCalendar: Calendar): String {
    val date = Date()
    mCalendar.time = date

    // 1 = Sunday, 2 = Monday, etc.
    val day_of_week: Int = mCalendar.get(Calendar.DAY_OF_WEEK)
    val monday_offset: Int
    if (day_of_week == 1) {
        monday_offset = -6
    } else {
        monday_offset = 2 - day_of_week // need to minus back
    }
    mCalendar.add(Calendar.DAY_OF_YEAR, monday_offset)
    val mDateMonday: Date = mCalendar.time

    // return 5 the next days of current day (object cal save current day)
    mCalendar.add(Calendar.DAY_OF_YEAR, 5)

    //Get format date
    val mondayMonth = DateFormat.format("MMM", mDateMonday).toString()

    return mondayMonth
}