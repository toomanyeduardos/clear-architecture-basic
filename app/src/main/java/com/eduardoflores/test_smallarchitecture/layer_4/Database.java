package com.eduardoflores.test_smallarchitecture.layer_4;

import android.content.Context;

import com.eduardoflores.test_smallarchitecture.interface_3_4.DatabaseInterface;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by eflores on 11/8/16.
 */

public class Database implements DatabaseInterface {

    private Context context;

    @Override
    public String getDatabaseContent(Context context, String databaseName) {
        this.context = context;
        return getDatabaseAsString(databaseName);
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
}
