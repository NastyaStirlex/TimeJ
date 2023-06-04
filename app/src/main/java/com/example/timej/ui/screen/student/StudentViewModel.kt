package com.example.timej.ui.screen.student


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timej.calendar
import com.example.timej.data.callbacks.GetSheduleCallback
import com.example.timej.data.dto.SheduleDayDto
import com.example.timej.data.repository.SheduleRepository
import com.example.timej.data.repository.UserAuthRepository
import com.example.timej.data_classes.Event
import com.example.timej.utils.getCurrentWeekEnd
import com.example.timej.utils.getCurrentWeekStart
import com.example.timej.ui.screen.shedule.Day
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.joda.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    userAuthRepository: UserAuthRepository,
    private val sheduleRepository: SheduleRepository
) : ViewModel() {

    val _weekdaysList = MutableLiveData<List<Day>>()
    val studentScreenState = mutableStateOf<Event<String>>(Event.default())
    init {
        initCurrentWeek()

        val groupId = userAuthRepository.groupId
        val groupNumber = userAuthRepository.groupNumber
        getShedule(
            beginDate = getCurrentWeekStart(mCalendar = calendar),
            endDate = getCurrentWeekEnd(mCalendar = calendar),
            groupNumber = groupId,
            teacherId = null,
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

    private val _studentScheduleState = MutableLiveData<List<SheduleDayDto>>()
    val studentScheduleState: LiveData<List<SheduleDayDto>>
        get() = _studentScheduleState


    fun getShedule(
        beginDate: String?,
        endDate: String?,
        groupNumber: String?,
        teacherId: String?,
        auditoryId: String?,
        isOnline: Boolean?
    ) = viewModelScope.launch {
        sheduleRepository.getShedule(
            beginDate = beginDate,
            endDate = endDate,
            groupNumber = groupNumber,
            teacherId = teacherId,
            auditoryId = auditoryId,
            isOnline = isOnline,
            screenState = studentScreenState,
            object : GetSheduleCallback<List<SheduleDayDto>> {
                override fun onSuccess(shedule: List<SheduleDayDto>) {
                    _studentScheduleState.value = null
                    _studentScheduleState.value = shedule
                }

                override fun onError(error: String?) {
                    Log.d("Error: ", error ?: "")
                }

            }
        )
    }

    fun getPreviousShedule(
        beginDate: String?,
        endDate: String?,
        groupNumber: String?,
        teacherId: String?,
        auditoryId: String?,
        isOnline: Boolean?
    ) = viewModelScope.launch {
        sheduleRepository.getShedule(
            beginDate = beginDate,
            endDate = endDate,
            groupNumber = groupNumber,
            teacherId = teacherId,
            auditoryId = auditoryId,
            isOnline = isOnline,
            screenState = studentScreenState,
            object : GetSheduleCallback<List<SheduleDayDto>> {
                override fun onSuccess(shedule: List<SheduleDayDto>) {
                    _studentScheduleState.value = null
                    _studentScheduleState.value = shedule
                    _weekdaysList.value = listOf(
                        Day(monday.value.minusDays(7), "Mon"),
                        Day(tuesday.value.minusDays(7), "Tue"),
                        Day(wednesday.value.minusDays(7), "Wed"),
                        Day(thursday.value.minusDays(7), "Thu"),
                        Day(friday.value.minusDays(7), "Fri"),
                        Day(saturday.value.minusDays(7), "Sat"),
                        Day(sunday.value.minusDays(7), "Sun")
                    )
                    monday.value = monday.value.minusDays(7)
                    tuesday.value = tuesday.value.minusDays(7)
                    wednesday.value = wednesday.value.minusDays(7)
                    thursday.value = thursday.value.minusDays(7)
                    friday.value = friday.value.minusDays(7)
                    saturday.value = saturday.value.minusDays(7)
                    sunday.value = sunday.value.minusDays(7)
                }

                override fun onError(error: String?) {
                    Log.d("Error: ", error ?: "")
                }

            }
        )
    }

    fun getNextShedule(
        beginDate: String?,
        endDate: String?,
        groupNumber: String?,
        teacherId: String?,
        auditoryId: String?,
        isOnline: Boolean?
    ) = viewModelScope.launch {
        sheduleRepository.getShedule(
            beginDate = beginDate,
            endDate = endDate,
            groupNumber = groupNumber,
            teacherId = teacherId,
            auditoryId = auditoryId,
            isOnline = isOnline,
            screenState = studentScreenState,
            object : GetSheduleCallback<List<SheduleDayDto>> {
                override fun onSuccess(shedule: List<SheduleDayDto>) {
                    _studentScheduleState.value = null
                    _studentScheduleState.value = shedule
                    _weekdaysList.value = listOf(
                        Day(monday.value.plusDays(7), "Mon"),
                        Day(tuesday.value.plusDays(7), "Tue"),
                        Day(wednesday.value.plusDays(7), "Wed"),
                        Day(thursday.value.plusDays(7), "Thu"),
                        Day(friday.value.plusDays(7), "Fri"),
                        Day(saturday.value.plusDays(7), "Sat"),
                        Day(sunday.value.plusDays(7), "Sun")
                    )
                    monday.value = monday.value.plusDays(7)
                    tuesday.value = tuesday.value.plusDays(7)
                    wednesday.value = wednesday.value.plusDays(7)
                    thursday.value = thursday.value.plusDays(7)
                    friday.value = friday.value.plusDays(7)
                    saturday.value = saturday.value.plusDays(7)
                    sunday.value = sunday.value.plusDays(7)
                }

                override fun onError(error: String?) {
                    Log.d("Error: ", error ?: "")
                }

            }
        )
    }
}