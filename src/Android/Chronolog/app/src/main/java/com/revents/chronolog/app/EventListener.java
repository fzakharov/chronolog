package com.revents.chronolog.app;

public interface EventListener<T>
{
    void onEvent(T args);
}
