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
            objectInput = new ObjectInputStream(client.getInputStream());
            outWriter = new PrintWriter(client.getOutputStream());
            client.setSendBufferSize(TCP_BUFFER_SIZE);
            client.setReceiveBufferSize(TCP_BUFFER_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendBook(Book book) {
        init();
        try {
            String jsonSaleOrder = "{\"updatedAt\":\"Jan 15, 2018 12:00:18 PM\",\"business\":\"59c31779bd71c75080a51f2b\",\"cashier\":\"59ee36859a7ff63110c81076\",\"createdAt\":\"Jan 15, 2018 12:00:18 PM\",\"localId\":\"3156ea98-3c53-420f-8f02-ba2291ab24bb\",\"payments\":[],\"recordStatus\":\"created\",\"saleOrderLines\":[{\"_id\":\"6a3da19c-75f9-4f09-b933-040c8a3d7351\",\"createdAt\":\"Jan 15, 2018 12:00:18 PM\",\"discounts\":[],\"modifiers\":[],\"notes\":\"\",\"price\":\"2500.0\",\"productName\":\"cafe moka g\",\"productVariant\":{\"_id\":\"59ebca5f221aa405c3c4cf88\",\"createdAt\":\"Oct 21, 2017 5:29:51 PM\",\"hasA\":\"recipe\",\"ingredients\":[{\"_id\":\"5a254ce7abd15f324b418ffc\",\"productVariant\":{\"_id\":\"59fcd9e39050b8202ad6c3b4\",\"createdAt\":\"Nov 3, 2017 4:04:35 PM\",\"ingredients\":[],\"updatedAt\":\"Jan 12, 2018 5:47:45 PM\",\"unitMeasure\":\"Gramo (g)\",\"recordStatus\":\"active\",\"productStocks\":[],\"quantity\":449.99,\"isTrackingInventory\":true,\"isTrackingCost\":false,\"isTrackingAlert\":false,\"isShowInStore\":false},\"quantity\":0.01},{\"_id\":\"5a254ce7abd15f324b418ffb\",\"productVariant\":{\"_id\":\"59edfa3312759a0ee8335307\",\"avgCost\":2000.0,\"createdAt\":\"Oct 23, 2017 9:18:27 AM\",\"ingredients\":[],\"updatedAt\":\"Jan 12, 2018 5:47:45 PM\",\"unitMeasure\":\"Gramo (g)\",\"recordStatus\":\"active\",\"minThreshold\":100.5,\"productStocks\":[],\"quantity\":498.0,\"isTrackingInventory\":true,\"isTrackingCost\":true,\"isTrackingAlert\":true,\"isShowInStore\":false},\"quantity\":2.0}],\"updatedAt\":\"Dec 4, 2017 8:25:59 AM\",\"sku\":\"\",\"recordStatus\":\"active\",\"minThreshold\":0.0,\"name\":\"capuchino\\n\",\"price\":2500.0,\"productStocks\":[],\"isTrackingInventory\":false,\"isTrackingCost\":false,\"isTrackingAlert\":false,\"isShowInStore\":true},\"updatedAt\":\"Jan 15, 2018 12:00:18 PM\",\"taxes\":[],\"quantity\":1},{\"_id\":\"8e050330-f754-494f-b4c1-26c8cf9f3b09\",\"createdAt\":\"Jan 15, 2018 12:01:16 PM\",\"discounts\":[],\"modifiers\":[],\"notes\":\"\",\"price\":\"555.0\",\"productName\":\"base tablet\",\"productVariant\":{\"_id\":\"59fc89eced03fb6d93b93825\",\"avgCost\":9000.0,\"createdAt\":\"Nov 3, 2017 10:23:24 AM\",\"ingredients\":[],\"updatedAt\":\"Dec 13, 2017 2:58:15 PM\",\"sku\":\"\",\"recordStatus\":\"active\",\"minThreshold\":5.0,\"name\":\"7\\u0027\",\"price\":555.0,\"productStocks\":[],\"quantity\":33.0,\"isTrackingInventory\":true,\"isTrackingCost\":true,\"isTrackingAlert\":true,\"isShowInStore\":true},\"updatedAt\":\"Jan 15, 2018 12:01:16 PM\",\"taxes\":[],\"quantity\":1}],\"saleOrderRefundsLines\":[],\"tag\":\"General Z/5\",\"total\":0.0,\"totalDiscount\":0.0,\"totalGratuity\":0.0,\"totalTax\":0.0,\"transaction\":false,\"attempts\":0}";
            outWriter.println(jsonSaleOrder);
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
