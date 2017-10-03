package jp.co.flexapp.infla.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by mitsuhori_y on 2017/10/03.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FbMsg {
    String id;
    String message;
    String createdAt;
}
