package org.rapaio.jupyter.kernel.message.adapters;

import com.google.gson.*;
import org.rapaio.jupyter.kernel.message.messages.ShellHistoryRequest;

import java.lang.reflect.Type;

public class HistoryRequestAdapter implements JsonDeserializer<ShellHistoryRequest> {
    public static final HistoryRequestAdapter INSTANCE = new HistoryRequestAdapter();

    private HistoryRequestAdapter() { }

    @Override
    public ShellHistoryRequest deserialize(JsonElement element, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        JsonObject object = element.getAsJsonObject();
        JsonPrimitive accessTypeRaw = object.getAsJsonPrimitive("hist_access_type");

        ShellHistoryRequest.AccessType accessType = ctx.deserialize(accessTypeRaw, ShellHistoryRequest.AccessType.class);
        return switch (accessType) {
            case RANGE -> ctx.deserialize(element, ShellHistoryRequest.Range.class);
            case TAIL -> ctx.deserialize(element, ShellHistoryRequest.Tail.class);
            case SEARCH -> ctx.deserialize(element, ShellHistoryRequest.Search.class);
        };
    }
}
