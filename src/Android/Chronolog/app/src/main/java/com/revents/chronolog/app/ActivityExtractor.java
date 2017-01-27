package com.revents.chronolog.app;

public interface ActivityExtractor<TOut, TIn> {
    TOut extract(TIn activity);
}
