package com.eduardoflores.test_smallarchitecture.layer_2;

import android.content.Context;

import com.eduardoflores.test_smallarchitecture.inter_1_2.GeometryInterface;
import com.eduardoflores.test_smallarchitecture.inter_3_4.DatabaseInterface;
import com.eduardoflores.test_smallarchitecture.layer_1.Rectangle;
import com.eduardoflores.test_smallarchitecture.layer_1.RectangleGeometry;
import com.eduardoflores.test_smallarchitecture.layer_4.Database;

import java.util.List;

/**
 * Created by eflores on 11/8/16.
 */

public class UseCase {

    public double getRectangleArea(double w, double h) {
        GeometryInterface geometryInterface = new RectangleGeometry();
        return geometryInterface.area(getValidatedWidth(w), getValidatedHeight(h));
    }

    private double getValidatedWidth(double w) {
        return Math.abs(w);
    }

    private double getValidatedHeight(double h) {
        return Math.abs(h);
    }

    public List<Rectangle> getListRectangles(Context context, String databaseName) {
        DatabaseInterface databaseInterface = new Database();
        return databaseInterface.getListRectangles(context, databaseName);
    }
}
