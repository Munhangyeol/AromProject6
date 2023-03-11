package com.example.aromproject6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button Student_btn;
    private Button Kunja_btn;
    private Button Jinkwan_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Student_btn=findViewById(R.id.activity_student_button);
        Kunja_btn=findViewById(R.id.activity_kunja_button);
        Jinkwan_btn=findViewById(R.id.activity_jinkwan_button);
        Student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,StudentRestActivity.class);
                startActivity(intent);
            }
        });
        Kunja_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,GunjaRestActivity.class);
                startActivity(intent);
            }
        });
        Jinkwan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,JinkwanRestActivity.class);
                startActivity(intent);
            }
        });


    }
}
