package com.eduardoflores.test_smallarchitecture.inter_3_4;

import android.content.Context;

import com.eduardoflores.test_smallarchitecture.layer_1.Rectangle;

import java.util.List;

/**
 * Created by eflores on 11/8/16.
 */

public interface DatabaseInterface {

    List<Rectangle> getListRectangles(Context context, String databaseName);
}
