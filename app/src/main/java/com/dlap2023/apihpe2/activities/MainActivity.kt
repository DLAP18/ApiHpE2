package com.dlap2023.apihpe2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.dlap2023.apihpe2.R
import com.dlap2023.apihpe2.databinding.ActivityMainBinding
import com.dlap2023.apihpe2.network.HpApi
import com.dlap2023.apihpe2.network.RetrofitService
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Seleccion de la categoria entre estudiantes y personal
        val intent = Intent(this, Characters::class.java)
        val bundle = Bundle()

        val categoriaEstudiante = resources.getString(R.string.cEstudiante)
        val categoriaPersonal = resources.getString(R.string.cEmpleado)

        binding.ivOpcEstudiante.setOnClickListener{
            bundle.putString("categoria", categoriaEstudiante)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        binding.ivOpcPersonal.setOnClickListener{
            bundle.putString("categoria", categoriaPersonal)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}