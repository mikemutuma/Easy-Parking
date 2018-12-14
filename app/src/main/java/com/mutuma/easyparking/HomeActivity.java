package com.mutuma.easyparking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    EditText reg, spot, hour;
    TextView error,total;
    Button confirm,view;
    ProgressDialog dialog;
    List<String> spots = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        reg = (EditText) findViewById(R.id.etdRegNo);
        spot = (EditText) findViewById(R.id.edtspot);
        hour = (EditText) findViewById(R.id.edhour);
        error = (TextView) findViewById(R.id.tverror);
        total = (TextView) findViewById(R.id.tvtt);
        confirm = (Button) findViewById(R.id.btnconfirm);
        view = (Button) findViewById(R.id.btnview);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Saving...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reg.getText().toString().isEmpty() || spot.getText().toString().isEmpty() || hour.getText().toString().isEmpty()){

                    error.setTextColor(Color.RED);
                    error.setText("Fill in everything");

                }else {
                    if (spots.contains(spot.getText().toString())){

                        error.setTextColor(Color.RED);
                        error.setText("Spot already taken");

                    }else {
                        spots.add(spot.getText().toString());
                        error.setTextColor(Color.GREEN);
                        error.setText("Parking Confirmed");

                        long time = System.currentTimeMillis();
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Magari/"+time);
                        String kitambulisho = reg.getText().toString();
                        String kiegezo = spot.getText().toString();
                        String muda = hour.getText().toString();
                        Item x = new Item(kitambulisho,kiegezo,muda);
                        dialog.show();
                        ref.setValue(x).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                dialog.dismiss();
                                if(task.isSuccessful()){
                                    Toast.makeText(HomeActivity.this, "Booked Successfully", Toast.LENGTH_SHORT).show();
                                    reg.setText("");
                                    spot.setText("");
                                    hour.setText("");
                                }else{
                                    Toast.makeText(HomeActivity.this, "Booking Failed", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });

                    }

                    total.setText(Double.toString(calculateTotal()));

                }

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pesa = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.android.stk");
                if (pesa !=null){
                    startActivity(pesa);
                }else {
                    Toast.makeText(HomeActivity.this, "No STK Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,UsersActivity.class);
                startActivity(intent);
            }
        });

    }

    public double calculateTotal(){
        double i = Double.parseDouble(hour.getText().toString()) * 100;
        return i;
    }

}
