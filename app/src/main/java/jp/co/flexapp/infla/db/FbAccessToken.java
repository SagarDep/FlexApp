package jp.co.flexapp.infla.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by mitsuhori_y on 2017/09/27.
 */

@Entity(tableName = "fbAccessToken")
public class FbAccessToken {

    @Getter
    @Setter
    @PrimaryKey
    @ColumnInfo(name = "accessToken")
    private String accessToken;

    @Getter
    @Setter
    @ColumnInfo(name = "userId")
    private String userId;
    
    public FbAccessToken(String accessToken, String userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }

}
