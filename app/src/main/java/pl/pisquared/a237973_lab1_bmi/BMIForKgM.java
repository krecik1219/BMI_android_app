package pl.pisquared.a237973_lab1_bmi;


/**
 * Created by Kretek on 08/03/2018.
 */

public class BMIForKgM extends BMI
{
    private static final double MIN_HEIGHT = 0.5;  // meters
    private static final double MAX_HEIGHT = 3;  // meters
    private static final double MIN_MASS = 5;  // kilograms
    private static final double MAX_MASS = 1000;  // kilograms

    public BMIForKgM(double mass, double height)
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

        return mass/(height*height);
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
