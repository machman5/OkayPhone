package com.crearo.okayphone.tuils.libsuperuser;

import android.content.Context;

import com.crearo.okayphone.managers.xml.XMLPrefsManager;
import com.crearo.okayphone.managers.xml.options.Behavior;
import com.crearo.okayphone.tuils.Tuils;

import java.io.File;

/**
 * Created by francescoandreuzzi on 18/08/2017.
 */

public class ShellHolder {

    private Context context;

    public ShellHolder(Context context) {
        this.context = context;
    }

    public Shell.Interactive build() {
        Shell.Interactive interactive = new Shell.Builder()
                .setOnSTDOUTLineListener(new StreamGobbler.OnLineListener() {
                    @Override
                    public void onLine(String line) {
                        Tuils.sendOutput(context, line);
                    }
                })
                .setOnSTDERRLineListener(new StreamGobbler.OnLineListener() {
                    @Override
                    public void onLine(String line) {
                        Tuils.sendOutput(context, line);
                    }
                })
                .open();
        interactive.addCommand("cd " + XMLPrefsManager.get(File.class, Behavior.home_path));
        return interactive;
    }
}
