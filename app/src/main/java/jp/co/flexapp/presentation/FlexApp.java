package jp.co.flexapp.presentation;

import android.app.Application;
import android.arch.persistence.room.Room;

import jp.co.flexapp.infla.db.FlexDatabase;

/**
 * Created by mitsuhori_y on 2017/09/27.
 */

public class FlexApp extends Application {

    public static FlexApp INSTANCE;

    private FlexDatabase database;

    private final String DATABASE_NAME = "FlexDB";

    public static FlexApp get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(), FlexDatabase.class, DATABASE_NAME)
                .build();

        INSTANCE = this;
    }

    public FlexDatabase getDB() {
        return database;
    }

}
