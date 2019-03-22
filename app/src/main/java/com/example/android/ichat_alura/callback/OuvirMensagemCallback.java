package com.example.android.ichat_alura.callback;

import android.content.Context;
import android.content.Intent;

import com.example.android.ichat_alura.activity.MainActivity;
import com.example.android.ichat_alura.event.FailureEvent;
import com.example.android.ichat_alura.event.MensagemEvent;
import com.example.android.ichat_alura.model.Mensagem;

import org.greenrobot.eventbus.EventBus;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagemCallback implements Callback<Mensagem> {

    private final Context context;
    private final EventBus eventBus;

    public OuvirMensagemCallback(Context context, EventBus eventBus) {

        this.context = context ;
        this.eventBus = eventBus;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
       if (response.isSuccessful()) {
            Mensagem mensagem = response.body();
            eventBus.post(new MensagemEvent(mensagem));
       }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
        eventBus.post(new FailureEvent());
    }
}
