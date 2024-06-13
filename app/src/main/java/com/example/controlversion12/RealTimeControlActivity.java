package com.example.controlversion12;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class RealTimeControlActivity extends AppCompatActivity {

    private EditText[] dayInputs;
    private Button btnSave, btnUpdate;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_control);

        dayInputs = new EditText[7];
        dayInputs[0] = findViewById(R.id.editTextDay1);
        dayInputs[1] = findViewById(R.id.editTextDay2);
        dayInputs[2] = findViewById(R.id.editTextDay3);
        dayInputs[3] = findViewById(R.id.editTextDay4);
        dayInputs[4] = findViewById(R.id.editTextDay5);
        dayInputs[5] = findViewById(R.id.editTextDay6);
        dayInputs[6] = findViewById(R.id.editTextDay7);

        btnSave = findViewById(R.id.btnSave);
        btnUpdate = findViewById(R.id.btnUpdate);

        Calendar FirebaseDatabase;
        databaseReference = FirebaseDatabase.getInstance().getReference("waterQuality");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        fetchData();
    }

    private void saveData() {
        for (int i = 0; i < 7; i++) {
            String day = "day" + (i + 1);
            String value = dayInputs[i].getText().toString();
            databaseReference.child(day).setValue(value);
        }
        Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show();
    }

    private void updateData() {
        fetchData();
        Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show();
    }

    private void fetchData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i < 7; i++) {
                    String day = "day" + (i + 1);
                    String value = dataSnapshot.child(day).getValue(String.class);
                    if (value != null) {
                        dayInputs[i].setText(value);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RealTimeControlActivity.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void backToMain(View view) {
        finish();
    }
}