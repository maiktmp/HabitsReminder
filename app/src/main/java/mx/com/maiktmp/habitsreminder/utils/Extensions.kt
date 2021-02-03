package mx.com.maiktmp.habitsreminder.utils

import android.content.Context
import android.opengl.Visibility
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import mx.com.maiktmp.habitsreminder.R
import mx.com.maiktmp.habitsreminder.utils.Extensions.required

object Extensions {


    //Toast
    fun Context.displayToast(message: String?, duration: Int) {
        Toast.makeText(this, message, duration).show()
    }


    //    Visibility
    fun View.showView() {
        this.visibility = View.VISIBLE
    }

    fun View.hideView() {
        this.visibility = View.GONE
    }


    //Validations
    fun TextInputLayout.required(): Boolean {
        this.error = null;
        val text = this.editText?.text.toString()
        if (text == "") {
            this.error = context.getString(R.string.validation_require)
            return false
        }
        return true
    }

    fun TextInputLayout.validateMail(): Boolean {
        this.error = null
        val text = this.editText?.text.toString()
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            this.error = context.getString(R.string.validation_email)
            return false
        }
        return true
    }

}