package com.example.timej.data.repository

import android.util.Log
import com.example.timej.data.callbacks.GetAuditoriumsCallback
import com.example.timej.data.net.MainApiService
import com.example.timej.data.dto.AuditoriumDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class AuditoriumRepository @Inject constructor(
    private val mainApiService: MainApiService
) {
    private var callGetAuditoriums: Call<List<AuditoriumDto>>? = null

    fun getAuditoriums(buildingId: String, callback: GetAuditoriumsCallback<List<AuditoriumDto>>) {
        callGetAuditoriums = mainApiService.getAuditoriums(buildingId = buildingId)
        callGetAuditoriums?.enqueue(
            object : Callback<List<AuditoriumDto>> {

                override fun onResponse(
                    call: Call<List<AuditoriumDto>>,
                    response: Response<List<AuditoriumDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }

                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                auditoriums = it
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
                }

                override fun onFailure(call: Call<List<AuditoriumDto>>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
    }
}