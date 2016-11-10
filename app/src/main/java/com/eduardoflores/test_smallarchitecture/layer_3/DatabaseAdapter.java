package com.eduardoflores.test_smallarchitecture.layer_3;

import com.eduardoflores.test_smallarchitecture.interface_2_3.DatabaseAdapterInterface;
import com.eduardoflores.test_smallarchitecture.ServiceLocator;
import com.eduardoflores.test_smallarchitecture.interface_3_4.DatabaseInterface;
import com.eduardoflores.test_smallarchitecture.layer_1.Rectangle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eflores on 11/10/16.
 */

public class DatabaseAdapter implements DatabaseAdapterInterface {

    DatabaseInterface databaseInterface;

    public DatabaseAdapter(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    @Override
    public List<Rectangle> getListRectangles() {
        databaseInterface = ServiceLocator.getInstance().getDatabase();
        String databaseContent = databaseInterface.getDatabaseContent();

        List<Rectangle> rectangleList = new ArrayList<>();
        try {
            JSONObject rootObject = new JSONObject(databaseContent);
            JSONArray rectanglesArray = rootObject.getJSONArray("rectangles");
            for (int i = 0; i < rectanglesArray.length(); i++) {
                Rectangle rectangle = new Rectangle();
                JSONObject rectangleObject = rectanglesArray.getJSONObject(i);

                rectangle.height = rectangleObject.getDouble("h");
                rectangle.width = rectangleObject.getDouble("w");

                rectangleList.add(rectangle);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rectangleList;
    }
}
