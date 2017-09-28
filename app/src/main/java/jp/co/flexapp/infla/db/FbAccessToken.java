package jp.co.flexapp.infla.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mitsuhori_y on 2017/09/27.
 */

@Entity(tableName = "fbAccessToken")
public class FbAccessToken {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "accessToken")
    private String accessToken;

    @ColumnInfo(name = "userId")
    private String userId;

    public FbAccessToken(String accessToken, String userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
