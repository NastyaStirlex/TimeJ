package com.example.timej.data.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.timej.data.callbacks.GetSheduleCallback
import com.example.timej.data.net.MainApiService
import com.example.timej.data.dto.SheduleDayDto
import com.example.timej.data_classes.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class SheduleRepository @Inject constructor(
    private val mainApiService: MainApiService
) {
    private var callGetShedule: Call<List<SheduleDayDto>>? = null

    fun getShedule(
        beginDate: String?,
        endDate: String?,
        groupNumber: String?,
        teacherId: String?,
        auditoryId: String?,
        isOnline: Boolean?,
        screenState: MutableState<Event<String>>,
        callback: GetSheduleCallback<List<SheduleDayDto>>
    ) {
        screenState.value = Event.loading()

        callGetShedule = mainApiService.getSchedule(
            beginDate = beginDate, endDate = endDate,
            groupNumber = groupNumber, teacherId = teacherId, auditoryId = auditoryId,
            isOnline = isOnline
        )

        callGetShedule?.enqueue(
            object : Callback<List<SheduleDayDto>> {
                override fun onResponse(
                    call: Call<List<SheduleDayDto>>,
                    response: Response<List<SheduleDayDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }

                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                shedule = it
                            )
                        } else {
                            Log.d("Response Code", response.errorBody().toString())
                            try {
                                val errorBody = response.errorBody()
                                callback.onError(errorBody.toString())
                            } catch (e: Exception) {
                                when (e) {
                                    is HttpException -> Log.d("Exception", "HTTP exception")
                                }
                            }
                            Log.d("Error", "${callback.onError(response.errorBody().toString())}")

                            return
                        }
                    }
                    screenState.value = Event.success("")
                }

                override fun onFailure(call: Call<List<SheduleDayDto>>, t: Throwable) {
                    callback.onError(t.message)
                }

            }
        )
    }
}