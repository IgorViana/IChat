package com.example.android.ichat_alura.adapter;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.ichat_alura.R;
import com.example.android.ichat_alura.model.Mensagem;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class MensagensAdapter extends RecyclerView.Adapter<MensagensAdapter.MyViewHolder> {
    private List<Mensagem> mensagens;
    private Context context;
    private int idDoCliente;

    @Inject
    Picasso picasso;

    public MensagensAdapter(int idDoCliente, List<Mensagem> mensagens, Context context) {
        this.idDoCliente = idDoCliente;
        this.mensagens = mensagens;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mensagem, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        Mensagem mensagem = mensagens.get(i);
        if(mensagem.getId() != idDoCliente) {
            viewHolder.itemView.setBackgroundColor(Color.CYAN);
        }
        viewHolder.textoMensagem.setText(mensagem.getTexto());
        picasso.with(context).load("https://api.adorable.io/avatars/285/"+ mensagem.getId() +".png").into(viewHolder.avatarMensagem);
    }

    @Override
    public int getItemCount() {
        return mensagens.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textoMensagem)
        public TextView textoMensagem;
        @BindView(R.id.item_imagemAvatar)
        public ImageView avatarMensagem;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
