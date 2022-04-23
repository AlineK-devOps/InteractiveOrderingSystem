package ru.nstu.ordsys.operation.ui

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import ru.nstu.ordsys.component.ui.dialog.BaseCustomDialog
import ru.nstu.ordsys.operation.R
import ru.nstu.ordsys.operation.databinding.OperationResultDialogFragmentBinding

private const val OPERATION_TYPE_PROPERTY = "OPERATION_TYPE_PROPERTY"
private const val MESSAGE_ID_PROPERTY = "MESSAGE_ID_PROPERTY"
private const val MESSAGE_TEXT_PROPERTY = "MESSAGE_TEXT_PROPERTY"
private const val ACTION_TEXT_ID = "ACTION_TEXT_ID"
private const val CLOSE_TEXT_ID = "CLOSE_TEXT_ID"
private const val ACTION_CODE = "ACTION_CODE"
private const val CLOSE_CODE = "CLOSE_CODE"

var OperationResultDialogFragment.OperationType
    get() = arguments?.getSerializable(OPERATION_TYPE_PROPERTY)
        ?: throw IllegalArgumentException("Arguments can't be null")
    set(value) {
        arguments =
            arguments?.also { it.putSerializable(OPERATION_TYPE_PROPERTY, value) } ?: bundleOf(
                OPERATION_TYPE_PROPERTY to value
            )
    }

var OperationResultDialogFragment.messageId
    get() = arguments?.getInt(MESSAGE_ID_PROPERTY)
        ?: throw IllegalArgumentException("Arguments can't be null")
    set(value) {
        arguments = arguments?.also { it.putInt(MESSAGE_ID_PROPERTY, value) } ?: bundleOf(
            MESSAGE_ID_PROPERTY to value
        )
    }

var OperationResultDialogFragment.messageText
    get() = arguments?.getString(MESSAGE_TEXT_PROPERTY)
        ?: throw IllegalArgumentException("Arguments can't be null")
    set(value) {
        arguments = arguments?.also { it.putString(MESSAGE_TEXT_PROPERTY, value) } ?: bundleOf(
            MESSAGE_TEXT_PROPERTY to value
        )
    }

var OperationResultDialogFragment.actionButtonTextId
    get() = arguments?.getInt(ACTION_TEXT_ID)
        ?: throw IllegalArgumentException("Arguments can't be null")
    set(value) {
        arguments = arguments?.also { it.putInt(ACTION_TEXT_ID, value) }
            ?: bundleOf(ACTION_TEXT_ID to value)
    }

var OperationResultDialogFragment.closeButtonTextId
    get() = arguments?.getInt(CLOSE_TEXT_ID)
        ?: throw IllegalArgumentException("Arguments can't be null")
    set(value) {
        arguments =
            arguments?.also { it.putInt(CLOSE_TEXT_ID, value) } ?: bundleOf(CLOSE_TEXT_ID to value)
    }

var OperationResultDialogFragment.actionButtonRequestCode
    get() = arguments?.getInt(ACTION_CODE)
        ?: throw IllegalArgumentException("Arguments can't be null")
    set(value) {
        arguments =
            arguments?.also { it.putInt(ACTION_CODE, value) } ?: bundleOf(ACTION_CODE to value)
    }

var OperationResultDialogFragment.closeButtonRequestCode
    get() = arguments?.getInt(CLOSE_CODE)
        ?: throw IllegalArgumentException("Arguments can't be null")
    set(value) {
        arguments =
            arguments?.also { it.putInt(CLOSE_CODE, value) } ?: bundleOf(CLOSE_CODE to value)
    }

class OperationResultDialogFragment :
    BaseCustomDialog<OperationResultDialogFragmentBinding, OperationResultDialogFragment.OperationResultDialogFragmentListener>() {

    interface OperationResultDialogFragmentListener : BaseDialogListener {

        fun onDialogCloseResult(requestCode: Int)
    }

    companion object {

        fun newInstance(
            operationType: DialogOperationType,
            messageId: Int = R.string.empty_text,
            messageText: String = String(),
            actionButtonTextId: Int = R.string.empty_text,
            closeButtonTextId: Int = R.string.empty_text,
            actionRequestCode: Int = DialogCloseResults.ACTION.code,
            closeRequestCode: Int = DialogCloseResults.CLOSE.code
        ) = OperationResultDialogFragment().apply {
            this.OperationType = operationType
            this.messageId = messageId
            this.messageText = messageText
            this.actionButtonTextId = actionButtonTextId
            this.closeButtonTextId = closeButtonTextId
            this.actionButtonRequestCode = actionRequestCode
            this.closeButtonRequestCode = closeRequestCode
        }
    }

    enum class DialogCloseResults(val code: Int) {
        ACTION(0),
        CLOSE(1)
    }

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): OperationResultDialogFragmentBinding =
        OperationResultDialogFragmentBinding.inflate(inflater, container, false)

    override fun otherSetups() {
        isCancelable = false
        generalSettings()

        when (OperationType) {
            DialogOperationType.OPERATION_SUCCESS,
            DialogOperationType.OPERATION_FAIL     -> createSuccessDialog()

            DialogOperationType.OPERATION_QUESTION -> createQuestionDialog()
        }
    }

    private fun generalSettings() {
        with(binding) {
            val actionText = resources.getText(actionButtonTextId)
            val closeText = resources.getString(closeButtonTextId)
            stateMessage.text = when {
                messageId == R.string.empty_text && messageText.isNotEmpty() -> messageText
                messageId != R.string.empty_text && messageText.isEmpty() -> resources.getText(
                    messageId
                )
                else -> resources.getText(R.string.dialog_empty_warning_text)
            }
            actionButton.apply {
                if (actionText.isNotEmpty()) {
                    isGone = false
                    text = resources.getText(actionButtonTextId)
                }
                setOnClickListener {
                    dismiss()
                    listener?.onDialogCloseResult(actionButtonRequestCode)
                }
            }
            closeButton.apply {
                if (closeText.isNotEmpty()) {
                    isGone = false
                    text = resources.getText(closeButtonTextId)
                }
                setOnClickListener {
                    dismiss()
                    listener?.onDialogCloseResult(closeButtonRequestCode)
                }
            }
        }
    }

    private fun createSuccessDialog(){
        binding.closeButton.setBackgroundColor(ContextCompat.getColor(requireContext(), ru.nstu.ordsys.component.resources.R.color.orange))
    }

    private fun createQuestionDialog(){
        with (binding){
            actionButton.setBackgroundColor(ContextCompat.getColor(requireContext(), ru.nstu.ordsys.component.resources.R.color.orange))
            closeButton.setBackgroundColor(ContextCompat.getColor(requireContext(), ru.nstu.ordsys.component.resources.R.color.light_gray))
        }
    }
}