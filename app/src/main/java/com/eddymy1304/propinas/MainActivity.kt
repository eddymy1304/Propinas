package com.eddymy1304.propinas

import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eddymy1304.propinas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calcular.setOnClickListener {calcularPropina()}

    }

    private fun calcularPropina() {
        var costo_servicio = binding.costoServicio.text.toString().toDoubleOrNull() ?: 0.00
        val porcentaje = when(binding.opciones.checkedRadioButtonId){
            R.id.opcion_veinte_por_ciento -> 0.2
            R.id.opcion_dieciocho_por_ciento -> 0.18
            R.id.opcion_quince_por_ciento -> 0.15
            else -> 0.00
        }
        var resultadoPropina = costo_servicio.times(porcentaje)

        if (binding.switchRedondear.isChecked) {
            resultadoPropina = kotlin.math.ceil(resultadoPropina)
        }

        val formatoMoneda = NumberFormat.getCurrencyInstance().format(resultadoPropina)
        binding.resultado.text = getString(R.string.propina, formatoMoneda)


    }
}
