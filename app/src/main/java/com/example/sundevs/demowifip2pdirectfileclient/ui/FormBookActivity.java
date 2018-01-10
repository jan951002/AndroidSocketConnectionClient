package com.example.sundevs.demowifip2pdirectfileclient.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sundevs.demowifip2pdirectfileclient.ClientContract;
import com.example.sundevs.demowifip2pdirectfileclient.R;
import com.example.sundevs.demowifip2pdirectfileclient.config.socket.ClientSocket;
import com.example.sundevs.demowifip2pdirectfileclient.domain.Book;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormBookActivity extends BaseActivity implements ClientContract.FormBookView {

    @BindView(R.id.txt_title_book)
    EditText bookTitle;
    @BindView(R.id.txt_author_book)
    EditText bookAuthor;
    @BindView(R.id.txt_category_book)
    EditText bookCategory;
    @BindView(R.id.txt_ip_address)
    EditText txtIpAddress;

    private ClientContract.FormBookPresenter client;

    public FormBookActivity(){
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_book);
        ButterKnife.bind(this);

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
}
