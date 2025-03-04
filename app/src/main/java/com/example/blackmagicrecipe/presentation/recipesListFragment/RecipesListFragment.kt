package com.example.blackmagicrecipe.presentation.recipesListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.blackmagicrecipe.databinding.FragmentRecipesListBinding
import com.example.blackmagicrecipe.presentation.recipesListFragment.adapters.RecipesAdapter

class RecipesListFragment : Fragment() {

    private val viewModel: RecipesListViewModel by viewModels()

    private var _binding: FragmentRecipesListBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("binding (FragmentWelcomeBinding) is null")

    private lateinit var recipeListAdapter: RecipesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.recipesList.observe(viewLifecycleOwner) {
            recipeListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        recipeListAdapter = RecipesAdapter()
        val recyclerView: RecyclerView = binding.rvRecipeList
        recyclerView.adapter = recipeListAdapter
    }

    companion object {
        fun newInstance() = RecipesListFragment()
        const val NAME = "RecipesListFragment"
        const val TAG = "RecipesListFragment"
    }
}