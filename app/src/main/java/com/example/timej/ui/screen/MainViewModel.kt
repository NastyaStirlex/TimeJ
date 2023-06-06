package com.example.timej.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timej.data.dto.*
import com.example.timej.data.repository.*
import com.example.timej.data.data_classes.Day
import com.example.timej.data.net.Event
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
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    private val screenState = mutableStateOf<Event<String>>(Event.default())
    val buildingScreenState = mutableStateOf<Event<List<BuildingDto>>>(Event.default())
    val auditoriumScreenState = mutableStateOf<Event<List<AuditoriumDto>>>(Event.default())
    val facultyScreenState = mutableStateOf<Event<List<FacultyDto>>>(Event.default())
    val groupScreenState = mutableStateOf<Event<List<GroupDto>>>(Event.default())
    val teachersScreenState = mutableStateOf<Event<List<TeacherDto>>>(Event.default())

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

    private val _scheduleState = MutableLiveData<List<ScheduleDayDto>>()
    val scheduleState: LiveData<List<ScheduleDayDto>>
        get() = _scheduleState

    fun getSchedule(
        beginDate: String?,
        endDate: String?,
        groupNumber: String?,
        teacherId: String?,
        auditoryId: String?,
        isOnline: Boolean?
    ) = viewModelScope.launch {
        try {
            val schedule = scheduleRepository.getSchedule(
                beginDate = beginDate,
                endDate = endDate,
                groupNumber = groupNumber,
                teacherId = teacherId,
                auditoryId = auditoryId,
                isOnline = isOnline,
                screenState = screenState
            )
            _scheduleState.value = schedule
        } catch (e: Exception) {
            _scheduleState.value = null
        }

    }

    fun getPreviousSchedule(
        beginDate: String?,
        endDate: String?,
        groupNumber: String?,
        teacherId: String?,
        auditoryId: String?,
        isOnline: Boolean?
    ) = viewModelScope.launch {
        try {
            val schedule = scheduleRepository.getSchedule(
                beginDate = beginDate,
                endDate = endDate,
                groupNumber = groupNumber,
                teacherId = teacherId,
                auditoryId = auditoryId,
                isOnline = isOnline,
                screenState = screenState
            )
            _scheduleState.value = schedule

        } catch (e: Exception) {
            _scheduleState.value = null
        }
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

    fun getNextSchedule(
        beginDate: String?,
        endDate: String?,
        groupNumber: String?,
        teacherId: String?,
        auditoryId: String?,
        isOnline: Boolean?
    ) = viewModelScope.launch {
        try {
            val schedule = scheduleRepository.getSchedule(
                beginDate = beginDate,
                endDate = endDate,
                groupNumber = groupNumber,
                teacherId = teacherId,
                auditoryId = auditoryId,
                isOnline = isOnline,
                screenState = screenState
            )
            _scheduleState.value = schedule
        } catch (e: Exception) {
            _scheduleState.value = null
        }
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

    private fun getBuildings() = viewModelScope.launch {
        buildingScreenState.value = Event.loading()
        try {
            val buildings = buildingRepository.getBuildings()
            _buildingsState.clear()
            _buildingsState.addAll(buildings)
            buildingScreenState.value = Event.success(buildings)
        } catch (e: Exception) {
            buildingScreenState.value = Event.success(null)
        }
    }


    fun getAuditoriums(buildingId: String) = viewModelScope.launch {
        auditoriumScreenState.value = Event.loading()
        try {
            val auditoriums = auditoriumRepository.getAuditoriums(buildingId)
            _auditoriumsState.clear()
            _auditoriumsState.addAll(auditoriums)

            auditoriumScreenState.value = Event.success(auditoriums)
        } catch (e: Exception) {
            auditoriumScreenState.value = Event.success(null)
        }


    }

    private fun getFaculties() = viewModelScope.launch {
        facultyScreenState.value = Event.loading()
        try {
            val faculties = facultyRepository.getFaculties()
            _facultiesState.clear()
            _facultiesState.addAll(faculties)

            facultyScreenState.value = Event.success(faculties)
        } catch (e: Exception) {
            facultyScreenState.value = Event.success(null)
        }
    }

    fun getGroups(facultyId: String) = viewModelScope.launch {
        groupScreenState.value = Event.loading()

        try {
            val groups = groupRepository.getGroups(facultyId)
            _groupsState.clear()
            _groupsState.addAll(groups)

            groupScreenState.value = Event.success(groups)
        } catch (e: Exception) {
            groupScreenState.value = Event.success(null)
        }
    }

    private fun getTeachers() = viewModelScope.launch {
        teachersScreenState.value = Event.loading()

        try {
            val teachers = teacherRepository.getTeachers()
            teachersScreenState.value = Event.success(teachers)
        } catch (e: Exception) {
            teachersScreenState.value = Event.success(null)
        }

    }
}