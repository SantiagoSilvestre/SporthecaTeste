package br.com.me.san.sporthecamatch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.me.san.sporthecamatch.databinding.ActivityMainBinding
import br.com.me.san.sporthecamatch.presentation.MainViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val binding by lazy {  ActivityMainBinding.inflate(layoutInflater)  }
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getData("any")

        observe()

    }

    private fun observe() {
        viewModel.retorno.observe(this) {
            when(it) {
                MainViewModel.State.Loading -> {}
                is MainViewModel.State.Error -> {
                    Toast.makeText(this, it.error.message, Toast.LENGTH_SHORT).show()
                }
                is MainViewModel.State.Success -> {
                    val dados = it.retorno.dados[0]
                    val copaDisputada = dados.player.barras.copasDoMundoDisputadas
                    val copaVencida = dados.player.barras.copasDoMundoVencidas

                    val dec = DecimalFormat(".000")

                    binding.tvName.text = dados.player.name
                    binding.tvPosicao.text = dados.player.pos
                    binding.tvCountry.text = dados.player.country
//                    binding.tvPercentual.text = dados.player.percentual.toString()
                    binding.tvPercentual.text = dec.format(dados.player.percentual).replace(",", ".")
                    binding.tvRanking.text = copaVencida.pos.toString()
                    binding.tvClassificacao.text = copaDisputada.pos.toString()

                    binding.pbCopaDisputada.max = copaDisputada.max.toInt()
                    binding.pbCopaDisputada.progress = copaDisputada.pla.toInt()
                    binding.pbCopasVencidas.max = copaVencida.max.toInt()
                    binding.pbCopasVencidas.progress = copaVencida.pla.toInt()

                    Glide.with(this)
                        .load(dados.player.img).circleCrop().into(binding.imvUser)
                }
            }
        }
    }

}