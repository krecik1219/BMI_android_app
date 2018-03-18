package pl.pisquared.a237973_lab1_bmi;

import org.junit.Test;

import pl.pisquared.a237973_lab1_bmi.utils.UnitsUtils;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
    @Test
    public void addition_isCorrect() throws Exception
    {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void for_valid_mass_and_height_should_return_correct_value_kgm()
    {
        BMI bmi = new BMIForKgM(60, 1.70);
         assertEquals(20.761,bmi.calculateBMI(),0.001);
    }

    @Test(expected = BMI.InvalidMassAndHeightException.class)
    public void for_invalid_mass_and_height_should_throw_exception_kgm()
    {
        BMI bmi = new BMIForKgM(0, 0);
        bmi.calculateBMI();
    }

    @Test(expected = BMI.InvalidMassException.class)
    public void for_invalid_mass_should_throw_exception_kgm()
    {
        BMI bmi = new BMIForKgM(0, 1.56);
        bmi.calculateBMI();
    }

    @Test(expected = BMI.InvalidHeightException.class)
    public void for_invalid_height_should_throw_exception_kgm()
    {
        BMI bmi = new BMIForKgM(76, 3.45);
        bmi.calculateBMI();
    }

    @Test
    public void for_valid_mass_and_height_should_return_correct_value_lbin()
    {
        BMI bmi = new BMIForLbIn(132.2774, 66.92913);
        assertEquals(20.759,bmi.calculateBMI(),0.001);
    }

    @Test(expected = BMI.InvalidMassAndHeightException.class)
    public void for_invalid_mass_and_height_should_throw_exception_lbin()
    {
        BMI bmi = new BMIForLbIn(0, 0);
        bmi.calculateBMI();
    }

    @Test(expected = BMI.InvalidMassException.class)
    public void for_invalid_mass_should_throw_exception_lbin()
    {
        BMI bmi = new BMIForLbIn(4, 92);
        bmi.calculateBMI();
    }

    @Test(expected = BMI.InvalidHeightException.class)
    public void for_invalid_height_should_throw_exception_lbin()
    {
        BMI bmi = new BMIForLbIn(402, 3.45);
        bmi.calculateBMI();
    }

    @Test
    public void for_provided_bmi_should_return_correct_color_kgm()
    {
        BMI bmi = new BMIForKgM(80, 1.89);
        assertEquals(BMI.LIME_COLOR, BMI.getBMIColor(bmi.calculateBMI()));
    }

    @Test
    public void for_provided_bmi_should_return_correct_color2_kgm()
    {
        BMI bmi = new BMIForKgM(120, 1.89);
        assertEquals(BMI.ORANGE_COLOR, BMI.getBMIColor(bmi.calculateBMI()));
    }

    @Test
    public void kilograms_to_pounds_convert_test()
    {
        assertEquals(123.4589, UnitsUtils.kilogramsToPounds(56), 0.001);
    }

    @Test
    public void pounds_to_kilograms_convert_test()
    {
        assertEquals(25.40117, UnitsUtils.poundsToKilograms(56), 0.00001);
    }

    @Test
    public void meters_to_inches_convert_test()
    {
        assertEquals(72.83465, UnitsUtils.metersToInches(1.85), 0.00001);
    }

    @Test
    public void inches_to_meters_convert_test()
    {
        assertEquals(1.651, UnitsUtils.inchesToMeters(65), 0.01);
    }
}