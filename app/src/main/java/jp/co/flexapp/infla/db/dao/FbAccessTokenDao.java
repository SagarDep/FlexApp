package jp.co.flexapp.infla.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import jp.co.flexapp.infla.db.entity.FbAccessToken;

/**
 * Created by mitsuhori_y on 2017/09/27.
 */

@Dao
public interface FbAccessTokenDao {

    @Query("SELECT * FROM fbAccessToken LIMIT 1")
    FbAccessToken getFbToken();

    @Insert
    void putFbToken(FbAccessToken token);

    @Update
    void updateFbToken(FbAccessToken token);

}
