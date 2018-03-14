package pl.pisquared.a237973_lab1_bmi.utils;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by Kretek on 13/03/2018.
 */

public class UnitsUtils
{
    public static final int METRIC_UNITS_CODE = 1;
    public static final int IMPERIAL_UNITS_CODE = 2;

    private static final String[] COUNTRIES_USING_IMPERIAL_UNITS = {"LR", "US", "GB", "MM"};
    private static final double POUNDS_TO_KILOGRAMS_FACTOR = 0.45359237;
    private static final double KILOGRAMS_TO_POUNDS_FACTOR = 2.20462;
    private static final double INCHES_TO_METERS_FACTOR = 0.0254;
    private static final double METERS_TO_INCHES_FACTOR = 39.3700787;

    public static int getPhoneUnitsType()
    {
        String countryCode = Locale.getDefault().getCountry();
        boolean isImperial = false;
        for(int i=0; i<COUNTRIES_USING_IMPERIAL_UNITS.length && !isImperial; i++)
        {
            if(Objects.equals(countryCode, COUNTRIES_USING_IMPERIAL_UNITS[i]))
                isImperial = true;
        }
        if(isImperial)
            return IMPERIAL_UNITS_CODE;
        else
            return METRIC_UNITS_CODE;
    }

    public static double poundsToKilograms(double pounds)
    {
        return pounds * POUNDS_TO_KILOGRAMS_FACTOR;
    }

    public static double kilogramsToPounds(double kilograms)
    {
        return kilograms * KILOGRAMS_TO_POUNDS_FACTOR;
    }

    public static double inchesToMeters(double inches)
    {
        return inches * INCHES_TO_METERS_FACTOR;
    }

    public static double metersToInches(double meters)
    {
        return meters * METERS_TO_INCHES_FACTOR;
    }
}
