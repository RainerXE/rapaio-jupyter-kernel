package org.rapaio.jupyter.kernel.message.messages;

import com.google.gson.annotations.SerializedName;
import org.rapaio.jupyter.kernel.core.display.DisplayData;
import org.rapaio.jupyter.kernel.message.ContentType;
import org.rapaio.jupyter.kernel.message.MessageType;

public class IOPubExecuteResult extends DisplayData implements ContentType<IOPubExecuteResult> {

    @Override
    public MessageType<IOPubExecuteResult> type() {
        return MessageType.IOPUB_EXECUTE_RESULT;
    }

    @SerializedName("execution_count")
    private final int count;

    public IOPubExecuteResult(int count, DisplayData data) {
        super(data.data(), data.metadata(), data.transientData());
        this.count = count;
    }
}
