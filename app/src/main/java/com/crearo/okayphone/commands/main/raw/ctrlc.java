package com.crearo.okayphone.commands.main.raw;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import java.io.File;

import com.crearo.okayphone.MainManager;
import com.crearo.okayphone.R;
import com.crearo.okayphone.UIManager;
import com.crearo.okayphone.commands.CommandAbstraction;
import com.crearo.okayphone.commands.ExecutePack;
import com.crearo.okayphone.commands.main.MainPack;
import com.crearo.okayphone.managers.xml.XMLPrefsManager;
import com.crearo.okayphone.managers.xml.options.Behavior;
import com.crearo.okayphone.tuils.StoppableThread;

/**
 * Created by francescoandreuzzi on 26/07/2017.
 */

public class ctrlc implements CommandAbstraction {

    @Override
    public String exec(final ExecutePack pack) throws Exception {
        new StoppableThread() {
            @Override
            public void run() {
                super.run();

                MainManager.interactive.kill();
                MainManager.interactive.close();
                MainManager.interactive = null;

                MainManager.interactive = ((MainPack) pack).shellHolder.build();

                ((MainPack) pack).currentDirectory = XMLPrefsManager.get(File.class, Behavior.home_path);
                LocalBroadcastManager.getInstance(pack.context.getApplicationContext()).sendBroadcast(new Intent(UIManager.ACTION_UPDATE_HINT));
            }
        }.start();

        LocalBroadcastManager.getInstance(pack.context.getApplicationContext()).sendBroadcast(new Intent(UIManager.ACTION_NOROOT));

        return null;
    }

    @Override
    public int[] argType() {
        return new int[0];
    }

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public int helpRes() {
        return R.string.help_ctrlc;
    }

    @Override
    public String onArgNotFound(ExecutePack pack, int indexNotFound) {
        return null;
    }

    @Override
    public String onNotArgEnough(ExecutePack pack, int nArgs) {
        return null;
    }
}
