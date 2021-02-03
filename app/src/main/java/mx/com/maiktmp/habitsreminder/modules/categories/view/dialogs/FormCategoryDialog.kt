package mx.com.maiktmp.habitsreminder.modules.categories.view.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.gson.Gson
import mx.com.maiktmp.database.entities.CategoryDB
import mx.com.maiktmp.habitsreminder.R
import mx.com.maiktmp.habitsreminder.base.ui.CustomDialogFragment
import mx.com.maiktmp.habitsreminder.base.ui.StackColors
import mx.com.maiktmp.habitsreminder.databinding.DialogFormCategoryBinding
import mx.com.maiktmp.habitsreminder.utils.Extensions.required
import mx.com.maiktmp.habitsreminder.utils.Extensions.showView
import mx.com.maiktmp.habitsreminder.utils.Extensions.validateMail

class FormCategoryDialog : CustomDialogFragment() {

    companion object {
        const val TAG = "FORM_CATEGORY_DIALOG"
        const val CATEGORY = "CATEGORY"

        fun newInstance(category: CategoryDB? = null): FormCategoryDialog {
            val args = Bundle()
            category?.let {
                args.putString(CATEGORY, Gson().toJson(it))
            }
            return FormCategoryDialog().apply {
                arguments = args
            }
        }
    }

    private lateinit var vBind: DialogFormCategoryBinding
    private var indexColorSelected = 0
    private var colorDots = ArrayList<ImageView>()
    private var category: CategoryDB? = null

    var onClickDoneButton: (id: Long?, name: String, color: Int) -> Unit = { _, _, _ -> }
    var onClickDeleteButton: (id: Long?) -> Unit = { _ -> }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBind = DialogFormCategoryBinding.inflate(inflater, container, false)
        retrieveArguments()
        setupButtons()
        setupColors()
        setupView()
        return vBind.root
    }

    private fun retrieveArguments() {
        arguments?.get(CATEGORY)?.let {
            category = Gson().fromJson(it as String, CategoryDB::class.java)
        }
    }

    private fun setupView() {
        vBind.tvTitle.text = if (category == null) "Nueva categoría" else "Modificar categoría"
        category?.let {
            vBind.tilName.editText?.setText(it.name)
            for ((index, image) in colorDots.withIndex()) {
                if (index == indexColorSelected) {
                    image.alpha = 1F
                }
            }

            vBind.btnDelete.showView()
            vBind.btnDelete.setOnClickListener { _ -> onClickDeleteButton(it.id) }
        }
    }

    private fun setupColors() {
        for ((index, color) in StackColors.categoryColors.withIndex()) {
            val image = ImageView(context).apply {
                setImageResource(R.drawable.shape_circle)
                imageTintList = context.getColorStateList(color)
                layoutParams = LinearLayout.LayoutParams(0, WRAP_CONTENT, 1F)
                alpha = if (category == null) 1F else 0.2F
            }
            if (category != null && category!!.color == color) {
                indexColorSelected = index
            }
            colorDots.add(image)
            image.setOnClickListener {
                indexColorSelected = index
                colorDots.forEach { _image -> _image.alpha = 0.2F }
                it.alpha = 1F
            }
            vBind.llColors.addView(image)
        }
    }

    private fun setupButtons() {

        vBind.btnCancel.setOnClickListener {
            dismiss()
        }

        vBind.btnDone.setOnClickListener {
            if (!vBind.tilName.required()) return@setOnClickListener
            onClickDoneButton(
                category?.id,
                vBind.tilName.editText?.text.toString(),
                StackColors.categoryColors[indexColorSelected]
            )
            dismiss()
        }
    }

}