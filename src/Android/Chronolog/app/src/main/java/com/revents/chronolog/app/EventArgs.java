package com.revents.chronolog.app;

public class EventArgs<T> {
    T mData;

    public EventArgs(T data) {
        mData = data;
    }

    public T getData() {
        return mData;
    }
}

