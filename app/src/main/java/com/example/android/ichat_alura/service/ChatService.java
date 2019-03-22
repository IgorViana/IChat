package com.example.android.ichat_alura.service;

import com.example.android.ichat_alura.model.Mensagem;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ChatService {

    @POST("polling")
    Call<Void> enviar(@Body Mensagem mensagem);

    @GET("polling")
    Call<Mensagem> ouvirMensagens();

    /**
     * final String texto = mensagem.getTexto();
     new Thread(new Runnable() {
    @Override public void run() {
    try {
    HttpURLConnection urlConnection = (HttpURLConnection) new URL("http://192.168.0.8:8080/polling").openConnection();
    urlConnection.setRequestMethod("POST");
    urlConnection.setRequestProperty("content-type", "application/json");

    JSONStringer json = new JSONStringer()
    .object()
    .key("text").value(texto)
    .key("id").value(mensagem.getId())
    .endObject();

    OutputStream outputStream = urlConnection.getOutputStream();
    PrintStream ps = new PrintStream(outputStream);

    ps.println(json.toString());

    urlConnection.connect();
    urlConnection.getInputStream();

    } catch (Exception e) {
    throw new RuntimeException(e);
    }

    }
    }).start(); */


    /**new Thread(new Runnable() {
    @Override public void run() {
    try {
    HttpURLConnection urlConnection = (HttpURLConnection) new URL("http://192.168.0.8:8080/polling").openConnection();
    urlConnection.setRequestMethod("GET");
    urlConnection.setRequestProperty("Accept", "application/json");

    urlConnection.connect();

    Scanner scanner = new Scanner(urlConnection.getInputStream());
    StringBuilder builder = new StringBuilder();

    while(scanner.hasNextLine()) {
    builder.append(scanner.nextLine());
    }

    String json = builder.toString();

    JSONObject jsonObject = new JSONObject(json);
    final Mensagem mensagem = new Mensagem(jsonObject.optInt("id"), jsonObject.getString("text"));

    activity.runOnUiThread(new Runnable() {
    @Override public void run() {
    activity.adicionaALista(mensagem);
    }
    });
    } catch (Exception e) {
    throw new RuntimeException(e);
    }
    }
    }).start(); */
}