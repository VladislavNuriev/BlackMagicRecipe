package com.example.blackmagicrecipe.presentation.brewingFragment


import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.example.blackmagicrecipe.R
import com.example.blackmagicrecipe.databinding.FragmentBrewingBinding
import com.example.blackmagicrecipe.presentation.brewingFragment.adapters.SearchProductAdapter1
import com.example.blackmagicrecipe.presentation.recipesListFragment.RecipesListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BrewingFragment : Fragment() {

    private val viewModel by viewModels<BrewingViewModel>()

    private var _binding: FragmentBrewingBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("binding (FragmentWelcomeBinding) is null")

    private lateinit var adapter: SearchProductAdapter1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrewingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        observeTimerState()

        // Observe database changes
        viewModel.coffeeProductList.observe(viewLifecycleOwner) { items ->
            adapter = SearchProductAdapter1(requireContext(), items)
            // Setup AutoCompleteTextView
            binding.textViewCoffeeName.setAdapter(adapter)
            binding.textViewCoffeeName.threshold = 2
        }

        // Text changes listener
        binding.textViewCoffeeName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                adapter.filter.filter(s)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Item selection
//        adapter.onProductClickListener = object : SearchProductAdapter.OnProductClickListener {
//            override fun onProductClick(product: CoffeeProduct) {
//                val productName = product.name
//                binding.textViewCoffeeName.setText(productName)
//            }
//        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.chronometer.stop()
        _binding = null
    }


    private fun setOnClickListeners() {
        binding.buttonSaveBlackMagic.setOnClickListener {
            fetchValuesFromViews()
            launchRecipesListFragment()
        }

        binding.buttonTimer.setOnClickListener {
            viewModel.toggleTimer()
        }
    }


    private fun fetchValuesFromViews() {
        val brewingType = binding.spinnerBrewingType.selectedItem.toString()
        val coffeeNameString = binding.textViewCoffeeName.text.toString()
        val timerString = binding.chronometer.text.toString()
        val acidity = binding.sliderAcidity.value.toInt()
        val body = binding.sliderBody.value.toInt()
        val sweetness = binding.sliderSweetness.value.toInt()
        val rating = binding.sliderOverallRating.value.toInt()
        viewModel.saveRecipe(brewingType, coffeeNameString, timerString, acidity, body, sweetness, rating)
    }

    private fun observeTimerState() {
        viewModel.isTimerActive.observe(viewLifecycleOwner) {
            if (it) {
                startTimer()
            } else {
                stopTimer()
            }
            setupBrewingGif(it)
        }
    }

    private fun startTimer() {
        binding.chronometer.apply {
            base = SystemClock.elapsedRealtime()
            start()
        }
        binding.buttonTimer.text = getString(R.string.stop_timer)
    }


    private fun stopTimer() {
        binding.chronometer.stop()
        binding.buttonTimer.text = getString(R.string.reset_and_start)
    }


    private fun setupBrewingGif(isTimerActive: Boolean) {
        if (isTimerActive) {
            binding.imageViewCoffeeBrewing.visibility = View.VISIBLE
            val imageViewTarget = DrawableImageViewTarget(binding.imageViewCoffeeBrewing)
            Glide.with(this)
                .load(R.drawable.gif_icon_coffee_machine)
                .into(imageViewTarget)
        } else {
            binding.imageViewCoffeeBrewing.visibility = View.GONE
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
        private const val TAG = "BrewingFragment"
    }
}