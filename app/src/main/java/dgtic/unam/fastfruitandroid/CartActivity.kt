package dgtic.unam.fastfruitandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import dgtic.unam.fastfruitandroid.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var adapter: CartFoodAdapter
    private lateinit var binding: ActivityCartBinding
    private var cartFoodList = mutableListOf<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        bottomNavigationView.menu.findItem(R.id.cart_item).isChecked = true

        cartFoodList = FoodListSingleton.getInstance().foodList.filter { it.quantity > 0 }.toMutableList()

        binding.recyclerviewcart.layoutManager = LinearLayoutManager(this)
        adapter = CartFoodAdapter(this, cartFoodList)
        binding.recyclerviewcart.adapter = adapter

        var total = 0.0
        for (food in cartFoodList) {
            total += food.quantity * food.price.toDouble()
        }

        binding.tvTotal.text = "$ ${total.toString()}"
        binding.btnBuy.setOnClickListener { handleBuyClick() }
    }

    private fun handleBuyClick() {
        val total = binding.tvTotal.text.toString()
        Toast.makeText(this, "Comprando por: $total", Toast.LENGTH_SHORT).show()

        for (food in cartFoodList) {
            food.quantity = 0
        }

        adapter.notifyDataSetChanged()

        binding.tvTotal.text = "$ 0.0"
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val email = intent.getStringExtra("email")
        return when (item.itemId) {
            R.id.home_item -> {
                if (javaClass != MainActivity::class.java) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("email", email)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)

                }
                true
            }
            R.id.cart_item -> {
                if (this !is CartActivity) {
                    val intent = Intent(this, CartActivity::class.java)
                    intent.putExtra("email", email)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
                true
            }
            R.id.user_item -> {
                if (javaClass != AccountActivity::class.java) {
                    val intent = Intent(this, AccountActivity::class.java)
                    intent.putExtra("email", email)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
                true
            }
            else -> false
        }
    }

}