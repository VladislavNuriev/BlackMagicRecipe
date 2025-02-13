package com.example.blackmagicrecipe.presentation.recipesListFragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.blackmagicrecipe.R
import com.example.blackmagicrecipe.databinding.ItemRvRecipeBinding
import com.example.blackmagicrecipe.domain.entites.BrewingType
import com.example.blackmagicrecipe.domain.entites.Recipe

class RecipesAdapter() :
    ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(RecipeDiffCallBack()) {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class RecipeViewHolder(val binding: ItemRvRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecipeViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = ItemRvRecipeBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return RecipeViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(recipeViewHolder: RecipeViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val recipe = getItem(position)
        with(recipeViewHolder.binding) {
            textViewBrewingMethod.text = recipe.brewingType.toString()
            textViewCoffeeLabel.text = recipe.coffeeProduct?.name.toString()
            textViewOverallRating.text = recipe.evaluation.overallRating.toString()
            imageViewBrewingType.setImageResource(recipe.brewingType.iconResourceId)
        }
    }
}