package mx.com.maiktmp.habitsreminder.modules.categories.view.adapter

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import mx.com.maiktmp.database.entities.CategoryDB
import mx.com.maiktmp.habitsreminder.R
import mx.com.maiktmp.habitsreminder.databinding.ItemCategoryBinding
import mx.com.maiktmp.habitsreminder.models.Category
import mx.com.maiktmp.habitsreminder.modules.categories.view.CategoriesFragment
import mx.com.maiktmp.habitsreminder.utils.Extensions.hideView
import mx.com.maiktmp.habitsreminder.utils.Extensions.showView

class CategoryAdapter(
    var categories: ArrayList<CategoryDB>,
    var categoryListener: CategoriesFragment.CategoryListener? = null
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    var originalData = ArrayList<CategoryDB>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.vBind.tvQuantity.text = "0"
        holder.vBind.tvCategoryName.text = category.name
        holder.vBind.cvRoot.backgroundTintList =
            ContextCompat.getColorStateList(holder.itemView.context, category.color!!)
        setupLongClickListener(holder.vBind, category, position)
    }

    override fun getItemCount(): Int = categories.size

    private fun setupLongClickListener(
        vBind: ItemCategoryBinding,
        category: CategoryDB,
        index: Int
    ) {
        vBind.cvRoot.setOnLongClickListener {
            categoryListener?.onLongClickCategory(category, index)
            true
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vBind: ItemCategoryBinding = ItemCategoryBinding.bind(itemView)
    }
}