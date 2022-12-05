package com.example.coreandroid.ui.Setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.biometric.BiometricManager
import com.crocodic.core.data.CoreSession
import com.crocodic.core.extension.openActivity
//import com.crocodic.core.extension.tos
//import com.example.coreandroid.R
import com.example.coreandroid.base.activity.BaseActivity
import com.example.coreandroid.data.constant.Const
//import com.example.coreandroid.databinding.ActivitySettingBinding
import com.example.coreandroid.ui.login.LoginActivity
import com.exsample.loginapi.R
import com.exsample.loginapi.databinding.ActivitySettingBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//@AndroidEntryPoint
//class SettingActivity : BaseActivity<ActivitySettingBinding, SettingViewModel>() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setLayoutRes(R.layout.activity_setting)
//
//        binding.hasBiometric = hasBiometricCapability()
//        binding.enableBiometric = session.getBoolean(Const.SESSION.BIOMETRIC)
//
//        binding.btnBiometric.setOnCheckedChangeListener {compoundButton, b ->
//            session.setValue(Const.SESSION.BIOMETRIC,b)
//        }
//    }
//
//    private fun hasBiometricCapability(): Boolean{
//        val biometricManager = BiometricManager.from(this)
//        return biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS
//
//    }
//    override fun onClick(v: View?) {
//        when(v) {
//            binding.btnLogout -> {
//                authLogoutSuccess()
//                openActivity<LoginActivity>()
//                finishAffinity()
//            }
//        }
//        super.onClick(v)
//    }
//}

//mas nanda
//@AndroidEntryPoint
//class SettingActivity : BaseActivity<ActivitySettingBinding, SettingViewModel>(R.layout.activity_setting) {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        //set toolbar
//       setSupportActionBar(binding.btnSetting)
//
//        //chek Biometric
//       if (hasBiometricCapability() == true) {
//           tos(R.string.support_biometric)
//       }else{
//           tos(R.string.tidak_support_biometric, false)
//       }
//
//        binding.hasBiometric = hasBiometricCapability()
//        binding.enableBiometric = session.getBoolean(Const.SESSION.BIOMETRIC)
//
//        binding.btnBiometric.setOnCheckedChangeListener { compoundButton, b ->
//            session.setValue(Const.SESSION.BIOMETRIC,b)
//        }
//    }
//
//    //chek biometric function
//    private fun hasBiometricCapability(): Boolean{
//        val biometricManager = BiometricManager.from(this)
//        return biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS
//}
//
//    override fun onClick(v: View?) {
//        when (v) {
//            binding.btnLogout -> {
//                authLogoutSuccess()
//                openActivity<LoginActivity>()
//                finishAffinity()
//            }
//        }
//
//        super.onClick(v)
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return true
//    }
//}

//
//        //set toolbar
//        setSupportActionBar(binding.toolbarSetting)
//
//        //chek Biometric
//        if (hasBiometricCapability() == true) {
//
//
//        }        }


//        binding.hasBiometric = hasBiometricCapability()
//        binding.enableBiometric = session.getBoolean(Const.SESSION.BIOMETRIC)
//
//        binding.btnBiometric.setOnCheckedChangeListener {compoundButton, b ->
//            session.setValue(Const.SESSION.BIOMETRIC,b)
//        }
//    }
//
//    private fun hasBiometricCapability(): Boolean{
//        val biometricManager = BiometricManager.from(this)
//        return biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS
//
//    }
//    override fun onClick(v: View?) {
//        when(v) {
//            binding.btnLogout -> {
//                authLogoutSuccess()
//                openActivity<LoginActivity>()
//                finishAffinity()
//            }
//        }
//        super.onClick(v)
//    }
//}

@AndroidEntryPoint
class SettingActivity : BaseActivity<ActivitySettingBinding, SettingViewModel>(R.layout.activity_setting) {

    @Inject
    lateinit var session: CoreSession

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)

        binding.hasBiometric = hasBiometricCapability()
       binding.enableBiometric = session.getBoolean(Const.BIOMETRIC)

        binding.btnBiometric.setOnCheckedChangeListener { buttonView, isChecked ->
            session.setValue(Const.BIOMETRIC, isChecked)

            binding.btnLogout.setOnClickListener {
                viewModel.logout{
                openActivity<LoginActivity>()
                    finish()
            }
        }
}

}
    private fun hasBiometricCapability():Boolean {
        val biometricManager = BiometricManager.from(this)
        return biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS
    }
}

//            viewModel.apiLogout()
//            openActivity<LoginActivity>()
//            finish()
//        }
//
//    }
