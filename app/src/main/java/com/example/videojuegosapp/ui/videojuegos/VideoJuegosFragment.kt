package com.example.videojuegosapp.ui.videojuegos

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videojuegosapp.R
import com.example.videojuegosapp.comun.HorizontalItemDecoration
import com.example.videojuegosapp.comun.VerticalItemDecoration
import com.example.videojuegosapp.databinding.FragmentoVideoJuegosBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VideoJuegosFragment : Fragment(R.layout.fragmento_video_juegos) {
    private val binding = FragmentoVideoJuegosBinding.inflate(layoutInflater)
    private val vm by viewModels<VideoJuegosViewModel>()
    private val videoJuegoAdapter by lazy {
        VideoJuegoAdapter { videoJuego ->
            val action =
                VideoJuegosFragmentDirections.actionNavigationVideoJuegosADetalle(videoJuego.id)
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        selectCategory()
        uiState()
    }

    private fun selectCategory() = with(binding) {
        /*if (checkedCategory.isEmpty()) {
            vm.getAllGame()
        }
        lifecycleScope.launch {
            vm.getCategoryAndId.collect { categoryType ->
                favoritosChip.visibility =
                    if (categoryType.checkedCategoryId == 0) View.GONE else View.VISIBLE

                checkedCategory = categoryType.checkedCategory.apply {
                    if (!this.contains("clear filter")) {
                        toolbarTextView.text = this.capitalizeFirstLetter()
                    }
                }
                checkedCategoryId = categoryType.checkedCategoryId
                updateChip(checkedCategoryId, categoryChipGroup)
                favoritosChip.visibility = if (checkedCategory == "home") View.GONE else View.VISIBLE
            }
        }
        categoryChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            checkedIds.forEach {
                checkedCategory = group.findViewById<Chip>(it).text.toString().lowercase()
                lifecycleScope.launch {
                    if (!checkedCategory.contains("clear filter")) {
                        //vm.getGameByCategory(checkedCategory)
                    }
                }

                val transition = ChangeBounds()
                transition.duration = 200
                TransitionManager.beginDelayedTransition(
                    categoryChipGroup.parent as ViewGroup,
                    transition
                )
            }
        }

        favoritosChip.setOnClickListener {
            lifecycleScope.launch {
                vm.getAllGame()
                favoritosChip.visibility = View.GONE
            }
        }*/
    }


    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        if (chipId != 0) {
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            } catch (e: Exception) {
                binding.errorVideoJuego.text = e.message
            }
        }
    }

    private fun setupRv() = with(binding) {
        videoJuegosRecycler.apply {
            adapter = videoJuegoAdapter
            setHasFixedSize(false)
            addItemDecoration(VerticalItemDecoration(26))
            addItemDecoration(HorizontalItemDecoration(42))
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(), LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun uiState() = with(binding) {
        lifecycleScope.launch {
            vm.videoJuegosLista.collectLatest { state ->
                when (state) {
                    is VideoJuegoState.Success -> {
                        errorVideoJuego.visibility = View.GONE
                        videoJuegosRecycler.visibility = View.VISIBLE
                        shimmerViewContainer.apply {
                            stopShimmer()
                            visibility = View.GONE
                        }
                        videoJuegoAdapter.submitList(state.data)
                    }

                    is VideoJuegoState.Error -> {
                        videoJuegosRecycler.visibility = View.GONE
                        errorVideoJuego.apply {
                            visibility = View.VISIBLE
                            text = state.message
                        }
                        shimmerViewContainer.apply {
                            stopShimmer()
                            visibility = View.GONE
                        }
                    }

                    is VideoJuegoState.Loading -> {
                        errorVideoJuego.visibility = View.GONE
                        videoJuegosRecycler.visibility = View.GONE
                        shimmerViewContainer.apply {
                            startShimmer()
                            visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

}