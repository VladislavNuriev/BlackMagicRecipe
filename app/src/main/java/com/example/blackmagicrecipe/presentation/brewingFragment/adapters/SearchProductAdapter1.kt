package com.example.blackmagicrecipe.presentation.brewingFragment.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.example.blackmagicrecipe.domain.models.CoffeeProduct

class SearchProductAdapter1(
    context: Context,
    private val allProducts: List<CoffeeProduct>
) : ArrayAdapter<CoffeeProduct>(context, android.R.layout.simple_dropdown_item_1line, allProducts) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent) as TextView
        view.text = allProducts[position].name
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = if (constraint.isNullOrEmpty()) {
                    allProducts
                } else {
                    allProducts.filter {
                        it.name.contains(constraint, ignoreCase = true)
                    }
                }
                return FilterResults().apply {
                    values = filteredList
                    count = filteredList.size
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if ((results?.count ?: 0) > 0) {
                    val filteredList = results?.values as MutableList<CoffeeProduct>
                    clear()
                    addAll(filteredList)
                    notifyDataSetChanged()
                } else {
                    notifyDataSetInvalidated()
                }
            }
        }
    }
}