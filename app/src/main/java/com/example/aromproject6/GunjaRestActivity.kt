package com.example.aromproject6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aromproject6.R
import android.content.Intent
import com.example.aromproject6.MainActivity
import android.widget.TextView
import android.widget.ProgressBar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.OnFailureListener
import android.content.res.ColorStateList
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot

class GunjaRestActivity : AppCompatActivity(), Runnable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gunja_rest_main)
    }

    override fun run() {}
}