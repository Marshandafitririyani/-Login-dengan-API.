package com.example.coreandroid.ui.login

import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiCode
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.api.ApiResponse
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.extension.toObject
import com.example.coreandroid.api.ApiService
import com.example.coreandroid.base.viewmodel.BaseViewModel
import com.example.coreandroid.data.room.user.User
import com.example.coreandroid.data.room.user.UserDao
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val apiService: ApiService, private val gson: Gson, private val userDao: UserDao): BaseViewModel(){
    fun login(phone: String, password: String) = viewModelScope.launch {
        _apiResponse.send(ApiResponse().responseLoading())
        ApiObserver({apiService.login(phone, password)}, false, object : ApiObserver.ResponseListener{
            override suspend fun  onSuccess(response: JSONObject) {
                val status = response.getInt(ApiCode.STATUS)
                if (status == ApiCode.SUCCESS){
                    val data = response.getJSONObject(ApiCode.DATA).toObject<User>(gson)
                    userDao.insert(data.copy(idRoom = 1))
                    _apiResponse.send(ApiResponse().responseSuccess())
                }else {
                    val message = response.getString(ApiCode.MESSAGE)
                    _apiResponse.send(ApiResponse(status = ApiStatus.ERROR, message = message ))
                }
            }
        })
    }
}
