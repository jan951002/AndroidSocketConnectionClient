package com.example.sundevs.demowifip2pdirectfileclient;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;

import com.example.sundevs.demowifip2pdirectfileclient.domain.Book;
import com.example.sundevs.demowifip2pdirectfileclient.ui.BasePresenter;
import com.example.sundevs.demowifip2pdirectfileclient.ui.BaseView;

/**
 * Created by SunDevs on 9/01/2018.
 */

public interface ClientContract {
    interface MainView extends BaseView{
        void setClientWifiStatus(String s);
        void setClientStatus(String s);
        void setNetworkToReadyState(WifiP2pInfo wifiInfo, WifiP2pDevice device);
        void displayPeers(final WifiP2pDeviceList peers);
    }

    interface FormBookView extends BaseView{

    }

    interface MainPresenter {
        void attachView(ClientContract.MainView view);
        void detachView();
    }

    interface FormBookPresenter{
        void attachView(ClientContract.FormBookView view);
        void detachView();
        void sendBook(Book book);
    }
}
