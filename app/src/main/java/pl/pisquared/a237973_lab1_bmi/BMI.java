package pl.pisquared.a237973_lab1_bmi;

/**
 * Created by Kretek on 08/03/2018.
 */

public abstract class BMI
{
    protected static final int AQUA_COLOR = 0xff00ffff;
    protected static final int LIME_COLOR = 0xff00ff00;
    protected static final int YELLOW_COLOR = 0xffffff00;
    protected static final int ORANGE_COLOR = 0xffff6600;
    protected static final int RED_COLOR = 0xffff0000;
    protected double mass;
    protected double height;
    private static final double MIN_LIMIT_BMI_NORMAL_WEIGHT = 18.5;
    private static final double MIN_LIMIT_BMI_OVERWEIGHT = 25;
    private static final double MIN_LIMIT_BMI_OBESE = 30;
    private static final double MIN_LIMIT_BMI__HIGH_OBESE = 40;

    public BMI(double mass, double height)
    {
        this.mass = mass;
        this.height = height;
    }

    public static int getBMIColor(double bmi)
    {
        int bmiColor = 0;
        if(bmi<MIN_LIMIT_BMI_NORMAL_WEIGHT)
            bmiColor = BMI.AQUA_COLOR;
        else if(bmi>=MIN_LIMIT_BMI_NORMAL_WEIGHT && bmi <MIN_LIMIT_BMI_OVERWEIGHT)
            bmiColor = BMI.LIME_COLOR;
        else if (bmi>=MIN_LIMIT_BMI_OVERWEIGHT && bmi<MIN_LIMIT_BMI_OBESE)
            bmiColor = BMI.YELLOW_COLOR;
        else if(bmi>=MIN_LIMIT_BMI_OBESE && bmi<MIN_LIMIT_BMI__HIGH_OBESE)
            bmiColor = BMI.ORANGE_COLOR;
        else
            bmiColor = BMI.RED_COLOR;
        return bmiColor;
    }

    public class InvalidMassException extends IllegalArgumentException
    {
        InvalidMassException(String msg){super(msg);}
    }

    public class InvalidHeightException extends IllegalArgumentException
    {
        InvalidHeightException(String msg){super(msg);}
    }

    public class InvalidMassAndHeightException extends IllegalArgumentException
    {
        InvalidMassAndHeightException(String msg){super(msg);}
    }

    abstract public double calculateBMI();
    abstract protected boolean massDataIsValid();
    abstract protected boolean heightDataIsValid();
}
