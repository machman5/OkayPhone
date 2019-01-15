package com.crearo.okayphone.commands.specific;

import com.crearo.okayphone.R;
import com.crearo.okayphone.commands.CommandAbstraction;
import com.crearo.okayphone.commands.ExecutePack;
import com.crearo.okayphone.commands.main.MainPack;
import com.crearo.okayphone.commands.main.Param;
import com.crearo.okayphone.managers.xml.classes.XMLPrefsSave;
import com.crearo.okayphone.tuils.SimpleMutableEntry;
import com.crearo.okayphone.tuils.Tuils;

/**
 * Created by francescoandreuzzi on 01/05/2017.
 */

public abstract class ParamCommand implements CommandAbstraction {

    @Override
    public final int[] argType() {
        return new int[] {CommandAbstraction.PARAM};
    }

    @Override
    public final String exec(ExecutePack pack) throws Exception {
        String o = doThings(pack);
        if(o != null) return o;

        Param param = pack.get(Param.class);
        if(param == null) {
            Object o1 = pack.get(Object.class, 0);
            return pack.context.getString(R.string.output_invalid_param) + Tuils.SPACE + (o1 != null ? o1.toString() : "null");
        }
        return param.exec(pack);
    }

    public SimpleMutableEntry<Boolean, Param> getParam(MainPack pack, String param) {
        Param p = paramForString(pack, param);
        if(p == null && defaultParamReference() != null) {
            return new SimpleMutableEntry<>(true, paramForString(pack, defaultParam(pack)));
        }
        return new SimpleMutableEntry<>(false, p);
    }

    public String defaultParam(MainPack pack) {
        String def = pack.cmdPrefs.get(defaultParamReference());
        if(!def.startsWith("-")) def = "-" + def;
        return def;
    }

    @Override
    public String onNotArgEnough(ExecutePack pack, int nArgs) {
        return pack.context.getString(helpRes());
    }

    @Override
    public String onArgNotFound(ExecutePack pack, int indexNotFound) {
        if(indexNotFound == 0) {
            String param = pack.get(String.class, 0);
            if(param.length() == 0) return pack.context.getString(helpRes());

            return pack.context.getString(R.string.output_invalid_param) + Tuils.SPACE + param;
        }

        return pack.context.getString(helpRes());
    }

    public abstract String[] params();
    protected abstract Param paramForString(MainPack pack, String param);
    protected abstract String doThings(ExecutePack pack);
    public XMLPrefsSave defaultParamReference() {
        return null;
    }
}
