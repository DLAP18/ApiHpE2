package com.dlap2023.apihpe2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dlap2023.apihpe2.R
import com.dlap2023.apihpe2.databinding.ActivityCharactersDetailsBinding
import com.dlap2023.apihpe2.model.CharacterDetail
import com.dlap2023.apihpe2.network.HpApi
import com.dlap2023.apihpe2.network.RetrofitService
import com.dlap2023.apihpe2.utils.Constants
import com.dlap2023.apihpe2.adapters.CharactersAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersDetails : AppCompatActivity() {
    private lateinit var binding: ActivityCharactersDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        var id = ""

        if(bundle != null){
            id = bundle.getString("id", "")
        }
        //val id = bundle?.getString("id","")

        val call = RetrofitService.getRetrofit().create(HpApi::class.java).getHpCharacterDetail(id)
        //Toast.makeText(this, "Click en el elemento con id: ${id}", Toast.LENGTH_LONG).show()
        var nombres: String? = ""


        call.enqueue(object: Callback<CharacterDetail>{
            override fun onResponse(
                call: Call<CharacterDetail>,
                response: Response<CharacterDetail>
            ) {
                //Log.d("RESPONSE", "Personaje: ${response.body()!!.name}")
                binding.pbConexion.visibility = View.GONE

                Log.d(Constants.LOGTAG, resources.getString(R.string.rServidor, response.toString()))
                Log.d(Constants.LOGTAG, resources.getString(R.string.rDatos, response.body().toString()))

                binding.tvNombre.text = response.body()!!.name
                binding.tvCasaR.text = response.body()!!.house
                binding.tvFechaNacimientoR.text = response.body()!!.dateOfBirth
                binding.tvEspecieR.text = response.body()!!.species

                if(response.body()!!.wizard){
                    binding.tvMagoR.text = resources.getString(R.string.afirmacion)
                }else{
                    binding.tvMagoR.text = resources.getString(R.string.negacion)
                }

                binding.tvAscR.text = response.body()!!.ancestry
                binding.tvPatronusR.text = response.body()!!.patronus


                Glide.with(this@CharactersDetails)
                    .load(response.body()!!.image)
                    .into(binding.ivImage)
            }

            override fun onFailure(call: Call<CharacterDetail>, t: Throwable) {
                binding.pbConexion.visibility = View.GONE
                Toast.makeText(this@CharactersDetails, resources.getString(R.string.sinConexion), Toast.LENGTH_SHORT).show()
            }
        })
    }

}