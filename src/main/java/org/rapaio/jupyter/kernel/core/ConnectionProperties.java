package org.rapaio.jupyter.kernel.core;

import com.google.gson.annotations.SerializedName;
import org.rapaio.jupyter.kernel.message.HMACDigest;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public record ConnectionProperties(
        @SerializedName("control_port") int controlPort,
        @SerializedName("shell_port") int shellPort,
        @SerializedName("transport") String transport,
        @SerializedName("signature_scheme") String signatureScheme,
        @SerializedName("stdin_port") int stdinPort,
        @SerializedName("hb_port") int hbPort,
        @SerializedName("ip") String ip,
        @SerializedName("iopub_port") int iopubPort,
        @SerializedName("key") String key) {

    public HMACDigest createHMACDigest() throws InvalidKeyException, NoSuchAlgorithmException {
        if (key == null || key.isEmpty()) {
            return HMACDigest.NO_AUTH_INSTANCE;
        }
        return new HMACDigest(signatureScheme, key);
    }

    public String formatAddress(int port) {
        return transport + "://" + ip + ":" + port;
    }
}
