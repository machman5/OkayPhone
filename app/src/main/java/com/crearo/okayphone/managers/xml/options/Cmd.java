package com.crearo.okayphone.managers.xml.options;

import com.crearo.okayphone.managers.xml.XMLPrefsManager;
import com.crearo.okayphone.managers.xml.classes.XMLPrefsElement;
import com.crearo.okayphone.managers.xml.classes.XMLPrefsSave;

/**
 * Created by francescoandreuzzi on 24/09/2017.
 */

public enum Cmd implements XMLPrefsSave {

    default_search {
        @Override
        public String defaultValue() {
            return "-gg";
        }

        @Override
        public String info() {
            return "The param that will be used if you type \"search apples\" instead of \"search -param apples\"";
        }

        @Override
        public String type() {
            return XMLPrefsSave.TEXT;
        }
    };

    @Override
    public XMLPrefsElement parent() {
        return XMLPrefsManager.XMLPrefsRoot.CMD;
    }

    @Override
    public String label() {
        return name();
    }

    @Override
    public boolean is(String s) {
        return name().equals(s);
    }
}
