package dgtic.unam.fastfruitandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import dgtic.unam.fastfruitandroid.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityAccountBinding
    private var direcList = ArrayList<Direc>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.menu.findItem(R.id.user_item).isChecked = this is AccountActivity
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        val email = intent.getStringExtra("email")


        // Mostrar correo guardado en la variable email
        binding.tvUserEmail.text = email


        binding.AddDirecButton.setOnClickListener {
            val direcText = binding.etDiecNueva.text.toString()
            if (direcText.isNotEmpty()) {
                addDirec(Direc(direcText))
                binding.etDiecNueva.text.clear()
            }
        }
    }


    private fun initRecyclerView() {
        val adapter = DirecAdapter(direcList)
        binding.recycledirec.adapter = adapter
        binding.recycledirec.layoutManager = LinearLayoutManager(this)
    }

    // Add Direc instances to direcList and notify adapter of change
    private fun addDirec(direc: Direc) {
        direcList.add(direc)
        binding.recycledirec.adapter?.notifyDataSetChanged()
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
                if (javaClass != CartActivity::class.java) {
                    val intent = Intent(this, CartActivity::class.java)
                    intent.putExtra("email", email)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
                true
            }
            R.id.user_item -> {
                if (this !is AccountActivity) {
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