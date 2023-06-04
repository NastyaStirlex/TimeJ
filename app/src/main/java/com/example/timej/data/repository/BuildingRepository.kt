package com.example.timej.data.repository

import android.util.Log
import com.example.timej.data.callbacks.GetBuildingsCallback
import com.example.timej.data.net.MainApiService
import com.example.timej.data.dto.BuildingDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class BuildingRepository @Inject constructor(
    private val mainApiService: MainApiService
) {
    private var callGetBuildings: Call<List<BuildingDto>>? = null

    fun getBuildings(callback: GetBuildingsCallback<List<BuildingDto>>) {

        callGetBuildings = mainApiService.getBuildings()
        callGetBuildings?.enqueue(
            object : Callback<List<BuildingDto>> {

                override fun onResponse(
                    call: Call<List<BuildingDto>>,
                    response: Response<List<BuildingDto>>
                ) {
                    if (response.code() == 400) {
                        response.errorBody()?.let { Log.d("Error code 400", it.string()) }
                        return
                    }

                    response.body()?.let { it ->
                        if (response.isSuccessful) {
                            callback.onSuccess(
                                buildings = it
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

                override fun onFailure(call: Call<List<BuildingDto>>, t: Throwable) {
                    callback.onError(t.message)
                    //buildingScreenState.value = Event.error()
                }
            }
        )
    }
}