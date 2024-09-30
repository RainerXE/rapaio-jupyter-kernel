package org.rapaio.jupyter.kernel.message.messages;

import com.google.gson.annotations.SerializedName;
import org.rapaio.jupyter.kernel.message.ContentType;
import org.rapaio.jupyter.kernel.message.MessageType;

public record ShellInspectRequest(
        // The code that the request wants inspected
        String code,
        // The character index within the code in which the cursor is at. This allows for an inspection
        @SerializedName("cursor_pos") int cursorPos,
        // Either 0 or 1. 0 is the default and in IPython level 1 includes the source in the inspection.
        @SerializedName("detail_level")
        int detailLevel
) implements ContentType<ShellInspectRequest> {

    @Override
    public MessageType<ShellInspectRequest> type() {
        return MessageType.SHELL_INSPECT_REQUEST;
    }
}
