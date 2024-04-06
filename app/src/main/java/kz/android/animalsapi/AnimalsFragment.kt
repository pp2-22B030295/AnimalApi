package kz.android.animalsapi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kz.android.animalsapi.api.RetrofitModule
import kz.android.animalsapi.databinding.FragmentAnimalsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalsFragment:Fragment() {
    private var _binding: FragmentAnimalsBinding? = null
    private val binding get() = _binding!!
    private val adapter = AnimalAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimalsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())


        binding.searchBtn.setOnClickListener {
            performSearch(binding.searchEt.text.toString())
        }


    }
    private fun performSearch(animalName: String) {
        RetrofitModule.apiService.getAnimalsByName(animalName).enqueue(object : Callback<List<Animal>> {
            override fun onResponse(call: Call<List<Animal>>, response: Response<List<Animal>>) {
                if (response.isSuccessful) {
                    adapter.submitList(response.body())
                }
                else{
                    Log.e("Response", response.errorBody().toString())
                }
            }
            override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                Log.e("Network", t.message.toString())
            }

        })
    }
}