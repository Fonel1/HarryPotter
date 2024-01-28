package com.example.harrypoterrz

import ApiResponse
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypoterrz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var data = ArrayList<ApiResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        getAllCharacters()


    }

    private fun getAllCharacters() {
        val retrofit = ServiceBuilder.buildService(ServiceInterface::class.java)

        retrofit.getAllCharacter().enqueue(object : Callback<List<ApiResponse>> {
            override fun onResponse(call: Call<List<ApiResponse>>, response: Response<List<ApiResponse>>) {
                try {
                    Log.d("ResponseCode", "Code: ${response.code()}")
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            data.addAll(responseBody)
                            val adapter = CharacterAdapter(data, this@MainActivity)
                            adapter.setOnClickListener(object : CharacterAdapter.OnClickListener {
                                override fun onClick(position: Int, model: ApiResponse) {
                                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                                    intent.putExtra("character", model)
                                    startActivity(intent)
                                }
                            })
                            binding.recyclerview.adapter = adapter

                        } else {
                            Snackbar.make(binding.root, "Empty response body", Snackbar.LENGTH_LONG).show()
                        }
                    } else {
                        Snackbar.make(binding.root, "Failed to fetch data", Snackbar.LENGTH_LONG).show()
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    Snackbar.make(binding.root, "Error processing data", Snackbar.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<ApiResponse>>, t: Throwable) {
                Log.e("Failed", "Api Failed" + t.message)
                Snackbar.make(binding.root, "Error fetching data", Snackbar.LENGTH_LONG).show()
            }
        })
    }
}
