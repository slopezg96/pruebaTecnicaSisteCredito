package com.example.videojuegosapp.ui.videojuegos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.videojuegosapp.R
import com.example.videojuegosapp.databinding.ItemVideoJuegoBinding
import com.example.videojuegosapp.dominio.modelo.VideoJuego

class VideoJuegoAdapter(
    private val onItemClick: (item: VideoJuego) -> Unit
) : ListAdapter<VideoJuego, VideoJuegoAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemVideoJuegoBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemVideoJuegoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(videoJuego: VideoJuego) = with(binding) {
            tituloVideoJuego.text = videoJuego.titulo
            descripcionVideoJuego.text = videoJuego.descripcionCorta
            generoVideoJuego.text = videoJuego.genero
            plataformaVideoJuego.text = videoJuego.plataforma
            editorVideoJuego.text = videoJuego.editor

            imagenVideoJuego.load(videoJuego.miniatura) {
                crossfade(true)
                placeholder(R.drawable.placeholder_video_juego)
            }
            binding.root.setOnClickListener { onItemClick(videoJuego) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<VideoJuego>() {
        override fun areItemsTheSame(oldItem: VideoJuego, newItem: VideoJuego) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: VideoJuego, newItem: VideoJuego) =
            oldItem == newItem
    }
}