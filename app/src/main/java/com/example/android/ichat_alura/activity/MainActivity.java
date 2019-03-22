package com.example.android.ichat_alura.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.EventLog;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.android.ichat_alura.app.ChatApplication;
import com.example.android.ichat_alura.R;
import com.example.android.ichat_alura.adapter.MensagensAdapter;
import com.example.android.ichat_alura.callback.EnviarMensagemCallback;
import com.example.android.ichat_alura.callback.OuvirMensagemCallback;
import com.example.android.ichat_alura.component.ChatComponent;
import com.example.android.ichat_alura.event.FailureEvent;
import com.example.android.ichat_alura.event.MensagemEvent;
import com.example.android.ichat_alura.model.Mensagem;
import com.example.android.ichat_alura.service.ChatService;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private final int idDoCliente = 1;
    private List<Mensagem> listaMensagem;

    @BindView(R.id.textoEnviar)
    public EditText editTextMensagem;
    @BindView(R.id.botaoEnviar)
    public Button buttonEnviar;
    @BindView(R.id.listaMensagens)
    public RecyclerView rcVListaMensagem;
    @BindView(R.id.imagem_perfil)
    public ImageView imagemAvatar;

    @Inject
    ChatService chatService;
    @Inject
    Picasso picasso;
    @Inject
    EventBus eventBus;
    @Inject
    InputMethodManager inputMethodManager;


    private ChatComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        picasso.with(this).load("https://api.adorable.io/avatars/285/" + idDoCliente + ".png").into(imagemAvatar);

        if (savedInstanceState != null) {
            listaMensagem = (List<Mensagem>) savedInstanceState.getSerializable("mensagens");
        } else {
            listaMensagem = new ArrayList<>();
        }

        MensagensAdapter adapter = new MensagensAdapter(idDoCliente, listaMensagem, this);
        rcVListaMensagem.setLayoutManager(new LinearLayoutManager(this));
        rcVListaMensagem.setAdapter(adapter);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponent();
        component.inject(this);
        ouvirMensagem(null);
        eventBus.register(this);
    }

    @OnClick(R.id.botaoEnviar)
    public void enviarMensagem() {
        chatService.enviar(new Mensagem(idDoCliente, editTextMensagem.getText().toString())).enqueue(new EnviarMensagemCallback());
        editTextMensagem.getText().clear();
        inputMethodManager.hideSoftInputFromWindow(editTextMensagem.getWindowToken(), 0);
    }

    @Subscribe
    public void adicionaALista(MensagemEvent mensagemEvent) {
        listaMensagem.add(mensagemEvent.mensagem);
        MensagensAdapter adapter = new MensagensAdapter(idDoCliente, listaMensagem, this);
        rcVListaMensagem.setAdapter(adapter);
    }

    @Subscribe
    public void ouvirMensagem(MensagemEvent mensagemEvent) {
        Call<Mensagem> call = chatService.ouvirMensagens();
        call.enqueue(new OuvirMensagemCallback(this, eventBus));
    }

    @Subscribe
    public void lidarCom(FailureEvent event) {
        ouvirMensagem(null);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("mensagens", (ArrayList<Mensagem>) listaMensagem);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }
}
