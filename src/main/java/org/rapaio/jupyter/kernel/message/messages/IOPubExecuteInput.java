package org.rapaio.jupyter.kernel.message.messages;

import com.google.gson.annotations.SerializedName;
import org.rapaio.jupyter.kernel.message.ContentType;
import org.rapaio.jupyter.kernel.message.MessageType;

public record IOPubExecuteInput(
        @SerializedName("code") String code,
        @SerializedName("execution_count") int count) implements ContentType<IOPubExecuteInput> {

    @Override
    public MessageType<IOPubExecuteInput> type() {
        return MessageType.IOPUB_EXECUTE_INPUT;
    }
}
