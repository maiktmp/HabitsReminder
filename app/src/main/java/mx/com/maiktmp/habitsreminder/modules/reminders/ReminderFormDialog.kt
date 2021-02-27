package mx.com.maiktmp.habitsreminder.modules.reminders


import android.R
import android.app.Dialog
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import mx.com.maiktmp.habitsreminder.databinding.DialogReminderFormBinding


class ReminderFormDialog : BottomSheetDialogFragment() {

    lateinit var vBind: DialogReminderFormBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
//        val bottomSheet = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
//        val view: View = View.inflate(context, R.layout.layout_bottom_sheet, null)

    }
}