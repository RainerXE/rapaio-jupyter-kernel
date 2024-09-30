package org.rapaio.jupyter.kernel.message.messages;

import com.google.gson.annotations.SerializedName;
import org.rapaio.jupyter.kernel.message.ContentType;
import org.rapaio.jupyter.kernel.message.MessageType;

public record ShellCompleteRequest(
        @SerializedName("code") String code,
        @SerializedName("cursor_pos") int cursorPos) implements ContentType<ShellCompleteRequest> {

    @Override
    public MessageType<ShellCompleteRequest> type() {
        return MessageType.SHELL_COMPLETE_REQUEST;
    }
}
