package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.secretk.move.R;

/**
 * Created by zc on 2018/4/23.
 */

public class DemoActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }
    public void main(View view){
        intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void http(View view){
        intent=new Intent(this,HttpActivity.class);
        startActivity(intent);
    }
    public void addlabel(View view){
        intent=new Intent(this,AddLabelActivity.class);
        startActivity(intent);
    }
}
