package com.crearo.okayphone.commands.tuixt.raw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.crearo.okayphone.R;
import com.crearo.okayphone.commands.CommandAbstraction;
import com.crearo.okayphone.commands.ExecutePack;
import com.crearo.okayphone.commands.tuixt.TuixtPack;
import com.crearo.okayphone.tuils.Tuils;

/**
 * Created by francescoandreuzzi on 24/01/2017.
 */

public class help implements CommandAbstraction {

    @Override
    public String exec(ExecutePack info) throws Exception {
        TuixtPack pack = (TuixtPack) info;

        CommandAbstraction cmd = info.get(CommandAbstraction.class);
        int res = cmd == null ? R.string.output_commandnotfound : cmd.helpRes();
        return pack.resources.getString(res);
    }

    @Override
    public int[] argType() {
        return new int[] {CommandAbstraction.COMMAND};
    }

    @Override
    public int priority() {
        return 5;
    }

    @Override
    public int helpRes() {
        return R.string.help_tuixt_help;
    }

    @Override
    public String onArgNotFound(ExecutePack info, int index) {
        return onNotArgEnough(info, 0);
    }

    @Override
    public String onNotArgEnough(ExecutePack pack, int nArgs) {
        TuixtPack info = (TuixtPack) pack;
        List<String> toPrint = new ArrayList<>(Arrays.asList(info.commandGroup.getCommandNames()));

        Collections.sort(toPrint, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return Tuils.alphabeticCompare(lhs, rhs);
            }
        });

        Tuils.addPrefix(toPrint, Tuils.DOUBLE_SPACE);
        Tuils.addSeparator(toPrint, Tuils.TRIBLE_SPACE);
        Tuils.insertHeaders(toPrint, true);

        return Tuils.toPlanString(toPrint, Tuils.EMPTYSTRING);
    }
}
