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

    private String databaseName;

    @Override
    public String getDatabaseContent() {
        return getDatabaseAsString(databaseName);
    }

    public Database(Context context, String databaseName) {
        this.context = context;
        this.databaseName = databaseName;
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
