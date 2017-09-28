package jp.co.flexapp.presentation;

import android.app.Application;

/**
 * Created by mitsuhori_y on 2017/09/27.
 */

public class FlexApp extends Application {

    //private FlexDatabase database;

    private final String DATABASE_NAME = "FlexDB";

    @Override
    public void onCreate() {
        super.onCreate();
        //database = Room.databaseBuilder(getApplicationContext(), FlexDatabase.class, DATABASE_NAME)
        //.build();
    }

//    public FlexDatabase getDB() {
//        return database;
//    }

}
