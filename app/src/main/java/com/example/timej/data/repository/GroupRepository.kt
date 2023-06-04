package com.example.timej.data.repository

import android.util.Log
import com.example.timej.data.callbacks.GetGroupsCallback
import com.example.timej.data.net.MainApiService
import com.example.timej.data.dto.GroupDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class GroupRepository @Inject constructor(
    private val mainApiService: MainApiService
) {
    private var callGetGroups: Call<List<GroupDto>>? = null

    fun getGroups(facultyId: String, callback: GetGroupsCallback<List<GroupDto>>) {
        callGetGroups = mainApiService.getGroups(facultyId = facultyId)
        callGetGroups?.enqueue(
            object : Callback<List<GroupDto>> {

                override fun onResponse(
                    call: Call<List<GroupDto>>,
                    response: Response<List<GroupDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }

                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                groups = it
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

                override fun onFailure(call: Call<List<GroupDto>>, t: Throwable) {
                    callback.onError(t.message)
                }
            }
        )
    }
}