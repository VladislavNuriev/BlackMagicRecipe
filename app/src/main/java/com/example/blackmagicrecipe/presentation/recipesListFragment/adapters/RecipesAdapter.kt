package com.example.blackmagicrecipe.presentation.recipesListFragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.blackmagicrecipe.databinding.ItemRvRecipeBinding
import com.example.blackmagicrecipe.domain.models.Recipe
import com.example.blackmagicrecipe.presentation.converters.convertBrewingTypeToDrawableId

class RecipesAdapter() :
    ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(RecipeDiffCallBack()) {

    class RecipeViewHolder(val binding: ItemRvRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRvRecipeBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(recipeViewHolder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        with(recipeViewHolder.binding) {
            textViewBrewingMethod.text = recipe.brewingType.stringName
            textViewCoffeeLabel.text = recipe.coffeeProduct.name
            textViewOverallRating.text = recipe.evaluation.overallRating.toString()
            imageViewBrewingType.setImageResource(convertBrewingTypeToDrawableId(recipe.brewingType))
        }
    }
}