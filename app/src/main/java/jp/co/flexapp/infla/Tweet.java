package jp.co.flexapp.infla;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mitsuhori_y on 2017/08/30.
 */
@AllArgsConstructor
@Getter
@Setter
public class Tweet {
    int thumbNailId;
    String username;
    String tweet;
}
