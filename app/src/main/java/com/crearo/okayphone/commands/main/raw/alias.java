package com.crearo.okayphone.commands.main.raw;

import com.crearo.okayphone.R;
import com.crearo.okayphone.commands.CommandAbstraction;
import com.crearo.okayphone.commands.ExecutePack;
import com.crearo.okayphone.commands.main.MainPack;
import com.crearo.okayphone.commands.specific.ParamCommand;
import com.crearo.okayphone.managers.AliasManager;
import com.crearo.okayphone.tuils.Tuils;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by andre on 15/11/15.
 */
public class alias extends ParamCommand {

    private enum Param implements com.crearo.okayphone.commands.main.Param {

        add {
            @Override
            public String exec(ExecutePack pack) {
                ArrayList<String> args = pack.getList();
                if (args.size() < 2) return pack.context.getString(R.string.output_lessarg);

                if (((MainPack) pack).aliasManager.add(args.remove(0), Tuils.toPlanString(args, Tuils.SPACE))) {
                    return null;
                } else {
                    return pack.context.getString(R.string.output_problemaddingalias);
                }
            }

            @Override
            public int[] args() {
                return new int[]{CommandAbstraction.TEXTLIST};
            }
        },
        rm {
            @Override
            public String exec(ExecutePack pack) {
                ArrayList<String> args = pack.getList();
                if (args.size() < 1) return pack.context.getString(R.string.output_lessarg);
                if (((MainPack) pack).aliasManager.remove(args.get(0))) return null;
                else
                    return pack.context.getString(R.string.output_aliasnotfound) + Tuils.SPACE + args.get(0);
            }

            @Override
            public int[] args() {
                return new int[]{CommandAbstraction.TEXTLIST};
            }
        },
        file {
            @Override
            public String exec(ExecutePack pack) {
                pack.context.startActivity(Tuils.openFile(new File(Tuils.getFolder(), AliasManager.PATH)));
                return null;
            }

            @Override
            public int[] args() {
                return new int[0];
            }
        },
        ls {
            @Override
            public String exec(ExecutePack pack) {
                return ((MainPack) pack).aliasManager.printAliases();
            }

            @Override
            public int[] args() {
                return new int[0];
            }
        },
        tutorial {
            @Override
            public int[] args() {
                return new int[0];
            }

            @Override
            public String exec(ExecutePack pack) {
                pack.context.startActivity(Tuils.webPage("https://github.com/Andre1299/TUI-ConsoleLauncher/wiki/Alias"));
                return null;
            }
        };

        static Param get(String p) {
            p = p.toLowerCase();
            Param[] ps = values();
            for (Param p1 : ps)
                if (p.endsWith(p1.label()))
                    return p1;
            return null;
        }

        static String[] labels() {
            Param[] ps = values();
            String[] ss = new String[ps.length];

            for (int count = 0; count < ps.length; count++) {
                ss[count] = ps[count].label();
            }

            return ss;
        }

        @Override
        public String label() {
            return Tuils.MINUS + name();
        }

        @Override
        public String onNotArgEnough(ExecutePack pack, int index) {
            return pack.context.getString(R.string.help_alias);
        }

        @Override
        public String onArgNotFound(ExecutePack pack, int index) {
            return null;
        }
    }


    @Override
    public String[] params() {
        return Param.labels();
    }

    @Override
    protected com.crearo.okayphone.commands.main.Param paramForString(MainPack pack, String param) {
        return Param.get(param);
    }

    @Override
    protected String doThings(ExecutePack pack) {
        return null;
    }

    @Override
    public int helpRes() {
        return R.string.help_alias;
    }

    @Override
    public int priority() {
        return 2;
    }
}
