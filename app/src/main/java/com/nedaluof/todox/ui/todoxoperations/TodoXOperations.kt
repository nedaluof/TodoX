package com.nedaluof.todox.ui.todoxoperations

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nedaluof.todox.R
import com.nedaluof.todox.model.TodoX
import com.nedaluof.todox.ui.todoxoperations.TodoXOperations.NewInstance.OperationType
import com.nedaluof.todox.ui.todoxoperations.TodoXOperations.NewInstance.TODOX_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.design_operation_bottom_sheet.*
import java.time.LocalDateTime

/**
 * Created by nedaluOf on 10/25/2020.
 */
/**
 * [TodoXOperations] class handle creation of [BottomSheetDialogFragment]
 * and deal with ADD and EDIT Functionality
 * */

@AndroidEntryPoint
class TodoXOperations(private val operationType: OperationType) : BottomSheetDialogFragment() {

    @VisibleForTesting
    val viewModel: TodoXOperationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.design_operation_bottom_sheet, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (operationType) {
            OperationType.ADD -> {
                //All views have default values
                doOperation(OperationType.ADD, null)
            }
            OperationType.EDIT -> {
                operationEditTodoX()
            }
        }
    }

    private fun operationEditTodoX() {
        //prepare all views for  edit operation
        val comingTodoX = arguments?.getParcelable<TodoX>(TODOX_KEY)
        bottom_sheet_title.text = getString(R.string.bottom_sheet_title_label_op2)
        edt_title.setText(comingTodoX!!.title)
        edt_description.setText(comingTodoX.description)
        operation_btn.text = getString(R.string.operation_bt_label_op2)
        doOperation(OperationType.EDIT, comingTodoX)
    }


    private fun doOperation(type: OperationType, todoX: TodoX?) {
        operation_btn.setOnClickListener {
            val _title = edt_title.text.toString()
            val _description = edt_description.text.toString()
            if (_title.isNotEmpty()) {
                if (_description.isNotEmpty()) {
                    when (type) {
                        OperationType.ADD -> {
                            val newTodoX = TodoX(
                                title = _title,
                                description = _description,
                            )
                            //todo show loading indicator
                            viewModel.insertTodoX(newTodoX).observe(this) {
                                //todo hide loading indicator
                                dismiss()
                            }
                        }
                        OperationType.EDIT -> {
                            //todo show loading indicator
                            todoX!!.apply {
                                title = _title
                                description = _description
                                updatedAt = LocalDateTime.now().toString()
                            }
                            viewModel.updateTodoX(todoX).observe(this) {
                                //todo hide loading indicator
                                dismiss()
                            }
                        }
                    }
                } else {
                    edt_description.error = "missing"
                    edt_description.requestFocus()
                }
            } else {
                edt_title.error = "missing"
                edt_title.requestFocus()
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        //TODO("ask to discard fields")
    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    object NewInstance {
        fun newInstance(operationType: OperationType, todoX: TodoX?): TodoXOperations =
            TodoXOperations(operationType).apply {
                if (todoX != null) {
                    arguments = Bundle().apply {
                        putParcelable(TODOX_KEY, todoX)
                    }
                }
            }

        const val TODOX_KEY = "TODOX_KEY"
        const val TAG = "TODOX_OPERATIONS_BOTTOM"

        enum class OperationType { ADD, EDIT; }
    }
}