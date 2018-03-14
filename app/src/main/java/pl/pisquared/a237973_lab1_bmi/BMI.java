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

    public BMI(double mass, double height)
    {
        this.mass = mass;
        this.height = height;
    }

    public static int getBMIColor(double bmi)
    {
        int bmiColor = 0;
        if(bmi<18.5)
            bmiColor = BMI.AQUA_COLOR;
        else if(bmi>=18.5 && bmi <25)
            bmiColor = BMI.LIME_COLOR;
        else if (bmi>=25 && bmi<30)
            bmiColor = BMI.YELLOW_COLOR;
        else if(bmi>=30 && bmi<40)
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
