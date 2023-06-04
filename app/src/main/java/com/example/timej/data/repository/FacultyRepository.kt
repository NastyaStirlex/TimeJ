package com.example.timej.data.repository

import android.util.Log
import com.example.timej.data.callbacks.GetFacultiesCallback
import com.example.timej.data.net.MainApiService
import com.example.timej.data.dto.FacultyDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class FacultyRepository @Inject constructor(
    private val mainApiService: MainApiService
) {
    private var callGetFaculties: Call<List<FacultyDto>>? = null

    fun getFaculties(callback: GetFacultiesCallback<List<FacultyDto>>) {
        callGetFaculties = mainApiService.getFaculties()
        callGetFaculties?.enqueue(
            object : Callback<List<FacultyDto>> {

                override fun onResponse(
                    call: Call<List<FacultyDto>>,
                    response: Response<List<FacultyDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }

                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                faculties = it
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

                override fun onFailure(call: Call<List<FacultyDto>>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
    }
}