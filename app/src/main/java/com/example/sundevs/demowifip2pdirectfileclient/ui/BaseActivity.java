package com.example.sundevs.demowifip2pdirectfileclient.ui;

import android.app.Activity;
import android.support.v4.content.ContextCompat;

import es.dmoral.toasty.Toasty;

/**
 * Created by SunDevs on 9/01/2018.
 */

public abstract class BaseActivity extends Activity implements BaseView {

    public BaseActivity() {
    }

    @Override
    public void toastyConfig() {
        Toasty.Config.getInstance()
                .setErrorColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                .setInfoColor(ContextCompat.getColor(this, android.R.color.darker_gray))
                .setSuccessColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
                .setWarningColor(ContextCompat.getColor(this, android.R.color.holo_orange_light))
                .setTextColor(ContextCompat.getColor(this, android.R.color.white))
                .apply();
    }

    @Override
    public void showMessage(String message, MessageType type) {
        switch (type) {
            case SUCCESS:
                Toasty.success(this, message).show();
                break;
            case ERROR:
                Toasty.error(this, message).show();
                break;
            case WARNING:
                Toasty.warning(this, message).show();
                break;
            case INFO:
                Toasty.info(this, message).show();
                break;
        }
    }
}
