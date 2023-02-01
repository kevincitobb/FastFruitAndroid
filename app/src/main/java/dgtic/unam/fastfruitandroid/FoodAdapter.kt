package dgtic.unam.fastfruitandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private val FoodList: List<Food>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_row_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = FoodList[position]


        holder.foodImage.setImageResource(food.foodImage)
        holder.price.text = food.price
        holder.restaurantName.text = food.restaurantName
        holder.likeImage.setImageResource(if (food.liked) R.drawable.ic_like_filled else R.drawable.ic_like_empty)

        holder.likeImage.setOnClickListener {
            food.liked = !food.liked
            holder.likeImage.setImageResource(if (food.liked) R.drawable.ic_like_filled else R.drawable.ic_like_empty)
            // Actualizar en la base de datos o en la lista
        }

        holder.addButton.setOnClickListener {
            food.quantity++
            food.setFoodQuantity(food.quantity)
            notifyDataSetChanged()
        }
        holder.removeButton.setOnClickListener {
            if (food.quantity > 0) {
                food.quantity--
                food.setFoodQuantity(food.quantity)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return FoodList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.food_image)
        val price: TextView = itemView.findViewById(R.id.price)
        val restaurantName: TextView = itemView.findViewById(R.id.restaurant_name)
        val likeImage: ImageView = itemView.findViewById(R.id.imageView4)
        val addButton: AppCompatImageButton = itemView.findViewById(R.id.add_button)
        val removeButton: AppCompatImageButton = itemView.findViewById(R.id.add_button2)
    }
}