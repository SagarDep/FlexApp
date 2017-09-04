package jp.co.flexapp.infla.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by mitsuhori_y on 2017/08/30.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tweet {
    int id;
    int thumbNailId;
    String username;
    String tweet;
}
