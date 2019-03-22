package com.example.android.ichat_alura.callback;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;

public class EnviarMensagemCallback implements retrofit2.Callback<Void> {
    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {
        Log.d("FALHA", "onFailure: " + t.getMessage());
    }
}
