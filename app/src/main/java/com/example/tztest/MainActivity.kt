package com.example.tztest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val itemsList: RecyclerView = findViewById(R.id.itemsList)
        val items = ObjectStorage.getAllItems().reversed()

        val buttonAllView: Button = findViewById(R.id.all_view_button)
        val createNewItem: Button = findViewById(R.id.create_button)

        buttonAllView.setOnClickListener {
            val intent = Intent(this, ItemHistoryActivity::class.java )
            intent.putExtra("allItem", items.toTypedArray())
            this.startActivity(intent)
        }

        createNewItem.setOnClickListener {
            val intent = Intent(this, CreateItemActivity::class.java)
            this.startActivity(intent)
        }


        val lastThreeItems = if (items.size <= 3) {
            items
        } else {
            items.subList(0, 3)
        }

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(lastThreeItems, this)


    }
}