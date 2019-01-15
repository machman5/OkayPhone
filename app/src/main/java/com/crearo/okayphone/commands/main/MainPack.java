package com.crearo.okayphone.commands.main;

import android.content.Context;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;

import com.crearo.okayphone.commands.CommandGroup;
import com.crearo.okayphone.commands.CommandsPreferences;
import com.crearo.okayphone.commands.ExecutePack;
import com.crearo.okayphone.managers.AliasManager;
import com.crearo.okayphone.managers.AppsManager;
import com.crearo.okayphone.managers.ContactManager;
import com.crearo.okayphone.managers.RssManager;
import com.crearo.okayphone.managers.flashlight.TorchManager;
import com.crearo.okayphone.managers.music.MusicManager2;
import com.crearo.okayphone.managers.xml.XMLPrefsManager;
import com.crearo.okayphone.managers.xml.options.Behavior;
import com.crearo.okayphone.tuils.interfaces.Redirectator;
import com.crearo.okayphone.tuils.libsuperuser.ShellHolder;

import java.io.File;
import java.lang.reflect.Method;

import okhttp3.OkHttpClient;

/**
 * Created by francescoandreuzzi on 24/01/2017.
 */

public class MainPack extends ExecutePack {

    //	current directory
    public File currentDirectory;

    //	resources references
    public Resources res;

    //	internet
    public WifiManager wifi;

    //	3g/data
    public Method setMobileDataEnabledMethod;
    public ConnectivityManager connectivityMgr;
    public Object connectMgr;

    //	contacts
    public ContactManager contacts;

    //	music
    public MusicManager2 player;

    //	apps & assocs
    public AliasManager aliasManager;
    public AppsManager appsManager;

    public CommandsPreferences cmdPrefs;

    public LocationManager locationManager;

    public String lastCommand;

    public Redirectator redirectator;

    public ShellHolder shellHolder;

    public RssManager rssManager;

    public OkHttpClient client;

    public MainPack(Context context, CommandGroup commandGroup, AliasManager alMgr, AppsManager appmgr, MusicManager2 p,
                    ContactManager c, Redirectator redirectator, ShellHolder shellHolder, RssManager rssManager, OkHttpClient client) {
        super(commandGroup);

        this.currentDirectory = XMLPrefsManager.get(File.class, Behavior.home_path);

        this.shellHolder = shellHolder;

        this.rssManager = rssManager;

        this.client = client;

        this.res = context.getResources();

        this.context = context;

        this.aliasManager = alMgr;
        this.appsManager = appmgr;

        this.cmdPrefs = new CommandsPreferences();

        this.player = p;
        this.contacts = c;

        this.redirectator = redirectator;
    }

    public void dispose() {
        TorchManager mgr = TorchManager.getInstance();
        if (mgr.isOn()) mgr.turnOff();
    }

    public void destroy() {
        if (player != null) player.destroy();
        appsManager.onDestroy();
        if (rssManager != null) rssManager.dispose();
        contacts.destroy(context);
    }
}
