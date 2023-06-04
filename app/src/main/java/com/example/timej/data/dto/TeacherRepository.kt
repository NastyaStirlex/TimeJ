package com.example.timej.data.dto

import android.util.Log
import com.example.timej.data.callbacks.GetTeachersCallback
import com.example.timej.data.net.MainApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class TeacherRepository @Inject constructor(
    private val mainApiService: MainApiService
) {
    private var callGetTeachers: Call<List<TeacherDto>>? = null

    fun getTeachers(callback: GetTeachersCallback<List<TeacherDto>>) {
        callGetTeachers = mainApiService.getTeachers()
        callGetTeachers?.enqueue(
            object : Callback<List<TeacherDto>> {
                override fun onResponse(
                    call: Call<List<TeacherDto>>,
                    response: Response<List<TeacherDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }

                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                teachers = it
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

                override fun onFailure(call: Call<List<TeacherDto>>, t: Throwable) {
                    callback.onError(t.message)
                }

            }
        )
    }
}