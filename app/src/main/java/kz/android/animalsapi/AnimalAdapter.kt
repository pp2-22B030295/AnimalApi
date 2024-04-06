package kz.android.animalsapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.android.animalsapi.databinding.ItemAnimalBinding

class AnimalAdapter : ListAdapter<Animal, AnimalAdapter.AnimalViewHolder>(DogBreedDiffCallback()) {


    class DogBreedDiffCallback : DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAnimalBinding.inflate(layoutInflater, parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val dogBreed = getItem(position)
        holder.bind(dogBreed)
    }

    class AnimalViewHolder(private val binding: ItemAnimalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(animalInfo: Animal) {
            with(binding) {
                scientificNameTextView.text = animalInfo.taxonomy?.scientific_name ?: "Scientific name not available"

                locationTextView.text = animalInfo.locations?.joinToString(", ") ?: "Locations not available"

                val characteristics = animalInfo.characteristics
                if (characteristics != null) {
                    populationTextView.text = "Population: ${characteristics.estimated_population_size ?: "Not available"}"
                    threatTextView.text = "Threat: ${characteristics.biggest_threat ?: "Not available"}"
                    speedTextView.text = "Speed: ${characteristics.top_speed ?: "Not available"}"
                    lifespanTextView.text = "Lifespan: ${characteristics.lifespan ?: "Not available"}"
                } else {
                    populationTextView.text = "Population: Not available"
                    threatTextView.text = "Threat: Not available"
                    speedTextView.text = "Speed: Not available"
                    lifespanTextView.text = "Lifespan: Not available"
                }
            }
        }

    }
}