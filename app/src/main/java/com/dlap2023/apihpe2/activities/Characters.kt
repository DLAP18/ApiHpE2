package com.dlap2023.apihpe2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dlap2023.apihpe2.R
import com.dlap2023.apihpe2.adapters.CharactersAdapter
import com.dlap2023.apihpe2.databinding.ActivityCharactersBinding
import com.dlap2023.apihpe2.databinding.ActivityMainBinding
import com.dlap2023.apihpe2.model.Character
import com.dlap2023.apihpe2.network.HpApi
import com.dlap2023.apihpe2.network.RetrofitService
import com.dlap2023.apihpe2.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class Characters : AppCompatActivity() {
    private lateinit var binding: ActivityCharactersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Obtencion de la categoria seleccionada
        val bundle = intent.extras
        var categoria = ""

        if(bundle != null){
            categoria = bundle.getString("categoria", "")
        }
        //Toast.makeText(this, "La categoria es:$categoria", Toast.LENGTH_SHORT).show()

        val call = RetrofitService.getRetrofit().create(HpApi::class.java).getHpCategoryCharacters(categoria)

        call.enqueue(object: Callback<ArrayList<Character>>{
            override fun onResponse(
                call: Call<ArrayList<Character>>,
                response: Response<ArrayList<Character>>
            ){
                binding.pbConexion.visibility = View.GONE

                Log.d(Constants.LOGTAG, resources.getString(R.string.rServidor, response.toString()))
                Log.d(Constants.LOGTAG, resources.getString(R.string.rDatos, response.body().toString()))

                binding.rvMenu.layoutManager = LinearLayoutManager(this@Characters)
                binding.rvMenu.adapter = CharactersAdapter(this@Characters, response.body()!!){selectedCharacter: Character ->
                    characterClicked(selectedCharacter)
                }
            }

            override fun onFailure(call: Call<ArrayList<Character>>, t: Throwable) {
                binding.pbConexion.visibility = View.GONE
                Toast.makeText(this@Characters, resources.getString(R.string.sinConexion), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun characterClicked(character: Character){
        //Toast.makeText(this, "Click en el elemento con nombre ${character.name}", Toast.LENGTH_SHORT).show()

        val bundle = Bundle()
        bundle.putString("id", character.id)

        val intent = Intent(this, CharactersDetails::class.java)
        intent.putExtras(bundle)

        startActivity(intent)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}