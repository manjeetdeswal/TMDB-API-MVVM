package com.thenotesgiver.tmdbapimvvm.data.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import com.thenotesgiver.tmdbapimvvm.R
import com.thenotesgiver.tmdbapimvvm.databinding.ItemSpinnerBinding

class SpinnerAdapter(
    context: Context,
    private val items: Array<String>
) : BaseAdapter() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
       return items.size
    }

    override fun getItem(p0: Int): Any {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View {
        return if (convertView != null) {
            convertView
        } else {
            val view = layoutInflater.inflate(R.layout.item_spinner, null)
            val binding = ItemSpinnerBinding.bind(view)
            val item = items[i]
            binding.spinnerText.text = item

            view
        }
    }

//more overridde methods
}