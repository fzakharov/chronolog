package com.revents.chronolog.app;

import com.revents.chronolog.features.feed.EditFactActivity;
import com.revents.chronolog.features.type.EditFactTypeActivity;
import com.revents.chronolog.features.group.EditFactTypeGroupActivity;
import com.revents.chronolog.features.group.FactTypeGroupsActivity;
import com.revents.chronolog.features.type.FactTypesActivity;
import com.revents.chronolog.features.feed.FactsfeedActivity;
import com.revents.chronolog.features.value.ValueDescriptorsActivity;
import com.revents.chronolog.features.value.ValueTypesActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject(FactsfeedActivity activity);

    void inject(FactTypesActivity activity);

    void inject(EditFactTypeActivity activity);

    void inject(FactTypeGroupsActivity activity);

    void inject(EditFactTypeGroupActivity activity);

    void inject(ValueDescriptorsActivity activity);

    void inject(ValueTypesActivity activity);

    void inject(EditFactActivity activity);
}
