package org.rapaio.jupyter.kernel.message.messages;

import com.google.gson.annotations.SerializedName;
import org.rapaio.jupyter.kernel.message.ContentType;
import org.rapaio.jupyter.kernel.message.MessageType;

public record ShellCommInfoRequest(
        @SerializedName("target_name") String targetName) implements ContentType<ShellCommInfoRequest> {

    @Override
    public MessageType<ShellCommInfoRequest> type() {
        return MessageType.SHELL_COMM_INFO_REQUEST;
    }
}
