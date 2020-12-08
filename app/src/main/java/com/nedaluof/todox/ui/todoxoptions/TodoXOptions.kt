package com.nedaluof.todox.ui.todoxoptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nedaluof.todox.R
import com.nedaluof.todox.model.TodoX
import com.nedaluof.todox.ui.todoxoperations.TodoXOperations
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.design_options_bottom_sheet.*

/**
 * Created by nedaluof on 11/9/2020.
 */
@AndroidEntryPoint
class TodoXOptions : BottomSheetDialogFragment() {

    private val viewModel: TodoXOptionsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.design_options_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val comingTodoX =
            arguments?.getParcelable<TodoX>(TodoxOptions.TODOX_KEY_OPT)
        edit_btn.setOnClickListener {
            val operationBottomSheet: BottomSheetDialogFragment =
                TodoXOperations.NewInstance.newInstance(
                    TodoXOperations.NewInstance.OperationType.EDIT, comingTodoX
                )

            activity?.supportFragmentManager?.let { it1 ->
                operationBottomSheet.show(
                    it1,
                    TodoXOperations.NewInstance.TAG
                )
            }

            dismiss()
        }

        delete_btn.apply {
            elevation = 0f
            setOnClickListener {
                // TODO: 11/2020 confirm deletion
                viewModel.deleteTodoX(comingTodoX!!).observe(this@TodoXOptions) {
                    Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_LONG).show()
                    dismiss()
                }
            }
        }

    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    object TodoxOptions {
        fun newInstance(todoX: TodoX?): TodoXOptions =
            TodoXOptions().apply {
                if (todoX != null) {
                    arguments = Bundle().apply {
                        putParcelable(TODOX_KEY_OPT, todoX)
                    }
                }
            }

        const val TODOX_KEY_OPT = "TODOX_KEY_OPT"
        const val TAG = "TODOX_OPTIONS_BOTTOM"
    }

}