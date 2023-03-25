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
import android.graphics.Color
import android.view.View
import android.widget.Button
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.util.HashMap

class StudentRestActivity : AppCompatActivity(), Runnable {
    var textView: TextView? = null
    var Student_Text: TextView? = null
    private var narutto_btn: Button? = null
    private var azio_btn: Button? = null
    private var kimbab_btn: Button? = null
    var Student_ProgressBar: ProgressBar? = null
    var db = FirebaseFirestore.getInstance()
    var current_time = 0
    var Student_Num = 0
    private var userID = 0
    private var user_current_time = 0
    private var strID: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Student_ProgressBar.setMax(256);
        setContentView(R.layout.student_rest_main)
        narutto_btn = findViewById(R.id.student_rest_narrotto_btn)
        azio_btn = findViewById(R.id.student_rest_azio_btn)
        kimbab_btn = findViewById(R.id.student_rest_kimbab_btn)
        Student_ProgressBar = findViewById(R.id.rest_student_bar)
        Student_ProgressBar?.setProgress(1)
        val t = Thread(this)
        t.start()
        narutto_btn?.setOnClickListener(View.OnClickListener {
            val user: MutableMap<String, Any> = HashMap()
            strID = userID.toString()
            user["userID"] = user_current_time
            db.collection("User_Student").document("user$strID")
                .set(user)
                .addOnSuccessListener {
                    println("회원가입에 성공 하였습니다! 가입자 성명: $userID")
                    userID++
                }
                .addOnFailureListener { println("회원가입에 실패하였습니다.") }
        })
    }

    override fun run() {
        while (true) {
            try {
                Thread.sleep(5000)
                current_time += 5
                user_current_time = current_time
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            Student_ProgressBar!!.progress = Student_Num
            if (Student_Num < 100) {
                Student_ProgressBar!!.progressTintList =
                    ColorStateList.valueOf(Color.parseColor("#00FF00"))
            }
            if (Student_Num > 180) {
                Student_ProgressBar!!.progressTintList =
                    ColorStateList.valueOf(Color.parseColor("#FF0000"))
            }
            if (Student_Num >= 100 && Student_Num <= 180) {
                Student_ProgressBar!!.progressTintList =
                    ColorStateList.valueOf(Color.parseColor("#0000FF"))
            }
            println("StudentNum:$Student_Num")
            Student_Num = 0
            db.collection("User_Student")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            Student_Num++
                            println("Current Time: " + current_time + "DB Time: " + document.data["userID"])
                            if (current_time - Integer.valueOf(document.data["userID"].toString()) >= 30) {
                                println("30초 경과 " + document.data["userID"])
                                if (Student_Num > 1) db.collection("User").document(document.id)
                                    .delete()
                            }
                        }
                    } else {
                        println("This is Error Doccument")
                    }
                }
        }
    }
}