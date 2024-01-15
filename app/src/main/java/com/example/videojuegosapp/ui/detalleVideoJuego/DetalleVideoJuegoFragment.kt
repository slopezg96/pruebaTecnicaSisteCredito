package com.example.videojuegosapp.ui.detalleVideoJuego

import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.videojuegosapp.R
import com.example.videojuegosapp.comun.convertirAFormatoDiaMesAnio
import com.example.videojuegosapp.comun.makeCollapsible
import com.example.videojuegosapp.databinding.FragmentDetalleVideoJuegoBinding
import com.example.videojuegosapp.dominio.modelo.DetalleVideoJuego
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetalleVideoJuegoFragment : Fragment(R.layout.fragment_detalle_video_juego) {
    private val binding = FragmentDetalleVideoJuegoBinding.inflate(layoutInflater)
    private val vm by viewModels<DetalleVideoJuegoViewModel>()
    private val args: DetalleVideoJuegoFragmentArgs by navArgs()
    private val reviewAdapter by lazy { CapturaPantallaAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.obtenerDetalleVideoJuego(args.idVideoJuego)
            }
        }

        detalleUIState()
        irAtras()

    }

    private fun irAtras() = with(binding) {
        toolbarDetalle.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun handleDetailUiState(response: DetalleVideoJuegoState) = with(binding) {
        when (response) {
            is DetalleVideoJuegoState.Loading -> {
                shimmerDetailContainer.startShimmer()
                shimmerDetailContainer.visibility = View.VISIBLE
                nestedScrollView.isNestedScrollingEnabled = false
            }

            is DetalleVideoJuegoState.Error -> {
                shimmerDetailContainer.stopShimmer()
                shimmerDetailContainer.visibility = View.GONE
                nestedScrollView.isNestedScrollingEnabled = false
            }

            is DetalleVideoJuegoState.Success -> {
                nestedScrollView.isNestedScrollingEnabled = true
                shimmerDetailContainer.stopShimmer()
                shimmerDetailContainer.visibility = View.GONE
                setDataDetalle(response.data)
            }

            else -> {}
        }
    }

    private fun setDataDetalle(detalleVideoJuego: DetalleVideoJuego) = with(binding) {
        shimmerDetailContainer.stopShimmer()
        shimmerDetailContainer.visibility = View.GONE
        imagenDetalle.load(detalleVideoJuego.miniatura) {
            crossfade(true)
            placeholder(R.drawable.placeholder_video_juego)
        }

        descripcionDetalleTextView.makeCollapsible(3, Int.MAX_VALUE, expandMoreDrawable)

        tituloDetalle.text = detalleVideoJuego.titulo
        generoDetalle.text = detalleVideoJuego.genero
        plataformaDetalle.text = detalleVideoJuego.plataforma
        fechaLanzamiento.text =
            detalleVideoJuego.fechaLanzamiento.convertirAFormatoDiaMesAnio()
        descripcionDetalleTextView.text = detalleVideoJuego.descripcion

        if (detalleVideoJuego.requisitosMinimosSistema == null) {
            requerimientosSistemaConstraintLayout.visibility = View.GONE
            viewLineAbout.visibility = View.GONE
            viewLineRequerimientosSistema.visibility = View.GONE
        }
        sistemaRequeridoOS.text = detalleVideoJuego.requisitosMinimosSistema.os
        sistemaRequeridoCPU.text =
            detalleVideoJuego.requisitosMinimosSistema.processor ?: ""
        sistemaRequeridoRAM.text = detalleVideoJuego.requisitosMinimosSistema.memory ?: ""
        sistemaRequeridoStorage.text =
            detalleVideoJuego.requisitosMinimosSistema.storage ?: ""
        sistemaRequeridoGraphics.text =
            detalleVideoJuego.requisitosMinimosSistema.graphics ?: ""

        capturasPantallaRecyclerView.adapter = reviewAdapter
        reviewAdapter.submitList(detalleVideoJuego.capturasPantalla ?: emptyList())

        if (detalleVideoJuego.capturasPantalla.isNullOrEmpty()) {
            capturasPantallaTextView.visibility = View.GONE
        }

        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

            if (scrollY >= tituloDetalle.top + tituloDetalle.height) {
                toolbarDetalle.title = tituloDetalle.text
            } else {
                toolbarDetalle.title = ""
            }
        })
    }

    private fun detalleUIState() {
        viewLifecycleOwner.lifecycleScope.launch {
            vm.detalleData.collect { response ->
                handleDetailUiState(response)
            }
        }
    }
}
