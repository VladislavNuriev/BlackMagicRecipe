package com.example.blackmagicrecipe.presentation.brewingFragment


import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.example.blackmagicrecipe.R
import com.example.blackmagicrecipe.databinding.FragmentBrewingBinding
import com.example.blackmagicrecipe.presentation.recipesListFragment.RecipesListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BrewingFragment : Fragment() {

    private val viewModel by viewModels<BrewingViewModel>()

    private var _binding: FragmentBrewingBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("binding (FragmentWelcomeBinding) is null")


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
        observeFailures()
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
        val coffeeNameString = binding.spinnerCoffeeName.selectedItem.toString()
        val timerString = binding.chronometer.text.toString()
        val acidity = binding.sliderAcidity.value.toInt()
        val body = binding.sliderBody.value.toInt()
        val sweetness = binding.sliderSweetness.value.toInt()
        val rating = binding.sliderOverallRating.value.toInt()
        viewModel.saveRecipe(brewingType, coffeeNameString, timerString, acidity, body, sweetness, rating)
    }

    private fun observeFailures() {
        viewModel.loadingStatus.observe(viewLifecycleOwner) {
            if ((it != LOADING) and (it != SUCCESS)) {
                Toast.makeText(requireActivity(), it.toString(), Toast.LENGTH_LONG).show()
            }
        }
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
        const val LOADING = "Loading"
        const val SUCCESS = "Success"
    }
}