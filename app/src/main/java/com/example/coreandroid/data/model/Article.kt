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
    val image: String?,
    @Expose
    @SerializedName("resize_image")
    val resizeImage:String?
): Parcelable {
    companion object {

        @JvmStatic
        @BindingAdapter(value = ["image", "imageThumbnail"], requireAll = false)
        fun loadImage(imageView: ImageView, image: String?, imageThumbnail: String?) {
            image?.let{
                val thumbnail = Glide
                    .with(imageView.context)
                    .load(imageThumbnail)
                    .placeholder(R.drawable.loading_image)
                    .apply(RequestOptions.centerCropTransform())

            Glide
                .with(imageView.context)
                .load(image)
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.errornotfound)
                .thumbnail(thumbnail)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView)

            Picasso.get()
                .load(image)
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.errornotfound)
                .into(imageView)
        }
    }
}
}