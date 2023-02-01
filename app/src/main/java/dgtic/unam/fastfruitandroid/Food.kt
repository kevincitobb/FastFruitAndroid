package dgtic.unam.fastfruitandroid
import android.util.Log

class Food {
    var foodImage: Int = 0
    var price: String = ""
    var restaurantName: String = ""
    var liked: Boolean = false
    var foodType: String = ""
    var quantity: Int = 0
    var total: Double = 0.0

    constructor(foodImage: Int, price: String, restaurantName: String, foodType: String) {
        this.foodImage = foodImage
        this.price = price
        this.restaurantName = restaurantName
        this.foodType = foodType
        try {
            val priceDouble = price.toDouble()
            this.total = priceDouble * quantity.toDouble()
        } catch (e: NumberFormatException) {
            this.total = 20000000.0
            Log.e("Food", "Error al convertir el precio a double: $price")
        }
    }

    fun setFoodQuantity(quantity: Int) {
        this.quantity = quantity
        this.total = this.price.toDouble() * quantity.toDouble()
    }
}
