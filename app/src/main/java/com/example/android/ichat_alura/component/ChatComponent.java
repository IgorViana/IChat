package com.example.android.ichat_alura.component;

import com.example.android.ichat_alura.activity.MainActivity;
import com.example.android.ichat_alura.adapter.MensagensAdapter;
import com.example.android.ichat_alura.module.ChatModule;

import dagger.Component;

@Component(modules = ChatModule.class)
public interface ChatComponent {
    void inject(MainActivity activity);

    void inject(MensagensAdapter adapter);
}
