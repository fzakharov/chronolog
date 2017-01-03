package com.revents.chronolog.app;

import com.revents.chronolog.features.EditFactTypeActivity;
import com.revents.chronolog.features.FactTypesActivity;
import com.revents.chronolog.features.FactsfeedActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject(FactsfeedActivity ffa);

    void inject(FactTypesActivity fta);

    void inject(EditFactTypeActivity efta);
}
