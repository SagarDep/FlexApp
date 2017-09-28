package jp.co.flexapp.infla.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by mitsuhori_y on 2017/09/27.
 */

@Database(entities = {FbAccessToken.class}, version = 3)
public abstract class FlexDatabase extends RoomDatabase {

    public abstract FbAccessTokenDao fbAccessTokenDao();

    //   private static final Object sLock = new Object();

//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//        }
//    };

//    public static FlexDatabase getInstance(Context context) {
//        synchronized (sLock) {
//            if (INSTANCE == null) {
//                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                        FlexDatabase.class, "sample.db").build();
//            }
//            return INSTANCE;
//        }
//    }
}
