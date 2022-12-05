package com.example.coreandroid.base.activity

import androidx.databinding.ViewDataBinding
import com.crocodic.core.base.activity.CoreActivity
import com.crocodic.core.base.viewmodel.CoreViewModel
import com.crocodic.core.data.CoreSession
import com.crocodic.core.extension.clearNotification
import com.example.coreandroid.data.room.AppDatabase
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseActivity<VB: ViewDataBinding, VM: CoreViewModel>(layoutRes: Int): CoreActivity<VB, VM>(layoutRes) {
//    @Inject
//    lateinit var gson: Gson
//
//    @Inject
//    lateinit var appDatabase: AppDatabase
//
//    @Inject
//    lateinit var session: CoreSession
//
//    override fun authLogoutSuccess() {
//       loadingDialog.dismiss()
//        expiredDialog.dismiss()
//        clearNotification()
//        GlobalScope.launch {
//            appDatabase.clearAllTables()
//        }
//    }
//
}