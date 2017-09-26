package jp.co.flexapp.common.enums;

/**
 * Created by mitsuhori_y on 2017/09/26.
 */

public enum TabType {

    //Twitter
    TWITTER(0),

    //Facebook
    FACEBOOK(1),

    //Instagram
    INSTAGRAM(2);

    private final Integer value;

    TabType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
