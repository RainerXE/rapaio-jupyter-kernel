package org.rapaio.jupyter.kernel.message.adapters;

import com.google.gson.*;
import org.rapaio.jupyter.kernel.message.MessageType;

import java.lang.reflect.Type;

public class MessageTypeAdapter implements JsonSerializer<MessageType<?>>, JsonDeserializer<MessageType<?>> {
    public static final MessageTypeAdapter INSTANCE = new MessageTypeAdapter();

    private MessageTypeAdapter() { }

    @Override
    public MessageType<?> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        return MessageType.getType(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(MessageType<?> messageType, Type type, JsonSerializationContext ctx) {
        return new JsonPrimitive(messageType.name());
    }
}
