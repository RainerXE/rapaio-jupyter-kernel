package org.rapaio.jupyter.kernel.message.messages;

import com.google.gson.annotations.SerializedName;
import org.rapaio.jupyter.kernel.message.ContentType;
import org.rapaio.jupyter.kernel.message.MessageType;

public record IOPubStream(
        StreamName name,
        String text) implements ContentType<IOPubStream> {

    @Override
    public MessageType<IOPubStream> type() {
        return MessageType.IOPUB_STREAM;
    }

    public enum StreamName {
        @SerializedName("stdout") OUT,
        @SerializedName("stderr") ERR
    }
}
