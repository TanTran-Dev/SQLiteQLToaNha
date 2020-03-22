package com.trantan.sqliteqltoanha.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.trantan.sqliteqltoanha.R;
import com.trantan.sqliteqltoanha.model.Building;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonBuildingManager(View view) {
        Intent intent = new Intent(this,QLToaNhaActivity.class);
        startActivity(intent);
    }

    public void onButtonResidentManager(View view) {
        Intent intent = new Intent(this, QLCuDanActivity.class);
        startActivity(intent);
    }
}
