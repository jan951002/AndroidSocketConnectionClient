package com.example.sundevs.demowifip2pdirectfileclient.ui;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.sundevs.demowifip2pdirectfileclient.ClientContract;
import com.example.sundevs.demowifip2pdirectfileclient.R;
import com.example.sundevs.demowifip2pdirectfileclient.config.net.ConnectivityChange;
import com.example.sundevs.demowifip2pdirectfileclient.config.socket.ClientSocket;
import com.example.sundevs.demowifip2pdirectfileclient.domain.Book;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements ClientContract.MainView {

    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.txt_title_book)
    EditText bookTitle;
    @BindView(R.id.txt_author_book)
    EditText bookAuthor;
    @BindView(R.id.txt_category_book)
    EditText bookCategory;
    @BindView(R.id.txt_ip_address)
    EditText txtIpAddress;

    private ClientContract.FormBookPresenter client;
    private ClientContract.ConnectivityChange connectivityChange;

    public MainActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_book);
        ButterKnife.bind(this);
        connectivityChange = new ConnectivityChange();
        connectivityChange.attachView(this);
        IntentFilter netIntent = new IntentFilter();
        netIntent.addAction(WifiManager.EXTRA_NETWORK_INFO);
        netIntent.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        registerReceiver((BroadcastReceiver) connectivityChange, netIntent);
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        client = new ClientSocket(txtIpAddress.getText().toString());
        client.attachView(this);
        String title = bookTitle.getText().toString();
        String author = bookAuthor.getText().toString();
        String category = bookCategory.getText().toString();
        Book book = new Book();
        book.setAll(title, author, category);
        client.sendBook(book);
    }

    @Override
    public void changeEnabledButtonSend(boolean enabled) {
        btnSend.setEnabled(enabled);
    }
}
