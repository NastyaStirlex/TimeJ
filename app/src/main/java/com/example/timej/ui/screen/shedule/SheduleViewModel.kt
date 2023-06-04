package com.example.timej.ui.screen.shedule

import androidx.lifecycle.ViewModel
import com.example.timej.data.repository.SheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SheduleViewModel @Inject constructor(
    private val sheduleRepository: SheduleRepository
) : ViewModel() {

    private var calend: Calendar = Calendar.getInstance()

    init {
        //Log.d("Start and end of cur week: ", getCurrentWeek(calend))

    }


}