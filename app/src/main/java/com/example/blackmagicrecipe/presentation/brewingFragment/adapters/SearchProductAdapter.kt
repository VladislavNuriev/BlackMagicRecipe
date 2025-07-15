package com.example.blackmagicrecipe.presentation.brewingFragment.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.example.blackmagicrecipe.domain.models.CoffeeProduct
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SearchProductAdapter @Inject constructor(
    @ApplicationContext context: Context,
) : ArrayAdapter<CoffeeProduct>(context, android.R.layout.simple_dropdown_item_1line) {

    var productList: List<CoffeeProduct> = mutableListOf()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent) as TextView
        view.text = getItem(position)?.name
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                val filteredList = if (charSearch.isEmpty()) {
                    productList
                } else {
                    productList.filter { item ->
                        item.name.lowercase().contains(charSearch.lowercase())
                    }
                }
                return FilterResults().apply {
                    values = filteredList
                    count = filteredList.size
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if ((results?.count ?: 0) > 0) {
                    clear()
                    addAll(results?.values as List<CoffeeProduct>)
                } else {
                    notifyDataSetInvalidated()
                }
            }
        }
    }
}