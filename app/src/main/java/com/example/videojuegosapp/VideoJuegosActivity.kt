import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.videojuegosapp.R
import com.example.videojuegosapp.databinding.VideoJuegosActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoJuegosActivity : AppCompatActivity() {

    private lateinit var binding: VideoJuegosActivityBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = VideoJuegosActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentoContenedor) as NavHostFragment
        navController = navHostFragment.navController

    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }

}