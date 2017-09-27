package jp.co.flexapp.infla.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by mitsuhori_y on 2017/09/27.
 */

@Entity
@AllArgsConstructor
public class FbAccessToken {
    @Getter
    @PrimaryKey
    private String accessToken;

    @Getter
    @ColumnInfo(name = "userId")
    private String userId;


}
