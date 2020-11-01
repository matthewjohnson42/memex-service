package com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.wrappers;

import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.RawTextES;

public class RawTextESHit {

    private RawTextES _source;

    public RawTextESHit() { }

    public void set_source(RawTextES _source) {
        this._source = _source;
    }
    public RawTextES get_source() {
        return _source;
    }

}
