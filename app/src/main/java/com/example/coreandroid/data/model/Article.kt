package com.example.coreandroid.data.model

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.coreandroid.R
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article (
    @Expose
    @SerializedName("created_at")
    val createdAt: String?,
    @Expose
    @SerializedName("title")
    val title: String?,
    @Expose
    @SerializedName("content")
    val content: String?,
    @Expose
    @SerializedName("image")
    val image: String?
): Parcelable {
    companion object {

        @JvmStatic
        @BindingAdapter("image")
        fun loadImage(imageView: ImageView, image: String?) {
            Glide
                .with(imageView.context)
                .load(image)
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.try_later)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView)

            Picasso.get()
                .load(image)
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.try_later)
                .into(imageView)
        }
    }
}