package com.example.sundevs.demowifip2pdirectfileclient;

import com.example.sundevs.demowifip2pdirectfileclient.domain.Book;
import com.example.sundevs.demowifip2pdirectfileclient.ui.BaseView;

/**
 * Created by SunDevs on 9/01/2018.
 */

public interface ClientContract {

    interface MainView extends BaseView{
        void changeEnabledButtonSend(boolean enabled);
    }

    interface ConnectivityChange{
        void attachView(MainView view);
        void detachView();
    }

    interface FormBookPresenter{
        void attachView(MainView view);
        void detachView();
        void sendBook(Book book);

    }
}
