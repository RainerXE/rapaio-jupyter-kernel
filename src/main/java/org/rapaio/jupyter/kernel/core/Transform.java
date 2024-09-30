package org.rapaio.jupyter.kernel.core;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.rapaio.jupyter.kernel.message.ContentType;
import org.rapaio.jupyter.kernel.message.Header;
import org.rapaio.jupyter.kernel.message.MessageType;
import org.rapaio.jupyter.kernel.message.adapters.*;
import org.rapaio.jupyter.kernel.message.messages.IOPubStatus;
import org.rapaio.jupyter.kernel.message.messages.ShellHistoryRequest;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

public class Transform {

    public static final Charset ASCII = StandardCharsets.US_ASCII;
    public static final Charset UTF_8 = StandardCharsets.UTF_8;
    public static final byte[] IDENTITY_DELIMITER = "<IDS|MSG>".getBytes(ASCII);
    public static final JsonObject EMPTY_JSON_OBJ = new JsonObject();

    private static final byte[] EMPTY_JSON_BYTES = "{}".getBytes(UTF_8);
    private static final Type JSON_OBJ_AS_MAP = new TypeToken<Map<String, Object>>() {
    }.getType();


    private static final Gson replyGson = new GsonBuilder()
            .registerTypeAdapter(ExpressionValue.class, ExpressionValueAdapter.INSTANCE)
            .create();

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Header.class, HeaderAdapter.INSTANCE)
            .registerTypeAdapter(MessageType.class, MessageTypeAdapter.INSTANCE)
            .registerTypeAdapter(IOPubStatus.class, PublishStatusAdapter.INSTANCE)
            .registerTypeAdapter(ShellHistoryRequest.class, HistoryRequestAdapter.INSTANCE)
            .registerTypeHierarchyAdapter(ContentType.class, new ReplyTypeAdapter(replyGson))
            .create();

    public static boolean equalsIdentityDelimiter(byte[] raw) {
        return Arrays.equals(IDENTITY_DELIMITER, raw);
    }

    public static <T> T fromJson(String text, Class<T> clazz) {
        return fromJson(text.getBytes(UTF_8), clazz);
    }

    public static <T> T fromJson(byte[] raw, Class<T> clazz) {
        return gson.fromJson(new String(raw, UTF_8), clazz);
    }

    public static <T> T fromJsonNull(String text, Class<T> clazz) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        return fromJsonNull(text.getBytes(UTF_8), clazz);
    }

    public static <T> T fromJsonNull(byte[] raw, Class<T> clazz) {
        JsonElement parentHeaderJson = JsonParser.parseString(new String(raw, UTF_8));
        if (parentHeaderJson.isJsonObject() && !parentHeaderJson.getAsJsonObject().isEmpty()) {
            return gson.fromJson(parentHeaderJson, clazz);
        }
        return null;
    }

    public static Map<String, Object> fromJsonMap(byte[] raw) {
        return gson.fromJson(new String(raw, UTF_8), JSON_OBJ_AS_MAP);
    }

    public static String toJson(Object object) {
        if (object == null) {
            return "{}";
        }
        return gson.toJson(object);
    }

    public static byte[] toJsonBytes(Object object) {
        if (object == null) {
            return EMPTY_JSON_BYTES;
        }
        return gson.toJson(object).getBytes(UTF_8);
    }
}
