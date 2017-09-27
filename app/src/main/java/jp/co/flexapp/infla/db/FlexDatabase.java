package jp.co.flexapp.infla.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import jp.co.flexapp.infla.db.dao.FbAccessTokenDao;
import jp.co.flexapp.infla.db.entity.FbAccessToken;

/**
 * Created by mitsuhori_y on 2017/09/27.
 */

@Database(entities = {FbAccessToken.class}, version = 1)
public abstract class FlexDatabase extends RoomDatabase {
    public abstract FbAccessTokenDao fbAccessTokenDao();
}
