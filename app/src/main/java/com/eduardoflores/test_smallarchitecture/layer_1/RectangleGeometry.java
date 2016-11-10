package com.eduardoflores.test_smallarchitecture.layer_1;

import com.eduardoflores.test_smallarchitecture.interface_1_2.GeometryInterface;

/**
 * Created by eflores on 11/8/16.
 */

public class RectangleGeometry implements GeometryInterface {

    @Override
    public double area(double w, double h) {
        return w * h;
    }
}
