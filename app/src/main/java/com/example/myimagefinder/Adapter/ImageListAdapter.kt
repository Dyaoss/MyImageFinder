package com.example.myimagefinder.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myimagefinder.Retrofit.KakaoImageData
import com.example.myimagefinder.databinding.RecyclerviewItemBinding
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class ImageListAdapter(var itemData: MutableList<KakaoImageData>) :
    RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null


    inner class ImageViewHolder(binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val ivThumnailView = binding.itemImage
        val tvSitename = binding.itemSiteName
        val tvImageDate = binding.itemDate
        val ivlike = binding.ivLike

        fun bind(data: KakaoImageData) {
            Glide.with(itemView.context)
                .load(data.thumbnailUrl)
                .into(ivThumnailView)
            tvSitename.text = data.siteName
            tvImageDate.text = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .format(OffsetDateTime.parse(data.dateTime))
            ivlike.bringToFront()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemData.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = itemData[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
    }

    fun setData(data: MutableList<KakaoImageData>) {
        itemData = data
        notifyDataSetChanged()

    }
}