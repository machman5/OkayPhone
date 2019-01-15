package com.crearo.okayphone.tuils.interfaces;

import com.crearo.okayphone.commands.specific.RedirectCommand;

/**
 * Created by francescoandreuzzi on 03/03/2017.
 */

public interface Redirectator {

    void prepareRedirection(RedirectCommand cmd);

    void cleanup();
}
