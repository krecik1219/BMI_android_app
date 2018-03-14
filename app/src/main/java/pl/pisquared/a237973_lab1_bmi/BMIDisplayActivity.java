package pl.pisquared.a237973_lab1_bmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

import pl.pisquared.a237973_lab1_bmi.utils.UnitsUtils;

public class BMIDisplayActivity extends AppCompatActivity
{

    private TextView tvDisplayBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmidisplay);
        Intent receivedIntent = getIntent();
        double bmi = receivedIntent.getDoubleExtra(MainActivity.BMI_VALUE_KEY, 0);
        tvDisplayBMI = findViewById(R.id.tv_display_bmi);
        tvDisplayBMI.setText(String.format(Locale.US, "%.2f", bmi));
        getWindow().getDecorView().setBackgroundColor(BMI.getBMIColor(bmi));
    }
}
