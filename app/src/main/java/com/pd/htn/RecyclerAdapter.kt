package com.pd.htn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pd.htn.models.Transaction
import kotlinx.android.synthetic.main.history_cell.view.*

class RecyclerAdapter(private val transactions: Collection<Transaction>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cell = LayoutInflater.from(parent.context).inflate(R.layout.history_cell, parent, false)

        return ViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.date.text = transactions.elementAt(position).originationDateTime.toString()
        holder.view.rewardValue.text = transactions.elementAt(position).currencyAmount.toString()
    }
}