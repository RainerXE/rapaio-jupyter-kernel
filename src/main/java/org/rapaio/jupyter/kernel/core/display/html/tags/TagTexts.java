package org.rapaio.jupyter.kernel.core.display.html.tags;

import org.rapaio.jupyter.kernel.core.display.html.HtmlStyle;
import org.rapaio.jupyter.kernel.core.display.html.Tag;

public class TagTexts extends Tag {

    private final String[] args;

    public TagTexts(String... args) {
        this.args = args;
    }

    @Override
    public String render(HtmlStyle style) {
        return String.join("", args);
    }
}
