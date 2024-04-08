package com.example.tztest

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar

class ItemHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.all_history)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val listItems: RecyclerView = findViewById(R.id.list_item_history)
        val items = intent.getParcelableArrayExtra("allItem")?.map { it as Item } ?: emptyList()

        val buttonBack: ImageButton = findViewById(R.id.button_back)
        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java )
            this.startActivity(intent)
        }

        listItems.layoutManager = LinearLayoutManager(this)
        listItems.adapter = ItemsAdapter(items, this)



//
//        val itemsList: RecyclerView = findViewById(R.id.itemsList)
//
//
//
//        itemsList.layoutManager = LinearLayoutManager(this)
//        itemsList.adapter = ItemsAdapter(items, this)




    }
}