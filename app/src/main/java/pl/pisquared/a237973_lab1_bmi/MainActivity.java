package pl.pisquared.a237973_lab1_bmi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import pl.pisquared.a237973_lab1_bmi.utils.UnitsUtils;

public class MainActivity extends AppCompatActivity
{
    public static final String BMI_VALUE_KEY = "bmi_value";
    public static final String UNIT_TYPE_KEY = "unit_type_key";
    public static final String MASS_KEY = "mass_key";
    public static final String HEIGHT_KEY = "height_key";
    public static final String INVALID_NUMBER_ERROR_MSG = "Please provide valid number";
    public static final String INVALID_MASS_ERROR_MSG = "Please provide number between 5 - 1000 for KG or 11 - 2205 for lb";
    public static final String INVALID_HEIGHT_ERROR_MSG = "Please provide number between 0.5 - 3 for meters or 19 - 119 for inches";

    private int currentUnitType;
    private EditText etProvideMassField;
    private EditText etProvideHeightField;
    private Button bCountBMI;
    private RadioGroup rgUnitsSwitch;
    private TextView tvMassLabel;
    private TextView tvHeightLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etProvideMassField = findViewById(R.id.et_edit_mass);
        etProvideHeightField = findViewById(R.id.et_edit_height);
        tvMassLabel = findViewById(R.id.tv_mass_label);
        tvHeightLabel = findViewById(R.id.tv_height_label);
        rgUnitsSwitch = findViewById(R.id.rg_units_switch);
        rgUnitsSwitch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                checkAndSetCurrentUnits(checkedId);
            }
        });
        bCountBMI = findViewById(R.id.count_bmi);
        bCountBMI.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                countBMI();
            }
        });
        currentUnitType = UnitsUtils.getPhoneUnitsType();
        setUnitsRadioGroup();
        loadSavedState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int selectedId = item.getItemId();
        if(selectedId == R.id.about_author_item)
        {
            Intent goAboutAuthorIntent = new Intent(this, AboutAuthorActivity.class);
            startActivity(goAboutAuthorIntent);
            return true;
        }
        if(selectedId == R.id.save_menu_item)
        {
            saveAppState();
            return true;
        }
        if(selectedId == R.id.delete_save_item)
        {
            clearSavedState();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearSavedState()
    {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        spEditor.clear();
        spEditor.apply();
        Toast.makeText(this, R.string.on_state_deleted, Toast.LENGTH_SHORT).show();
    }

    private void saveAppState()
    {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        String mass = etProvideMassField.getText().toString();
        String height = etProvideHeightField.getText().toString();
        spEditor.putString(MASS_KEY, mass);
        spEditor.putString(HEIGHT_KEY, height);
        spEditor.putInt(UNIT_TYPE_KEY, currentUnitType);
        spEditor.apply();
        Toast.makeText(this, R.string.on_state_saved, Toast.LENGTH_SHORT).show();
    }

    private void loadSavedState()
    {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String mass = sharedPreferences.getString(MASS_KEY, "");
        String height = sharedPreferences.getString(HEIGHT_KEY, "");
        int unitsType = sharedPreferences.getInt(UNIT_TYPE_KEY, currentUnitType);
        if(unitsType == UnitsUtils.METRIC_UNITS_CODE)
            rgUnitsSwitch.check(R.id.rb_kg_m_units);
        else
            rgUnitsSwitch.check(R.id.rb_lb_in_units);
        etProvideMassField.setText(mass);
        etProvideHeightField.setText(height);
    }

    private void setUnitsRadioGroup()
    {
        if(currentUnitType == UnitsUtils.METRIC_UNITS_CODE)
            rgUnitsSwitch.check(R.id.rb_kg_m_units);
        else
            rgUnitsSwitch.check(R.id.rb_lb_in_units);
    }

    private void checkAndSetCurrentUnits(int checkedId)
    {
        if(checkedId == R.id.rb_kg_m_units)
        {
            currentUnitType = UnitsUtils.METRIC_UNITS_CODE;
            tvMassLabel.setText(R.string.mass_kg);
            tvHeightLabel.setText(R.string.height_m);
        }
        else if(checkedId == R.id.rb_lb_in_units)
        {
            currentUnitType = UnitsUtils.IMPERIAL_UNITS_CODE;
            tvMassLabel.setText(R.string.mass_lb);
            tvHeightLabel.setText(R.string.height_in);
        }
        convertUnitsEditTextFields();
    }

    private void countBMI()
    {
        String massStr = etProvideMassField.getText().toString();
        String heightStr = etProvideHeightField.getText().toString();
        boolean massIsCorrect = false;
        boolean heightIsCorrect = false;
        double mass=0;
        double height = 0;
        try
        {
            mass = Double.parseDouble(massStr);
            massIsCorrect = true;
        }
        catch(NumberFormatException e)
        {
            setErrorMsgInEditText(INVALID_NUMBER_ERROR_MSG, etProvideMassField);
        }
        try
        {
            height = Double.parseDouble(heightStr);
            heightIsCorrect = true;
        }
        catch(NumberFormatException e)
        {
            setErrorMsgInEditText(INVALID_NUMBER_ERROR_MSG, etProvideHeightField);
        }
        if(massIsCorrect && heightIsCorrect)
        {
            try
            {
                BMI bmiCalculator = null;
                if(currentUnitType == UnitsUtils.METRIC_UNITS_CODE)
                    bmiCalculator= new BMIForKgM(mass, height);
                else
                    bmiCalculator= new BMIForLbIn(mass, height);
                double bmi = bmiCalculator.calculateBMI();
                Intent bmiDisplayIntent = new Intent(this, BMIDisplayActivity.class);
                bmiDisplayIntent.putExtra(BMI_VALUE_KEY, bmi);
                startActivity(bmiDisplayIntent);
            }
            catch(BMI.InvalidMassAndHeightException e)
            {
                setErrorMsgInEditText(INVALID_MASS_ERROR_MSG, etProvideMassField);
                setErrorMsgInEditText(INVALID_HEIGHT_ERROR_MSG, etProvideHeightField);
            }
            catch(BMI.InvalidMassException e)
            {
                setErrorMsgInEditText(INVALID_MASS_ERROR_MSG, etProvideMassField);
            }
            catch(BMI.InvalidHeightException e)
            {
                setErrorMsgInEditText(INVALID_HEIGHT_ERROR_MSG, etProvideHeightField);
            }
        }
    }

    private void setErrorMsgInEditText(String errMsg, EditText etField)
    {
        etField.setError(errMsg);
    }

    private void convertUnitsEditTextFields()
    {
        String massStr = replaceCommasWithDots(etProvideMassField.getText().toString());
        String heightStr = replaceCommasWithDots(etProvideHeightField.getText().toString());
        if(massStr.isEmpty() || heightStr.isEmpty())
        {
            etProvideMassField.setText("");
            etProvideHeightField.setText("");
        }
        else
        {
            try
            {
                double mass = Double.parseDouble(massStr);
                double height = Double.parseDouble(heightStr);

                double convertedMass = 0;
                double convertedHeight = 0;
                if(currentUnitType == UnitsUtils.METRIC_UNITS_CODE)
                {
                    convertedMass = UnitsUtils.poundsToKilograms(mass);
                    convertedHeight = UnitsUtils.inchesToMeters(height);
                }
                else
                {
                    convertedMass = UnitsUtils.kilogramsToPounds(mass);
                    convertedHeight = UnitsUtils.metersToInches(height);
                }
                etProvideMassField.setText(String.format(Locale.US, "%.2f", convertedMass));
                etProvideHeightField.setText(String.format(Locale.US, "%.2f", convertedHeight));
            }
            catch(NumberFormatException e)
            {
                Toast.makeText(this, R.string.err_invalid_input, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String replaceCommasWithDots(String numberLikeString)
    {
        return numberLikeString.replace(',', '.');
    }
}
