package jp.co.flexapp.infla.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import io.reactivex.Single;

/**
 * Created by mitsuhori_y on 2017/09/27.
 */

@Dao
public interface FbAccessTokenDao {

    @Query("SELECT * FROM fbAccessToken LIMIT 1")
    Single<FbAccessToken> getFbToken();

    @Query("SELECT * FROM fbAccessToken WHERE userId LIKE :uid")
    FbAccessToken getFbAccessToken(String uid);

    @Insert
    void putFbToken(FbAccessToken token);

    @Update
    void updateFbToken(FbAccessToken token);

}
