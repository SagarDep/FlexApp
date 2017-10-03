package jp.co.flexapp.common.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import jp.co.flexapp.infla.entity.FbMsg;

/**
 * Created by mitsuhori_y on 2017/10/03.
 */

public class FbJsonGet {

    public static ArrayList<FbMsg> toFbMsg(String json) {
        if (json.length() > 0) {
            return parser(json);
        }
        return null;
    }

    private static ArrayList<FbMsg> parser(String json) {
        ArrayList<FbMsg> list = new ArrayList<>();

        JsonObject jsonObject = (JsonObject) new Gson().fromJson(json, JsonObject.class);

        JsonArray jsonArr = jsonObject.get("data").getAsJsonArray();
        String arr = jsonArr.get(0).toString();

        JsonObject obj;
        String message, createdTime, id;
        
        for (int i = 0; i < jsonArr.size(); i++) {
            obj = jsonArr.get(i).getAsJsonObject();
            if (obj.get("story") != null) {
                continue;
            }
            message = obj.get("message").getAsString();
            createdTime = obj.get("created_time").getAsString();
            id = obj.get("id").getAsString();
            list.add(new FbMsg(id, message, createdTime));
        }
        return list;
    }
}
