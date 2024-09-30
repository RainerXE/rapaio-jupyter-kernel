package org.rapaio.jupyter.kernel.message.messages;

import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import org.rapaio.jupyter.kernel.message.ContentType;
import org.rapaio.jupyter.kernel.message.MessageType;
import org.rapaio.jupyter.kernel.message.adapters.IdentityJsonElementAdapter;

public record CustomCommClose(
        @SerializedName("comm_id") String commId,
        @JsonAdapter(IdentityJsonElementAdapter.class) JsonObject data) implements ContentType<CustomCommClose> {

    @Override
    public MessageType<CustomCommClose> type() {
        return MessageType.CUSTOM_COMM_CLOSE;
    }
}
