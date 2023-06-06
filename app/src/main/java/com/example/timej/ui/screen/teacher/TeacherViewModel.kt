package com.example.timej.ui.screen.teacher

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timej.calendar
import com.example.timej.data.dto.ScheduleDayDto
import com.example.timej.data.repository.ScheduleRepository
import com.example.timej.data.repository.UserAuthRepository
import com.example.timej.data.data_classes.Day
import com.example.timej.data.net.Event
import com.example.timej.utils.getCurrentWeekEnd
import com.example.timej.utils.getCurrentWeekStart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.joda.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TeacherViewModel @Inject constructor(
    userAuthRepository: UserAuthRepository,
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    val teacherScreenState = mutableStateOf<Event<String>>(Event.default())

    val _weekdaysList = MutableLiveData<List<Day>>()

    init {
        initCurrentWeek()

        val teacherName = userAuthRepository.teacherName
        val teacherId = userAuthRepository.teacherId
        getSchedule(
            beginDate = getCurrentWeekStart(mCalendar = calendar),
            endDate = getCurrentWeekEnd(mCalendar = calendar),
            groupNumber = null,
            teacherId = teacherId,
            auditoryId = null,
            isOnline = null
        )
    }

    val sdf = LocalDate.now()

    val monday = mutableStateOf<LocalDate>(sdf.withDayOfWeek(1))
    val tuesday = mutableStateOf<LocalDate>(sdf.withDayOfWeek(2))
    val wednesday = mutableStateOf<LocalDate>(sdf.withDayOfWeek(3))
    val thursday = mutableStateOf<LocalDate>(sdf.withDayOfWeek(4))
    val friday = mutableStateOf<LocalDate>(sdf.withDayOfWeek(5))
    val saturday = mutableStateOf<LocalDate>(sdf.withDayOfWeek(6))
    val sunday = mutableStateOf<LocalDate>(sdf.withDayOfWeek(7))

    private fun initCurrentWeek() {
        val sdf = LocalDate.now()

        val mon = sdf.withDayOfWeek(1)
        val tue = sdf.withDayOfWeek(2)
        val wed = sdf.withDayOfWeek(3)
        val thu = sdf.withDayOfWeek(4)
        val fri = sdf.withDayOfWeek(5)
        val sat = sdf.withDayOfWeek(6)
        val sun = sdf.withDayOfWeek(7)

        _weekdaysList.value = listOf(
            Day(mon, "Mon"),
            Day(tue, "Tue"),
            Day(wed, "Wed"),
            Day(thu, "Thu"),
            Day(fri, "Fri"),
            Day(sat, "Sat"),
            Day(sun, "Sun")
        )

    }

    private val _teacherScheduleState = MutableLiveData<List<ScheduleDayDto>>()
    val teacherScheduleState: LiveData<List<ScheduleDayDto>>
        get() = _teacherScheduleState


    private fun getSchedule(
        beginDate: String?,
        endDate: String?,
        groupNumber: String?,
        teacherId: String?,
        auditoryId: String?,
        isOnline: Boolean?
    ) = viewModelScope.launch {
        scheduleRepository.getSchedule(
            beginDate = beginDate,
            endDate = endDate,
            groupNumber = groupNumber,
            teacherId = teacherId,
            auditoryId = auditoryId,
            isOnline = isOnline,
            screenState = teacherScreenState
        )
    }

    fun getPreviousSchedule(
        beginDate: String?,
        endDate: String?,
        groupNumber: String?,
        teacherId: String?,
        auditoryId: String?,
        isOnline: Boolean?
    ) = viewModelScope.launch {
        scheduleRepository.getSchedule(
            beginDate = beginDate,
            endDate = endDate,
            groupNumber = groupNumber,
            teacherId = teacherId,
            auditoryId = auditoryId,
            isOnline = isOnline,
            screenState = teacherScreenState
        )
    }

    fun getNextSchedule(
        beginDate: String?,
        endDate: String?,
        groupNumber: String?,
        teacherId: String?,
        auditoryId: String?,
        isOnline: Boolean?
    ) = viewModelScope.launch {
        scheduleRepository.getSchedule(
            beginDate = beginDate,
            endDate = endDate,
            groupNumber = groupNumber,
            teacherId = teacherId,
            auditoryId = auditoryId,
            isOnline = isOnline,
            screenState = teacherScreenState
        )
    }
}