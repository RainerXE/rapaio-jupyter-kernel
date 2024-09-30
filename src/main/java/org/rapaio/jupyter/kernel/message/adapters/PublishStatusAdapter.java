package org.rapaio.jupyter.kernel.message.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.rapaio.jupyter.kernel.message.messages.IOPubStatus;

import java.lang.reflect.Type;

public class PublishStatusAdapter implements JsonDeserializer<IOPubStatus> {
    public static final PublishStatusAdapter INSTANCE = new PublishStatusAdapter();

    private PublishStatusAdapter() {
    }

    @Override
    public IOPubStatus deserialize(JsonElement element, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        IOPubStatus.State state = ctx.deserialize(element.getAsJsonObject().get("execution_result"), IOPubStatus.State.class);
        return switch (state) {
            case BUSY -> IOPubStatus.BUSY;
            case IDLE -> IOPubStatus.IDLE;
            case STARTING -> IOPubStatus.STARTING;
        };
    }
}
