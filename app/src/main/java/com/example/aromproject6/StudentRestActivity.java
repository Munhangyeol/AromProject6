package com.example.aromproject6;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class StudentRestActivity extends AppCompatActivity implements Runnable{

    TextView textView;
    TextView Student_Text;
    private  Button narutto_btn;
    private  Button azio_btn;
    private  Button kimbab_btn;
    ProgressBar Student_ProgressBar;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    int current_time=0;
    int Student_Num=0;

    private int userID=0;
    private int user_current_time=0;
    private String strID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
       // Student_ProgressBar.setMax(256);
        setContentView(R.layout.student_rest_main);
       narutto_btn=findViewById(R.id.student_rest_narrotto_btn);
       azio_btn=findViewById(R.id.student_rest_azio_btn);
       kimbab_btn=findViewById(R.id.student_rest_kimbab_btn);


        Student_ProgressBar=findViewById(R.id.rest_student_bar);


        Student_ProgressBar.setProgress(1);


        Thread t=new Thread(this);
        t.start();
        narutto_btn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                Map<String,Object> user=new HashMap<>();
                strID=String.valueOf(userID);
                user.put("userID",user_current_time);

                db.collection("User_Student").document("user"+strID)
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                System.out.println("회원가입에 성공 하였습니다! 가입자 성명: "+userID);
                                userID++;
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("회원가입에 실패하였습니다.");

                            }
                        });

            }
        });



    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(5000);
                current_time+=5;
                user_current_time=current_time;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Student_ProgressBar.setProgress(Student_Num);

            if(Student_Num<100) {
                Student_ProgressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#00FF00")));

            }
            if(Student_Num>180) {
                Student_ProgressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));

            }
            if(Student_Num>=100&&Student_Num<=180){
                Student_ProgressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#0000FF")));

            }
            System.out.println("StudentNum:"+Student_Num);


            Student_Num=0;
            db.collection("User_Student")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    Student_Num++;
                                    System.out.println("Current Time: "+current_time+"DB Time: "+document.getData().get("userID"));
                                    if ( current_time- Integer.valueOf(document.getData().get("userID").toString())>= 30) {
                                        System.out.println("30초 경과 "+document.getData().get("userID"));
                                     if(Student_Num>1)
                                      db.collection("User").document(document.getId().toString()).delete();

                                    }

                                }

                            } else {
                                System.out.println("This is Error Doccument");

                            }


                        }

                    });
        }

    }
}

