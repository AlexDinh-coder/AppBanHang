package com.manager.appbanhang.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.manager.appbanhang.R;

import io.paperdb.Paper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_main);
        Paper.init(this);
        Thread thread = new Thread(){
            public void run(){
                try{
                   sleep(2000);
                }catch(Exception ex){

                }finally {
                    if(Paper.book().read("user") == null){
                        Intent intent = new Intent(getApplicationContext(), DangNhapMainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent home = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(home);
                        finish();
                    }


                }
            }
        };
        thread.start();

    }
}