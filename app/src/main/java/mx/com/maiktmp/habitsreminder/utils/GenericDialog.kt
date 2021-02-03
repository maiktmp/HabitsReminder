package mx.com.maiktmp.habitsreminder.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import mx.com.maiktmp.habitsreminder.base.ui.CustomDialogFragment
import mx.com.maiktmp.habitsreminder.databinding.DialogGenericBinding
import mx.com.maiktmp.habitsreminder.utils.Extensions.showView

class GenericDialog : CustomDialogFragment() {

    companion object {
        const val TAG = "DIALOG_GENERIC"
        const val KEY_TITLE = "KEY_TITLE"
        const val KEY_BODY = "KEY_BODY"

        fun newInstance(title: String, body: String): GenericDialog {
            val args = Bundle().apply {
                putString(KEY_TITLE, title)
                putString(KEY_BODY, body)
            }
            return GenericDialog().apply {
                arguments = args
            }
        }
    }


    lateinit var vBind: DialogGenericBinding
    private var title: String? = null
    private var body: String? = null


    private var okText = "Ok"
    private var cancelText = "Cancelar"

    private var onOkClickListener: View.OnClickListener? = null
    private var onCancelListener: View.OnClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBind = DialogGenericBinding.inflate(inflater, container)
        retrieveExtras()
        setupUi()
        return vBind.root
    }


    fun setupOkButton(e: View.OnClickListener, text: String = "Ok") {
        this.okText = text
        this.onOkClickListener = e
    }

    fun setupCancelButton(e: View.OnClickListener, text: String = "Cancelar") {
        this.cancelText = text
        this.onCancelListener = e
    }


    private fun setupUi() {
        vBind.tvTitle.text = title
        vBind.tvBody.text = body

        if (onOkClickListener != null) {
            vBind.btnDone.showView()
            vBind.btnDone.text = okText
            vBind.btnDone.setOnClickListener(onOkClickListener)
        }

        if (onCancelListener != null) {
            vBind.btnCancel.showView()
            vBind.btnCancel.text = cancelText
            vBind.btnCancel.setOnClickListener(onCancelListener)
        }
    }


    private fun retrieveExtras() {
        title = arguments?.getString(KEY_TITLE)
        body = arguments?.getString(KEY_BODY)
    }


}