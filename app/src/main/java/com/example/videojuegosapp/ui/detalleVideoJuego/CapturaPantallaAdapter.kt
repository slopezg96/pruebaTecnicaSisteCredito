package com.example.videojuegosapp.ui.detalleVideoJuego

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.videojuegosapp.data.dto.CapturaPantalla
import com.example.videojuegosapp.databinding.ItemCapturaPantallaBinding
import coil.load
import com.example.videojuegosapp.R

class CapturaPantallaAdapter : ListAdapter<CapturaPantalla, CapturaPantallaAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCapturaPantallaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class ViewHolder(private val binding: ItemCapturaPantallaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(screenshot: CapturaPantalla) = with(binding) {
            capturaImageView.load(screenshot.image) {
                crossfade(true)
                placeholder(R.drawable.placeholder_video_juego)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CapturaPantalla>() {
        override fun areItemsTheSame(oldItem: CapturaPantalla, newItem: CapturaPantalla) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CapturaPantalla, newItem: CapturaPantalla) =
            oldItem == newItem
    }
}