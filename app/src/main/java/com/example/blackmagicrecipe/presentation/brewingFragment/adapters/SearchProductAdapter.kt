package com.example.blackmagicrecipe.presentation.brewingFragment.adapters

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.example.blackmagicrecipe.domain.models.CoffeeProduct
import javax.inject.Inject

class SearchProductAdapter @Inject constructor(
    context: Context,
    var productList: List<CoffeeProduct> = mutableListOf()
) : ArrayAdapter<CoffeeProduct>(context, android.R.layout.simple_dropdown_item_1line, productList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent) as TextView
        view.text = productList[position].name
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                return FilterResults().apply {
                    results.values = productList
                    results.count = productList.size
                    Log.d("testing", "performFiltering: productList $productList ")
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if ((results?.count ?: 0) > 0) {
                    productList = results?.values as List<CoffeeProduct>
                } else {
                    notifyDataSetInvalidated()
                }
            }
        }
    }
}