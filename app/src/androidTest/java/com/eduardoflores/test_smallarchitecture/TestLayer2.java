package com.eduardoflores.test_smallarchitecture;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.eduardoflores.test_smallarchitecture.layer_2.UseCase;

/**
 * Created by eflores on 11/30/16.
 */

public class TestLayer2 extends ApplicationTestCase<Application> {

    // mandatory constructor
    public TestLayer2() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    // Test UseCase class
    public void testGetRectangleArea() {
        final UseCase useCase = new UseCase(null);
        final double area = useCase.getRectangleArea(4, 3);
        assertEquals(area, 12.0);
    }
}