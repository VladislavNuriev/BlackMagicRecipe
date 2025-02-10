package com.example.blackmagicrecipe.presentation.brewingFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.blackmagicrecipe.R
import com.example.blackmagicrecipe.databinding.FragmentBrewingBinding
import com.example.blackmagicrecipe.domain.entites.BrewingType
import com.example.blackmagicrecipe.domain.entites.CoffeeEvaluation
import com.example.blackmagicrecipe.domain.entites.CoffeeProduct
import com.example.blackmagicrecipe.domain.entites.Recipe
import com.example.blackmagicrecipe.presentation.recipesListFragment.RecipesListFragment
import kotlinx.coroutines.launch

class BrewingFragment : Fragment() {

    private val viewModel: BrewingViewModel by viewModels()

    private var _binding: FragmentBrewingBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("binding (FragmentWelcomeBinding) is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrewingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveRecipe()

    }

    companion object {
        @JvmStatic
        fun newInstance() = BrewingFragment()
    }

    private fun saveRecipe() {
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
            launchRecipesListFragment()
        }
    }


    private fun launchRecipesListFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, RecipesListFragment.newInstance())
            .addToBackStack(RecipesListFragment.NAME)
            .commit()
    }
}