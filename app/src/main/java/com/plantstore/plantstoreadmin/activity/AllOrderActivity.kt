package com.plantstore.plantstoreadmin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.plantstore.plantstoreadmin.R
import com.plantstore.plantstoreadmin.adapter.AllOrderAdapter
import com.plantstore.plantstoreadmin.model.AllOrderModel

class AllOrderActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var list : ArrayList<AllOrderModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_order)
        list = ArrayList()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        Firebase.firestore.collection("allOrders").get().addOnSuccessListener {
            list.clear()
            for (doc in it){
                val data = doc.toObject(AllOrderModel::class.java)
                list.add(data)
            }
            recyclerView.adapter = AllOrderAdapter(list, this)
        }

        auth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.button7).setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}