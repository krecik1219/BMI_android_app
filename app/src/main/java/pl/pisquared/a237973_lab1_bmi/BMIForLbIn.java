package pl.pisquared.a237973_lab1_bmi;

/**
 * Created by Kretek on 13/03/2018.
 */

public class BMIForLbIn extends BMI
{
    private static final double MIN_HEIGHT = 19;  // inches
    private static final double MAX_HEIGHT = 119;  // inches
    private static final double MIN_MASS = 11;  // pounds
    private static final double MAX_MASS = 2205;  // pounds
    private static final int BMI_IMPERIAL_CONVERSION_FACTOR = 703;

    public BMIForLbIn(double mass, double height)
    {
        super(mass, height);
    }

    @Override
    public double calculateBMI()
    {
        boolean massIsValid= massDataIsValid();
        boolean heightIsValid = heightDataIsValid();
        if(!massIsValid && !heightIsValid)
            throw new InvalidMassAndHeightException("Provided mass and height are invalid");
        if(!massIsValid)
            throw new InvalidMassException("Provided mass is invalid");
        if(!heightIsValid)
            throw new InvalidHeightException("Provided height is invalid");

        return mass/(height * height) * BMI_IMPERIAL_CONVERSION_FACTOR;
    }

    @Override
    protected boolean massDataIsValid()
    {
        return mass>=MIN_MASS && mass<=MAX_MASS;
    }

    @Override
    protected boolean heightDataIsValid()
    {
        return height>=MIN_HEIGHT && height<= MAX_HEIGHT;
    }
}
