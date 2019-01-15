package com.crearo.okayphone.managers.xml.options;

import com.crearo.okayphone.managers.notifications.reply.ReplyManager;
import com.crearo.okayphone.managers.xml.classes.XMLPrefsElement;
import com.crearo.okayphone.managers.xml.classes.XMLPrefsSave;

/**
 * Created by francescoandreuzzi on 17/01/2018.
 */

public enum Reply implements XMLPrefsSave {

    reply_enabled {
        @Override
        public String defaultValue() {
            return "true";
        }

        @Override
        public String type() {
            return XMLPrefsSave.BOOLEAN;
        }

        @Override
        public String info() {
            return "If false, notification reply will be disabled";
        }
    };

    @Override
    public XMLPrefsElement parent() {
        return ReplyManager.instance;
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
