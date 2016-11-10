package com.eduardoflores.test_smallarchitecture.layer_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.eduardoflores.test_smallarchitecture.R;
import com.eduardoflores.test_smallarchitecture.ServiceLocator;
import com.eduardoflores.test_smallarchitecture.layer_1.Rectangle;
import com.eduardoflores.test_smallarchitecture.layer_2.UseCase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public UseCase useCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServiceLocator.getInstance().configureMainActivity(this);

        TextView areaTV = (TextView) findViewById(R.id.area_rectangle);

        String outputText = "";
        List<Rectangle> rectangleList = useCase.getListRectangles();
        for (Rectangle rectangle : rectangleList) {
            outputText = outputText + useCase.getRectangleArea(rectangle.width, rectangle.height) + "\n";
        }
        areaTV.setText("\n\n\nArea: \n" + outputText);
    }

}
