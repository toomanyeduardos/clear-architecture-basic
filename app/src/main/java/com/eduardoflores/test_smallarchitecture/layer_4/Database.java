package com.eduardoflores.test_smallarchitecture.layer_4;

import android.content.Context;

import com.eduardoflores.test_smallarchitecture.inter_3_4.DatabaseInterface;
import com.eduardoflores.test_smallarchitecture.layer_1.Rectangle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eflores on 11/8/16.
 */

public class Database implements DatabaseInterface {

    private Context context;

    @Override
    public List<Rectangle> getListRectangles(Context context, String databaseName) {
        this.context = context;

        String databaseContent = getDatabaseAsString(databaseName);
        return parseDatabaseContent(databaseContent);
    }

    private String getDatabaseAsString(String databaseName) {
        String content = null;
        try {
            InputStream stream = context.getAssets().open(databaseName);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            content = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return content;
    }

    private List<Rectangle> parseDatabaseContent(String databaseContent) {
        List<Rectangle> rectangleList = new ArrayList<>();
        try {
            JSONObject rootObject = new JSONObject(databaseContent);
            JSONArray rectanglesArray = rootObject.getJSONArray("rectangles");
            for (int i = 0; i < rectanglesArray.length(); i++) {
                Rectangle rectangle = new Rectangle();
                JSONObject rectangleObject = rectanglesArray.getJSONObject(i);

                rectangle.height = rectangleObject.getDouble("h");
                rectangle.length = rectangleObject.getDouble("w");

                rectangleList.add(rectangle);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rectangleList;
    }
}
