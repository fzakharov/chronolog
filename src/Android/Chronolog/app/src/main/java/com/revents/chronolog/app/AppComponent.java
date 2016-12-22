package com.revents.chronolog.app;

import com.revents.chronolog.features.factsfeed.FactsfeedActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject(FactsfeedActivity ffa);
}
