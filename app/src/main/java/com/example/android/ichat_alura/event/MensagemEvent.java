package com.example.android.ichat_alura.event;

import com.example.android.ichat_alura.model.Mensagem;

public class MensagemEvent {
    public final Mensagem mensagem;
    public MensagemEvent(Mensagem mensagem) {
     this.mensagem = mensagem;
    }
}
