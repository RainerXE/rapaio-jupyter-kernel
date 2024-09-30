package org.rapaio.jupyter.kernel.channels;

import org.rapaio.jupyter.kernel.core.ConnectionProperties;
import org.rapaio.jupyter.kernel.message.HMACDigest;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;

import java.util.logging.Logger;

public final class IOPubChannel extends AbstractChannel {

    private static final Logger LOGGER = Logger.getLogger(IOPubChannel.class.getSimpleName());

    public IOPubChannel(Channels channels, ZMQ.Context context, HMACDigest hmacGenerator) {
        super(channels, "IOPubChannel", context, SocketType.PUB, hmacGenerator);
    }

    @Override
    public void bind(ConnectionProperties connProps) {
        String addr = connProps.formatAddress(connProps.iopubPort());
        LOGGER.info(logPrefix + "Binding iopub to " + addr);
        socket.bind(addr);
    }
}
