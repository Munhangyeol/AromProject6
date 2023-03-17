package com.example.aromproject6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aromproject6.R
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.aromproject6.StudentRestActivity
import com.example.aromproject6.GunjaRestActivity
import com.example.aromproject6.JinkwanRestActivity

class MainActivity : AppCompatActivity() {
    private var Student_btn: Button? = null
    private var Kunja_btn: Button? = null
    private var Jinkwan_btn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Student_btn = findViewById(R.id.activity_student_button)
        Kunja_btn = findViewById(R.id.activity_kunja_button)
        Jinkwan_btn = findViewById(R.id.activity_jinkwan_button)
        Student_btn?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, StudentRestActivity::class.java)
            startActivity(intent)
        })
        Kunja_btn?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, GunjaRestActivity::class.java)
            startActivity(intent)
        })
        Jinkwan_btn?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, JinkwanRestActivity::class.java)
            startActivity(intent)
        })
    }
}