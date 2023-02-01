package dgtic.unam.fastfruitandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartFoodAdapter(val context: Context, private val foods: List<Food>) : RecyclerView.Adapter<CartFoodAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName: TextView = itemView.findViewById(R.id.restaurant_name2)
        val foodPrice: TextView = itemView.findViewById(R.id.price2)
        val foodCount: TextView = itemView.findViewById(R.id.tvCantidad)
        val addButton: ImageButton = itemView.findViewById(R.id.add_button)
        val removeButton: ImageButton = itemView.findViewById(R.id.add_button2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cart_row_item, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int = foods.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]
        holder.foodName.text = food.restaurantName
        holder.foodPrice.text = food.price
        holder.foodCount.text = food.quantity.toString()

        holder.addButton.setOnClickListener {
            food.quantity++
            holder.foodCount.text = food.quantity.toString()
            food.setFoodQuantity(food.quantity)
            notifyDataSetChanged()
        }
        holder.removeButton.setOnClickListener {
            if (food.quantity > 0) {
                food.quantity--
                holder.foodCount.text = food.quantity.toString()
                food.setFoodQuantity(food.quantity)
                notifyDataSetChanged()
            }
        }

    }
}
