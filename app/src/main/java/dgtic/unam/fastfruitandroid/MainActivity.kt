package dgtic.unam.fastfruitandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dgtic.unam.fastfruitandroid.databinding.ActivityMainBinding


enum class ProviderType {
    BASIC
}
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private var foodTypes = mutableListOf("Todos","Likes", "Verduras", "Frutas", "Otros")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val foodList = FoodListSingleton.getInstance().foodList
        setRecycler(foodList)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.menu.findItem(R.id.home_item).isChecked = this is MainActivity
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        // Inicializa el adapter del spinner
        val spinnerAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, foodTypes)
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        binding.spCat.adapter = spinnerAdapter

        binding.spCat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Recoge la selección del usuario en el spinner
                val selectedItem = binding.spCat.selectedItem.toString()
                // Filtra la lista de alimentos en base a la selección
                binding.tvCat.text = selectedItem
                val filteredFoodList = when {
                    selectedItem == "Todos" -> foodList
                    selectedItem == "Likes" -> foodList.filter { it.liked }
                    else -> foodList.filter { it.foodType == selectedItem }
                }
                // Muestra la lista filtrada en el recyclerview
                setRecycler(filteredFoodList)
            }
        }
    }

    private fun setRecycler(FoodList: List<Food>) {
        binding.foodRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val FoodAdapter =FoodAdapter(FoodList)
        binding.foodRecycler.adapter = FoodAdapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val email = intent.getStringExtra("email")

        return when (item.itemId) {
            R.id.home_item -> {
                if (this !is MainActivity) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("email", email)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
                true
            }
            R.id.cart_item -> {
                if (javaClass != CartActivity::class.java) {

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