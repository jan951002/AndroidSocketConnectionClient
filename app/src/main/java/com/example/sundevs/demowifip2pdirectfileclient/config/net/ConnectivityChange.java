package com.example.sundevs.demowifip2pdirectfileclient.config.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import com.example.sundevs.demowifip2pdirectfileclient.ClientContract;
import com.example.sundevs.demowifip2pdirectfileclient.ui.MessageType;

/**
 * Created by SunDevs on 10/01/2018.
 */

public class ConnectivityChange extends BroadcastReceiver implements ClientContract.ConnectivityChange{

    private ClientContract.MainView view;

    public ConnectivityChange() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        final String action = intent.getAction();
        if(action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            boolean connected = info.isConnected();
            if (connected) {
                if(view != null){
                    view.showMessage("Connected", MessageType.SUCCESS);
                }
            } else {
                if(view != null){
                    view.showMessage("No Connected", MessageType.WARNING);
                }
            }
            if(view != null){
                view.changeEnabledButtonSend(connected);
            }
        }
    }

    @Override
    public void attachView(ClientContract.MainView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
