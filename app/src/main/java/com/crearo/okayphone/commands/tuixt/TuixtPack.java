package com.crearo.okayphone.commands.tuixt;

import android.content.Context;
import android.content.res.Resources;
import android.widget.EditText;

import java.io.File;

import com.crearo.okayphone.commands.CommandGroup;
import com.crearo.okayphone.commands.ExecutePack;

/**
 * Created by francescoandreuzzi on 25/01/2017.
 */

public class TuixtPack extends ExecutePack {

    public File editFile;
    public EditText editText;

    public Resources resources;

    public TuixtPack(CommandGroup group, File file, Context context, EditText editText) {
        super(group);

        this.editText = editText;
        editFile = file;
        this.context = context;
        this.resources = context.getResources();
    }
}
