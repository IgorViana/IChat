package com.example.android.ichat_alura.app;

import android.app.Application;

import com.example.android.ichat_alura.component.ChatComponent;
import com.example.android.ichat_alura.component.DaggerChatComponent;
import com.example.android.ichat_alura.module.ChatModule;

public class ChatApplication extends Application {
    private ChatComponent component;

    public void onCreate() {
        super.onCreate();
        component = DaggerChatComponent.builder()
                .chatModule(new ChatModule(this))
                .build();
    }

    public ChatComponent getComponent(){
        return component;
    }
}
