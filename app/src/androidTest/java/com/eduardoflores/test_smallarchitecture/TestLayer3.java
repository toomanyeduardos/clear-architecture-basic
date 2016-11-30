package com.eduardoflores.test_smallarchitecture;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.eduardoflores.test_smallarchitecture.layer_1.Rectangle;
import com.eduardoflores.test_smallarchitecture.layer_3.DatabaseAdapter;
import com.eduardoflores.test_smallarchitecture.layer_4.Database;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by eflores on 11/30/16.
 */

public class TestLayer3 extends ApplicationTestCase<Application> {

    DatabaseAdapter databaseAdapter;

    // mandatory constructor
    public TestLayer3() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Need to setup the DatabaseAdapter the same way it is setup in the ServiceLocator. Not sure if its correct.
        String databaseName = "rectangles-data.json";
        databaseAdapter = new DatabaseAdapter(new Database(getContext(), databaseName));
    }

    public void testDatabase() {
        List<Rectangle> rectangleList = databaseAdapter.getListRectangles();
        assertNotNull(rectangleList);
        for (Rectangle rectangle : rectangleList) {
            assertNotNull(rectangle.height);
            assertThat(rectangle.height, greaterThan(0.0));
            assertNotNull(rectangle.width);
            assertThat(rectangle.width, greaterThan(0.0));
        }
    }
}
