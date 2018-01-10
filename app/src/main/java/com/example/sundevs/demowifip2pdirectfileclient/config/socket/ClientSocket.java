package com.example.sundevs.demowifip2pdirectfileclient.config.socket;

import android.os.StrictMode;

import com.example.sundevs.demowifip2pdirectfileclient.ClientContract;
import com.example.sundevs.demowifip2pdirectfileclient.domain.Book;
import com.example.sundevs.demowifip2pdirectfileclient.ui.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by SunDevs on 7/01/2018.
 */

public class ClientSocket implements ClientContract.FormBookPresenter {

    private final static int PORT = 2027;
    public final static int TCP_BUFFER_SIZE = 1024 * 1024;

    private Socket client;
    private PrintWriter outWriter;
    private ObjectInputStream objectInput;

    private String host;

    private ClientContract.MainView view;

    public ClientSocket(String host) {
        this.host = host;
    }

    public void init() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            client = new Socket(host, PORT);
            client.setSendBufferSize(TCP_BUFFER_SIZE);
            client.setReceiveBufferSize(TCP_BUFFER_SIZE);
            objectInput = new ObjectInputStream(client.getInputStream());
            outWriter = new PrintWriter(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendBook(Book book) {
        init();
        try {
            outWriter.println(book.serializeToJson());
            outWriter.flush();
            String result = (String) objectInput.readObject();
            System.out.println(result);
            view.showMessage(result, MessageType.SUCCESS);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            view.showMessage(e.getMessage(), MessageType.ERROR);
        }
    }

    @Override
    public void attachView(ClientContract.MainView view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
