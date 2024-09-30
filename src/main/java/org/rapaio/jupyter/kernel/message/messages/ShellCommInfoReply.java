package org.rapaio.jupyter.kernel.message.messages;

import com.google.gson.annotations.SerializedName;
import org.rapaio.jupyter.kernel.message.ContentType;
import org.rapaio.jupyter.kernel.message.MessageType;

import java.util.Map;

public record ShellCommInfoReply(Map<String, CommInfo> comms) implements ContentType<ShellCommInfoReply> {

    @Override
    public MessageType<ShellCommInfoReply> type() {
        return MessageType.SHELL_COMM_INFO_REPLY;
    }

    public record CommInfo(@SerializedName("target_name") String targetName) {
    }
}
