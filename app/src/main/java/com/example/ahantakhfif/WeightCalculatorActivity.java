package com.example.ahantakhfif;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class WeightCalculatorActivity extends AppCompatActivity {

    EditText wire_size_edt , mesh_size_edt;
    Button fans_calculator_btn;
    TextView weight_tv;
    ImageView back_icon_fans_weight;

    double weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_calculator);

        wire_size_edt = findViewById(R.id.wire_size_edt);
        mesh_size_edt = findViewById(R.id.mesh_size_edt);

        fans_calculator_btn = findViewById(R.id.btn_fans_weight_calculate);

        weight_tv = findViewById(R.id.weight_fans_tv);

        back_icon_fans_weight = findViewById(R.id.back_icon_fans_weight);
        back_icon_fans_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        fans_calculator_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                DecimalFormat formatter = new DecimalFormat("#0.000");


                if(wire_size_edt.getText().toString().trim().isEmpty()){

                    Toast.makeText(WeightCalculatorActivity.this, "لظفا سایز مفتول را وارد کنید!", Toast.LENGTH_SHORT).show();
                    wire_size_edt.requestFocus();

                }else if(mesh_size_edt.getText().toString().trim().isEmpty()){

                    Toast.makeText(WeightCalculatorActivity.this, "لظفا سایز چشمه را وارد کنید!", Toast.LENGTH_SHORT).show();
                    mesh_size_edt.requestFocus();

                }else{


                    double wire_size = Double.parseDouble(wire_size_edt.getText().toString().trim());
                    double mesh_size = Double.parseDouble(mesh_size_edt.getText().toString().trim());

                    if(wire_size == 0 || mesh_size == 0) {

                        Toast.makeText(WeightCalculatorActivity.this, "لطفا اندازه را درست وارد نمایید!", Toast.LENGTH_SHORT).show();

                    }else{

                        if(mesh_size < 5 && mesh_size > 0){

                            weight = (wire_size * wire_size * 1.4) / mesh_size;
                            weight_tv.setText(formatter.format(weight) + " kg");
                            imm.hideSoftInputFromWindow(view.getWindowToken() , 0);

                        }else{

                            if(wire_size >= 3){

                                weight = (wire_size * wire_size * 1.4) / mesh_size;
                                weight_tv.setText(formatter.format(weight) + " kg");
                                imm.hideSoftInputFromWindow(view.getWindowToken() , 0);

                            }else if(wire_size < 3 && wire_size > 0){

                                weight = (wire_size * wire_size * 1.3) / mesh_size;
                                weight_tv.setText(formatter.format(weight) + " kg");
                                imm.hideSoftInputFromWindow(view.getWindowToken() , 0);

                            }
                        }

                    }


                }

            }
        });

    }
}