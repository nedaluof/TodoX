package com.nedaluof.todox.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nedaluof.todox.R
import com.nedaluof.todox.ui.todoxoperations.TodoXOperations.NewInstance
import com.nedaluof.todox.ui.todoxoperations.TodoXOperations.NewInstance.OperationType.ADD
import com.nedaluof.todox.ui.todoxoptions.TodoXOptions.TodoxOptions.TAG
import com.nedaluof.todox.ui.todoxoptions.TodoXOptions.TodoxOptions.newInstance
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @VisibleForTesting
    val viewModel: MainViewModel by viewModels()
    lateinit var adapter: TodoXAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getAll().observe(this) { it ->
            adapter = TodoXAdapter(it)
            adapter.onClick = TodoXAdapter.OnClick {
                val optionsBottomSheet: BottomSheetDialogFragment = newInstance(it)
                optionsBottomSheet.show(supportFragmentManager, TAG)
            }
            recycler_view.adapter = adapter
        }

        recycler_view.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
        }

        add_btn.setOnClickListener {
            val operationBottomSheet: BottomSheetDialogFragment = NewInstance.newInstance(ADD, null)
            operationBottomSheet.show(supportFragmentManager, NewInstance.TAG)
        }

        /*val swipeCallback = object : RecyclerViewSwipe(context = this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //temporally save TodoX obj if the user need to undo deletion
                val currentTodoXPosition = viewHolder.adapterPosition
                val currentTodoX: TodoX = adapter.getTodoXAt(currentTodoXPosition)

                viewModel.deleteTodoX(adapter.getTodoXAt(viewHolder.adapterPosition))

                Snackbar
                    .make(main_layout, "Deleted Successfully.", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
                        viewModel.insert(currentTodoX)
                        recycler_view.scrollToPosition(currentTodoXPosition)
                    }
                    .setActionTextColor(Color.WHITE)
                    .show()
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(recycler_view)*/
    }
}