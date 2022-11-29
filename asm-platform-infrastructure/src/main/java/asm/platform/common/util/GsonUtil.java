package asm.platform.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class GsonUtil {

    public static final Gson gson = new GsonBuilder().disableHtmlEscaping()
            .registerTypeAdapter(
                    new TypeToken<Map<String, Object>>() {
                    }.getType(),
                    new GsonMapTypeAdapter()
            ).create();

    /**
     * fromJson
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * fromJson
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * toJson
     *
     * @param src
     * @return
     */
    public static String toJson(Object src) {
        if (src == null) {
            return null;
        }
        return gson.toJson(src);
    }
}
