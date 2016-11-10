package com.eduardoflores.test_smallarchitecture;

import android.content.Context;

import com.eduardoflores.test_smallarchitecture.interface_3_4.DatabaseInterface;
import com.eduardoflores.test_smallarchitecture.layer_2.UseCase;
import com.eduardoflores.test_smallarchitecture.layer_3.DatabaseAdapter;
import com.eduardoflores.test_smallarchitecture.layer_4.Database;
import com.eduardoflores.test_smallarchitecture.layer_4.MainActivity;

/**
 * Created by eflores on 11/10/16.
 */

// *** name and package where this class will live is still undecided ***

public class ServiceLocator {

    private static ServiceLocator instance;

    private Context context;

    private String databaseName;

    public ServiceLocator(Context context, String databaseName) {
        this.context = context;
        this.databaseName = databaseName;
        instance = this;
    }

    public static ServiceLocator getInstance() {
        return instance;
    }

    public DatabaseInterface getDatabase() {
        return new Database(context, databaseName);
    }

    public void destroyComponent() {
        instance = null;
    }

    public DatabaseAdapter createDatabaseAdapter() {
        return new DatabaseAdapter(getDatabase());
    }

    public void configureMainActivity(MainActivity mainActivity) {
        mainActivity.useCase = new UseCase(createDatabaseAdapter());
    }

}
