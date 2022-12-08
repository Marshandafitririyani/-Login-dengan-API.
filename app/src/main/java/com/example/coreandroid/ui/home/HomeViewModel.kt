package com.example.coreandroid.ui.home

import androidx.lifecycle.viewModelScope
import com.crocodic.core.api.ApiCode
import com.crocodic.core.api.ApiObserver
import com.crocodic.core.extension.toList
import com.example.coreandroid.api.ApiService
import com.example.coreandroid.base.viewmodel.BaseViewModel
import com.example.coreandroid.data.room.user.User
import com.example.coreandroid.data.room.user.UserDao
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

//@HiltViewModel
//class HomeViewModel @Inject constructor(private val userDao: UserDao): BaseViewModel() {
//    fun logout(logout: () ->Unit) = viewModelScope.launch {
//        userDao.deleteAll()
//        logout()
//    }
//}

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiService: ApiService, private val userDao: UserDao, private val gson: Gson): BaseViewModel() {

    private val _friends = Channel<List<User>>()
    val friends = _friends.receiveAsFlow()

    fun logout(logout: () -> Unit) = viewModelScope.launch {
        userDao.deleteAll()
        logout()
    }

    fun getFriends(search: String? =null) = viewModelScope.launch {
        val idUser = userDao.userLogin().id
        ApiObserver({ apiService.getFriends(idUser, search)},false, object : ApiObserver.ResponseListener{
            override suspend fun onSuccess(response: JSONObject) {
                val status = response.getInt(ApiCode.STATUS)
                if (status == ApiCode.SUCCESS){

                    val friendsArray = response.getJSONArray(ApiCode.DATA)
                    val _friendsList = friendsArray.toList<User>(gson)

                    val friendsList = if (search.isNullOrEmpty()) {
                        _friendsList
                    } else {
                        _friendsList.filter { it.name?.contains(search) == true }
                    }

                    _friends.send(friendsList)

                } else {
                    val message = response.getString(ApiCode.MESSAGE)
                }
            }
        })
    }

}