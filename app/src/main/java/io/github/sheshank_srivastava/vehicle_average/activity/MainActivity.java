package io.github.sheshank_srivastava.vehicle_average.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import io.github.sheshank_srivastava.vehicle_average.AppManager;
import io.github.sheshank_srivastava.vehicle_average.R;

public class MainActivity extends AppCompatActivity {

    private TextView txt_CurrentAverage;
    private EditText edit_KiloMeter;
    private EditText edit_Litre;
    private Button btn_Calculate;
    private AppManager manager = AppManager.getInstance();
    public static MainActivity instance;

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);

        txt_CurrentAverage = findViewById(R.id.txtAverage);
        edit_KiloMeter = findViewById(R.id.editMeterNumber);
        edit_Litre = findViewById(R.id.editLitre);
        btn_Calculate = findViewById(R.id.btnCalculate);

        showData();


        btn_Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_KiloMeter.getText().toString().equals("") || edit_Litre.getText().toString().equals(""))
                    return;

                long current_kilometer = Integer.parseInt(edit_KiloMeter.getText().toString());
                float current_fuel = Float.parseFloat(edit_Litre.getText().toString());
                float current_average = current_kilometer / current_fuel;
                DecimalFormat decimal = new DecimalFormat(".###");

                String label_average = decimal.format(current_average);

                txt_CurrentAverage.setText(label_average);
                manager.saveData(current_kilometer, current_fuel);
            }
        });

    }

    private void showData() {
        if (true){
            Toast.makeText(instance, "Welcome, We got you data", Toast.LENGTH_SHORT).show();
            return;
        }
        txt_CurrentAverage.setText(manager.getCurrentAverage()+"");

    }


}