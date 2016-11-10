package com.eduardoflores.test_smallarchitecture.interface_2_3;

import com.eduardoflores.test_smallarchitecture.layer_1.Rectangle;

import java.util.List;

/**
 * Created by eflores on 11/10/16.
 */

public interface DatabaseAdapterInterface {

    List<Rectangle> getListRectangles(String databaseName);
}
