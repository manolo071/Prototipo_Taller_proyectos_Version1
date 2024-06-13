package com.example.controlversion12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToRealTimeControl(View view) {
        Intent intent = new Intent(this, RealTimeControlActivity.class);
        startActivity(intent);
    }

    public void goToAlerts(View view) {
        Intent intent = new Intent(this, AlertsActivity.class);
        startActivity(intent);
    }

    public void goToReports(View view) {
        Intent intent = new Intent(this, ReportsActivity.class);
        startActivity(intent);
    }
}