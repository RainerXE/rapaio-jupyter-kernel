package org.rapaio.jupyter.kernel.install;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record KernelJson(
        @SerializedName("argv") String[] argv,
        @SerializedName("display_name") String displayName,
        @SerializedName("language") String language,
        @SerializedName("interrupt_mode") String interruptMode,
        @SerializedName("env") Map<String, String> env) {
}
