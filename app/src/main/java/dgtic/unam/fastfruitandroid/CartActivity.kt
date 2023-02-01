package dgtic.unam.fastfruitandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import dgtic.unam.fastfruitandroid.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var adapter: CartFoodAdapter
    private var cartFoodList = mutableListOf<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        bottomNavigationView.menu.findItem(R.id.cart_item).isChecked = this is CartActivity

        cartFoodList = FoodListSingleton.getInstance().foodList.filter { it.quantity > 0 } as MutableList<Food>

        // RecyclerView setup
        binding.recyclerviewcart.layoutManager = LinearLayoutManager(this)
        adapter = CartFoodAdapter(this, cartFoodList)
        binding.recyclerviewcart.adapter = adapter

        // Calculate the total
        var total = 0.0
        for (food in cartFoodList) {
            food.setFoodQuantity(food.quantity)
            total += food.total

        }



        // Show the total
        binding.tvTotal.text = "$ ${total.toString()}"
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home_item -> {
                if (javaClass != MainActivity::class.java) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)

                }
                true
            }
            R.id.cart_item -> {
                if (this !is CartActivity) {
                    val intent = Intent(this, CartActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
                true
            }
            R.id.user_item -> {
                if (javaClass != AccountActivity::class.java) {
                    val intent = Intent(this, AccountActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
                true
            }
            else -> false
        }
    }

}