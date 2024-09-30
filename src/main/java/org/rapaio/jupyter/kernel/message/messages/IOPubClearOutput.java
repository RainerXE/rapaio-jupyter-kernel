package org.rapaio.jupyter.kernel.message.messages;

import com.google.gson.annotations.SerializedName;
import org.rapaio.jupyter.kernel.message.ContentType;
import org.rapaio.jupyter.kernel.message.MessageType;

public record IOPubClearOutput(
        @SerializedName("wait") boolean shouldWait) implements ContentType<IOPubClearOutput> {

    @Override
    public MessageType<IOPubClearOutput> type() {
        return MessageType.IOPUB_CLEAR_OUTPUT;
    }
}
