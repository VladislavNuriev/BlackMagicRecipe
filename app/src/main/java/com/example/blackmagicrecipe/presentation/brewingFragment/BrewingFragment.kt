package com.example.blackmagicrecipe.presentation.brewingFragment

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.blackmagicrecipe.R
import com.example.blackmagicrecipe.databinding.FragmentBrewingBinding
import com.example.blackmagicrecipe.di.ApplicationComponent
import com.example.blackmagicrecipe.presentation.BlackMagicRecipeApp
import com.example.blackmagicrecipe.presentation.ViewModelFactory
import com.example.blackmagicrecipe.presentation.recipesListFragment.RecipesListFragment
import javax.inject.Inject

class BrewingFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as BlackMagicRecipeApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: BrewingViewModel

    private var _binding: FragmentBrewingBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("binding (FragmentWelcomeBinding) is null")


    override fun onAttach(context: Context) {
        component.inject(this   )
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrewingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[BrewingViewModel::class.java]
        setOnClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setOnClickListeners() {
        binding.buttonSaveBlackMagic.setOnClickListener {
            val brewingType = binding.spinnerBrewingType.selectedItem.toString()
            val coffeeNameString = binding.spinnerCoffeeName.selectedItem.toString()
            val acidity = binding.sliderAcidity.value.toInt()
            val body = binding.sliderBody.value.toInt()
            val sweetness = binding.sliderSweetness.value.toInt()
            val rating = binding.sliderOverallRating.value.toInt()
            viewModel.saveRecipe(brewingType, coffeeNameString, acidity, body, sweetness, rating)
            launchRecipesListFragment()
        }
    }


    private fun launchRecipesListFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, RecipesListFragment.newInstance())
            .addToBackStack(RecipesListFragment.NAME)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = BrewingFragment()
    }
}