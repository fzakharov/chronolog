package com.revents.chronolog.features;

import android.content.Intent;

public interface IntentExtractor<T>
{
    T extract(Intent data);
}
