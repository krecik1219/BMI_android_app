package pl.pisquared.a237973_lab1_bmi;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AboutAuthorActivity extends AppCompatActivity
{
    private ImageButton ibBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_author);
        ibBackButton = findViewById(R.id.b_back_from_author);
        ibBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                NavUtils.navigateUpFromSameTask(AboutAuthorActivity.this);
            }
        });
    }
}
