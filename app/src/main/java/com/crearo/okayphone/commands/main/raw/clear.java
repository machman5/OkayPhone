package com.crearo.okayphone.commands.main.raw;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.crearo.okayphone.R;
import com.crearo.okayphone.UIManager;
import com.crearo.okayphone.commands.CommandAbstraction;
import com.crearo.okayphone.commands.ExecutePack;

/**
 * Created by andre on 07/11/15.
 */
public class clear implements CommandAbstraction {

    @Override
    public String exec(ExecutePack info) throws Exception {
        LocalBroadcastManager.getInstance(info.context.getApplicationContext()).sendBroadcast(new Intent(UIManager.ACTION_CLEAR));
        return null;
    }

    @Override
    public int[] argType() {
        return new int[0];
    }

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public int helpRes() {
        return R.string.help_clear;
    }

    @Override
    public String onArgNotFound(ExecutePack info, int index) {
        return null;
    }

    @Override
    public String onNotArgEnough(ExecutePack info, int nArgs) {
        return null;
    }
}
