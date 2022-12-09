package com.example.coreandroid.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.extension.isEmptyRequired
import com.crocodic.core.extension.openActivity
import com.crocodic.core.extension.textOf
import com.example.coreandroid.R
import com.example.coreandroid.base.activity.BaseActivity
import com.example.coreandroid.databinding.ActivityProfileBinding
import com.example.coreandroid.ui.home.HomeActivity
import kotlinx.coroutines.launch

class ProfileActivity :
    BaseActivity<ActivityProfileBinding, ProfileViewModel>(R.layout.activity_profile) {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding.btnUpdate.setOnClickListener {
            if (listOf(
                    binding.edtNama,
                    binding.edtDescription
            ).isEmptyRequired(R.string.label_must_fill)
            )
                return@setOnClickListener
            val name = binding.edtNama.textOf()
            val description = binding.edtDescription.textOf()
            viewModel.update(name, binding.spSekolah.selectedItem as String, description)
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.apiResponse.collect{
                        when (it.status) {
                            ApiStatus.LOADING -> loadingDialog.show("Updating...")
                            ApiStatus.SUCCESS -> {
                                loadingDialog.dismiss()
                                openActivity<HomeActivity>()
                                finish()
                            }
                            else -> loadingDialog.setResponse(it.message ?: return@collect)
                        }
                    }
                }
            }
        }
    }
    }