package com.example.android.ichat_alura.module;

import android.app.Application;
import android.view.inputmethod.InputMethodManager;

import com.example.android.ichat_alura.service.ChatService;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.INPUT_METHOD_SERVICE;

@Module
public class ChatModule {

    private Application app;

    public ChatModule(Application app) {
        this.app = app;
    }

    @Provides
    public ChatService getChatService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+ "ip" +":8080/") //TODO CHANGE IP
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatService chatService = retrofit.create(ChatService.class);
        return chatService;
    }

    @Provides
    public Picasso getPicasso() {
        Picasso picasso = new Picasso.Builder(app).build();
        return picasso;
    }
    
    @Provides
    public EventBus getEventBus(){
        return EventBus.builder().build();
    }

    @Provides
    public InputMethodManager getInputMethodManager(){
        return (InputMethodManager) app.getSystemService(INPUT_METHOD_SERVICE);
    }
}
