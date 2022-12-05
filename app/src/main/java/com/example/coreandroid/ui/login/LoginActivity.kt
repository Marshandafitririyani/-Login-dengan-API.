package com.example.coreandroid.ui.login

import android.os.Bundle
import android.view.View
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.data.CoreSession
import com.crocodic.core.extension.isEmptyRequired
import com.crocodic.core.extension.openActivity
import com.crocodic.core.extension.textOf
import com.crocodic.core.extension.tos
import com.example.coreandroid.R
import com.example.coreandroid.base.activity.BaseActivity
import com.example.coreandroid.data.constant.Const
//import com.example.coreandroid.data.constant.Const.LOGIN.PHONE
import com.example.coreandroid.databinding.ActivityLoginBinding
import com.example.coreandroid.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {

    @Inject
    lateinit var session: CoreSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnLogin.setOnClickListener {
            if(binding.etPhone.isEmptyRequired(R.string.label_must_fill) || binding.etPassword.isEmptyRequired(R.string.label_must_fill)) {
                return@setOnClickListener
            }
            val phone = binding.etPhone.textOf()
            val password = binding.etPassword.textOf()

            viewModel.login(phone, password)
        }

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.apiResponse.collect{
                        when (it.status) {
                            ApiStatus.LOADING -> loadingDialog.show("Logging in...")
                            ApiStatus.SUCCESS -> {
                                loadingDialog.dismiss()
                                openActivity<HomeActivity> ()
                                finish()
                            }
                            else -> loadingDialog.setResponse(it.message ?:return@collect)
                        }
                    }
                }
            }
        }
    }

//    override fun onClick(v: View?) {
//        when (v) {
//            binding.btnSignIn -> showBiometricPrompt()
//        }
//        super.onClick(v)
//    }
//    private fun showBiometricPrompt() {
//        val builder = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
//            .setTitle("Biometric Authentication")
//            .setSubtitle("Enter biometric credentials to proceed.")
//            .setDescription("Input your Fingerprint or FaceID to ensure it's you!")
//        builder.apply {
//            setNegativeButtonText("Cancel")
//        }
//        val promptInfo = builder.build()
//        val biometricPrompt= initBiometricPrompt {success->
//            if (success){
//                loadingDialog.show("Logging in...")
//                viewModel.login(
//                    session.getString(Const.LOGIN.PHONE),
//                    session.getString(Const.LOGIN.PASSWORD)
//                )
//            }else{
//                tos("Biometric tidak ssesuai",short = false)
//            }
//        }
//        biometricPrompt.authenticate(promptInfo)
//
//    }
//
//}

    private fun showBiometricPrompt() {
       val builder = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Enter biometric credentials to proceed.")
            .setDescription("Input your Fingerprint or FaceID to ensure it's you!")
            .setNegativeButtonText("Cancel")
        val promptInfo = builder.build()
       val biometricPrompt= initBiometricPrompt{
           viewModel.login(session.getString(Const.PHONE), session.getString(Const.PASSWORD))
       }
        biometricPrompt.authenticate(promptInfo)
    }

    private fun initBiometricPrompt(listener: (Boolean)-> Unit): BiometricPrompt {
        val executor = ContextCompat.getMainExecutor(this)

        val callback = object : BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                listener(true)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                listener(false)
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                listener(false)
            }
        }

        return BiometricPrompt(this,executor, callback)

    }
}


