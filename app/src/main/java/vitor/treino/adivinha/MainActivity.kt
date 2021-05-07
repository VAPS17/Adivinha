package vitor.treino.adivinha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    private val random = Random()
    private var numeroAdivinhar : Int = 0
    private var jogo : Int = 0
    private var tentativas : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        novoJogo()
    }

    private fun novoJogo() {
        numeroAdivinhar = random.nextInt(10) + 1
        tentativas = 0
        jogo++

        atualizaTentiva()
        atualizaJogo()
    }

    private fun atualizaTentiva() {
        findViewById<TextView>(R.id.textViewTentativa).text = getString(R.string.tentativa) + tentativas
    }

    private fun atualizaJogo() {
        findViewById<TextView>(R.id.textViewJogo).text = getString(R.string.jogo) + jogo
    }

    fun adivinha(view: View) {
        val editTextNumero = findViewById<EditText>(R.id.editTextNumero)
        val numero = editTextNumero.text.toString().toIntOrNull()

        when(numero){
            in 1..10 -> verificaAcertou(numero!!)
            null -> editTextNumero.error = getString(R.string.numero_invalido)
            else -> editTextNumero.error = getString(R.string.numero_entre1a10)
        }
    }

    private fun verificaAcertou(numero: Int) {
        val textViewFeedback = findViewById<TextView>(R.id.textViewFeedback)

        if (numero == numeroAdivinhar) {
            textViewFeedback.text  = getString(R.string.acertou)
            novamente()
        } else if (numeroAdivinhar > numero) {
            textViewFeedback.text  = getString(R.string.numero_maior)
        } else {
            textViewFeedback.text  = getString(R.string.numero_menor)
        }

        tentativas++
        atualizaTentiva()
    }

    private fun novamente() {
    }
}