package com.example.blackmagicrecipe.presentation.recipesListFragment.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.blackmagicrecipe.domain.models.Recipe

class RecipeDiffCallBack: DiffUtil.ItemCallback<Recipe>() {
    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.recipeId == newItem.recipeId
    }
}