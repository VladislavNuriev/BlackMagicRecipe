package com.example.blackmagicrecipe.presentation

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.blackmagicrecipe.databinding.ActivityMainBinding
import com.example.blackmagicrecipe.domain.entites.BrewingType
import com.example.blackmagicrecipe.domain.entites.CoffeeEvaluation
import com.example.blackmagicrecipe.domain.entites.CoffeeProduct
import com.example.blackmagicrecipe.domain.entites.Recipe
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()


        binding.saveBlackMagic.setOnClickListener {
            val brewingTypeString = binding.spinnerBrewingType.selectedItem.toString()
            val brewingType = when (brewingTypeString) {
                "V60" -> BrewingType.V60
                "Espresso Machine" -> BrewingType.EspressoMachine
                else -> null
            }
            val coffeeNameString = binding.spinnerCoffeeName.selectedItem.toString()
            val coffeeProduct = CoffeeProduct(coffeeNameString, "Kenya", "http")
            val time = 25
            val acidity = binding.sliderAcidity.value.toInt()
            val body = binding.sliderBody.value.toInt()
            val sweetness = binding.sliderSweetness.value.toInt()
            val rating = binding.sliderOverallRating.value.toInt()
            val evaluation = CoffeeEvaluation(acidity, body, sweetness, rating)

            val newRecipe = Recipe(
                brewingType,
                coffeeProduct,
                time,
                evaluation
            )
            lifecycleScope.launch {
                viewModel.saveRecipe(newRecipe)
            }

        }

    }

    private fun observeViewModel() {
        lifecycleScope.launch {


        }
    }
}