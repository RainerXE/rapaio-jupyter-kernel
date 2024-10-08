package org.rapaio.jupyter.kernel.message.messages;

import com.google.gson.annotations.SerializedName;
import org.rapaio.jupyter.kernel.core.CompleteMatches;
import org.rapaio.jupyter.kernel.message.ContentType;
import org.rapaio.jupyter.kernel.message.MessageType;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public record ShellCompleteReply(
        @SerializedName("status") String status,
        @SerializedName("matches") List<String> matches,
        @SerializedName("cursor_start") int cursorStart,
        @SerializedName("cursor_end") int cursorEnd,
        @SerializedName("metadata") Map<String, Object> metadata) implements ContentType<ShellCompleteReply> {

    public static ShellCompleteReply empty(int pos) {
        return new ShellCompleteReply(STATUS, Collections.emptyList(), pos, pos, Collections.emptyMap());
    }

    public static ShellCompleteReply from(CompleteMatches matches) {
        return new ShellCompleteReply(STATUS, matches.replacements(), matches.start(), matches.end(), Collections.emptyMap());
    }

    private static final String STATUS = "ok";

    @Override
    public MessageType<ShellCompleteReply> type() {
        return MessageType.SHELL_COMPLETE_REPLY;
    }

    public ShellCompleteReply {
        if (!STATUS.equals(status)) {
            throw new IllegalArgumentException();
        }
    }
}
