package com.example.timej.ui.screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timej.data.callbacks.*
import com.example.timej.data.dto.*
import com.example.timej.data.repository.*
import com.example.timej.data_classes.Event
import com.example.timej.ui.screen.shedule.Day
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.joda.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val buildingRepository: BuildingRepository,
    private val auditoriumRepository: AuditoriumRepository,
    private val facultyRepository: FacultyRepository,
    private val groupRepository: GroupRepository,
    private val teacherRepository: TeacherRepository,
    private val sheduleRepository: SheduleRepository
) : ViewModel() {

    val screenState = mutableStateOf<Event<String>>(Event.default())
    val buildingScreenState = mutableStateOf<Event<String>>(Event.default())
    val auditoriumScreenState = mutableStateOf<Event<String>>(Event.default())
    val facultyScreenState = mutableStateOf<Event<String>>(Event.default())
    val groupScreenState = mutableStateOf<Event<String>>(Event.default())
    val teachersScreenState = mutableStateOf<Event<String>>(Event.default())

    val _weekdaysList = MutableLiveData<List<Day>>()

    init {
        getBuildings()
        getFaculties()
        getTeachers()
        initCurrentWeek()
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



    private val _auditoriumSearchState = mutableStateOf("")
    val auditoriumSearchState: State<String>
        get() = _auditoriumSearchState


    fun onAuditoriumSearchChange(auditorium: String) {
        _auditoriumSearchState.value = auditorium
    }


    private val _buildingSearchState = mutableStateOf("")
    val buildingSearchState: State<String>
        get() = _buildingSearchState

    fun onBuildingSearchChange(building: String) {
        _buildingSearchState.value = building
    }


    private val _facultySearchState = mutableStateOf("")
    val facultySearchState: State<String>
        get() = _facultySearchState

    fun onFacultySearchChange(faculty: String) {
        _facultySearchState.value = faculty
    }


    private val _groupSearchState = mutableStateOf("")
    val groupSearchState: State<String>
        get() = _groupSearchState

    fun onGroupSearchChange(group: String) {
        _groupSearchState.value = group
    }


    private val _teacherSearchState = mutableStateOf("")
    val teacherSearchState: State<String>
        get() = _teacherSearchState


    fun onTeacherSearchChange(teacher: String) {
        _teacherSearchState.value = teacher
    }


    private val _facultiesState = mutableStateListOf<FacultyDto>()
    val facultiesState: List<FacultyDto>
        get() = _facultiesState

    private val _groupsState = mutableStateListOf<GroupDto>()
    val groupsState: List<GroupDto>
        get() = _groupsState

    private val _buildingsState = mutableStateListOf<BuildingDto>()
    val buildingsState: List<BuildingDto>
        get() = _buildingsState

    private val _auditoriumsState = mutableStateListOf<AuditoriumDto>()
    val auditoriumsState: List<AuditoriumDto>
        get() = _auditoriumsState

    private val _teachersState = mutableStateListOf<TeacherDto>()
    val teachersState: List<TeacherDto>
        get() = _teachersState

    private val _sheduleState = MutableLiveData<List<SheduleDayDto>>()
    val sheduleState: LiveData<List<SheduleDayDto>>
        get() = _sheduleState

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
            screenState = screenState,
            object : GetSheduleCallback<List<SheduleDayDto>> {
                override fun onSuccess(shedule: List<SheduleDayDto>) {
                    _sheduleState.value = null
                    _sheduleState.value = shedule
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
            screenState = screenState,
            object : GetSheduleCallback<List<SheduleDayDto>> {
                override fun onSuccess(shedule: List<SheduleDayDto>) {
                    _sheduleState.value = null
                    _sheduleState.value = shedule
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
            screenState = screenState,
            object : GetSheduleCallback<List<SheduleDayDto>> {
                override fun onSuccess(shedule: List<SheduleDayDto>) {
                    _sheduleState.value = null
                    _sheduleState.value = shedule
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

    private fun getBuildings() = viewModelScope.launch {
        buildingScreenState.value = Event.loading()
        buildingRepository.getBuildings(
            object : GetBuildingsCallback<List<BuildingDto>> {
                override fun onSuccess(buildings: List<BuildingDto>) {
                    _buildingsState.clear()
                    _buildingsState.addAll(buildings)
                }

                override fun onError(error: String?) {
                    Log.d("Error: ", error ?: "")
                }
            }
        )
        buildingScreenState.value = Event.success(null)
    }


    fun getAuditoriums(buildingId: String) = viewModelScope.launch {
        auditoriumScreenState.value = Event.loading()
        auditoriumRepository.getAuditoriums(
            buildingId = buildingId,
            object : GetAuditoriumsCallback<List<AuditoriumDto>> {
                override fun onSuccess(auditoriums: List<AuditoriumDto>) {
                    _auditoriumsState.clear()
                    _auditoriumsState.addAll(auditoriums)
                }

                override fun onError(error: String?) {
                    Log.d("Error: ", error ?: "")
                }
            }
        )
        auditoriumScreenState.value = Event.success(null)
    }

    private fun getFaculties() = viewModelScope.launch {
        facultyScreenState.value = Event.loading()
        facultyRepository.getFaculties(
            object : GetFacultiesCallback<List<FacultyDto>> {
                override fun onSuccess(faculties: List<FacultyDto>) {
                    _facultiesState.clear()
                    _facultiesState.addAll(faculties)
                }

                override fun onError(error: String?) {
                    Log.d("Error: ", error ?: "")
                }
            }
        )
        facultyScreenState.value = Event.success(null)
    }

    fun getGroups(facultyId: String) = viewModelScope.launch {
        groupScreenState.value = Event.loading()
        groupRepository.getGroups(
            facultyId = facultyId,
            object : GetGroupsCallback<List<GroupDto>> {
                override fun onSuccess(groups: List<GroupDto>) {
                    _groupsState.clear()
                    _groupsState.addAll(groups)
                }

                override fun onError(error: String?) {
                    Log.d("Error: ", error ?: "")
                }
            }
        )
        groupScreenState.value = Event.success(null)
    }

    private fun getTeachers() = viewModelScope.launch {
        teachersScreenState.value = Event.loading()
        teacherRepository.getTeachers(
            object : GetTeachersCallback<List<TeacherDto>> {
                override fun onSuccess(teachers: List<TeacherDto>) {
                    _teachersState.clear()
                    _teachersState.addAll(teachers)
                }

                override fun onError(error: String?) {
                    Log.d("Error: ", error ?: "")
                }

            }
        )
        teachersScreenState.value = Event.success(null)
    }
}