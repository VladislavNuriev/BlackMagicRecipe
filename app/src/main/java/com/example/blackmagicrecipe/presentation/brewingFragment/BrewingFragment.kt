package com.example.blackmagicrecipe.presentation.brewingFragment


import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.example.blackmagicrecipe.R
import com.example.blackmagicrecipe.databinding.FragmentBrewingBinding
import com.example.blackmagicrecipe.presentation.brewingFragment.BrewingViewModel.Companion.FAILURE
import com.example.blackmagicrecipe.presentation.brewingFragment.BrewingViewModel.Companion.SUCCESS
import com.example.blackmagicrecipe.presentation.brewingFragment.adapters.SearchProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class BrewingFragment : Fragment() {

    private val viewModel by viewModels<BrewingViewModel>()

    private var _binding: FragmentBrewingBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("binding (FragmentBrewingBinding) is null")

    @Inject
    lateinit var searchAdapter: SearchProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrewingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextViewCoffeeName()
        setOnClickListeners()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.chronometer.stop()
        _binding = null
    }


    private fun setupTextViewCoffeeName() {
        binding.textViewCoffeeName.apply {
            setAdapter(searchAdapter)
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    viewModel.onSearchQueryChanged(s.toString())
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
            setOnItemClickListener { _, _, position, _ ->
                val selectedItem = searchAdapter.getItem(position)
                setText(selectedItem?.name ?: context.getString(R.string.non_selected))
            }
        }
    }

    private fun setOnClickListeners() {
        binding.buttonSaveBlackMagic.setOnClickListener {
            fetchValuesFromViews()
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
        viewModel.saveRecipe(
            brewingType,
            coffeeNameString,
            timerString,
            acidity,
            body,
            sweetness,
            rating
        )
    }

    private fun observeViewModel() {
        observeCoffeeProductList()
        observeFailures()
        observeTimerState()
    }

    private fun observeCoffeeProductList() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.coffeeProductList.collect { items ->
                    searchAdapter.clear()
                    searchAdapter.productList = items
                    searchAdapter.addAll(items)
                }
            }
        }
    }


    private fun observeFailures() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.loadingStatus.collect {
                    if (it == SUCCESS) {
                        Toast.makeText(
                            requireActivity(),
                            getString(R.string.products_updated), Toast.LENGTH_LONG
                        ).show()
                    }
                    if (it == FAILURE) {
                        Toast.makeText(
                            requireActivity(),
                            getString(R.string.products_updating_failed), Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun observeTimerState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.isTimerActive.collect {
                    if (it) {
                        startTimer()
                    } else {
                        stopTimer()
                    }
                    setupBrewingGif(it)
                }
            }
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
}