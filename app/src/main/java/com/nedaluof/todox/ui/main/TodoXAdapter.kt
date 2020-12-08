package com.nedaluof.todox.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nedaluof.todox.R
import com.nedaluof.todox.model.TodoX
import kotlinx.android.synthetic.main.item_todox.view.*

/**
 * Created by nedaluof on 10/25/2020.
 */
class TodoXAdapter(private val todoXList: List<TodoX>) :
    RecyclerView.Adapter<TodoXAdapter.TodoXVH>() {

    var onClick: OnClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoXVH =
        TodoXVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_todox, parent, false).rootView
        )

    // TODO: 11/9/2020 send the item id only
    override fun onBindViewHolder(holder: TodoXVH, position: Int) {
        val item = todoXList[position]
        holder.itemView.run {
            title.text = item.title
            description.text = item.description
            created_at.text = item.createdAt.formatDate(1)
            item_todox.setOnClickListener { onClick?.onItemClick(item) }
            updated_at.apply {
                if (item.updatedAt != null) {
                    text = item.updatedAt!!.formatDate(2)
                } else {
                    visibility = View.GONE
                }
            }
        }
    }


    override fun getItemCount(): Int = todoXList.size

    fun getTodoXAt(position: Int): TodoX = todoXList[position]

    private fun String.formatDate(type: Int): String {
        val indexOfT = this.indexOf("T")
        val str1 = this.substring(0, indexOfT)
        val str2 = this.substring(indexOfT + 1, indexOfT + 6)
        return when (type) {
            1 -> "created in : $str1  At  $str2"
            else -> "updated in : $str1  At  $str2"
        }
    }

    class TodoXVH(view: View) : RecyclerView.ViewHolder(view)
    fun interface OnClick {
        fun onItemClick(todoX: TodoX)
    }
}
