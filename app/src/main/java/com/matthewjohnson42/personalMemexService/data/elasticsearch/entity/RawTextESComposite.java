package com.matthewjohnson42.personalMemexService.data.elasticsearch.entity;

import java.util.List;

public class RawTextESComposite extends RawTextES {

    private List<String> highlights;

    public RawTextESComposite() { }

    public RawTextESComposite(RawTextESComposite rawTextESComposite) {
        super(rawTextESComposite);
        this.highlights = rawTextESComposite.getHighlights();
    }

    public RawTextESComposite(RawTextES rawTextES) {
        super(rawTextES);
    }

    public List<String> getHighlights() {
        return highlights;
    }
    public RawTextESComposite setHighlights(List<String> highlights) {
        this.highlights = highlights;
        return this;
    }
}