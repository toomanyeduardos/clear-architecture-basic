package com.eduardoflores.test_smallarchitecture;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.eduardoflores.test_smallarchitecture.interface_1_2.GeometryInterface;
import com.eduardoflores.test_smallarchitecture.layer_1.RectangleGeometry;

/**
 * Created by eflores on 11/30/16.
 */

public class TestLayer1 extends ApplicationTestCase<Application> {

    // mandatory constructor
    public TestLayer1() {
        super(Application.class);
    }

    public void testRectangleArea() {
        GeometryInterface geometryInterface = new RectangleGeometry();
        assertNotNull(geometryInterface);

        assertEquals(geometryInterface.area(4, 5), 20.0);
    }
}
