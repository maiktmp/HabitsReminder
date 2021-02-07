package mx.com.maiktmp.habitsreminder.modules

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import mx.com.maiktmp.habitsreminder.databinding.ToolbarBinding

open class CustomActivity : AppCompatActivity() {

    lateinit var toolbarBinding: ToolbarBinding

    fun setupToolbar(title: String) {
        toolbarBinding.toolbar.title = title

        setSupportActionBar(toolbarBinding.toolbar)
    }
}