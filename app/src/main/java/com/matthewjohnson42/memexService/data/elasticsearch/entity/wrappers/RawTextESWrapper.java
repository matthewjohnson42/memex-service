package com.matthewjohnson42.memexService.data.elasticsearch.entity.wrappers;

public class RawTextESWrapper {

    private RawTextESHits hits;

    public RawTextESWrapper() { }

    public void setHits(RawTextESHits hits) {
        this.hits = hits;
    }
    public RawTextESHits getHits() {
        return hits;
    }

}
