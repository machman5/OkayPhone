package com.crearo.okayphone.commands.main;

import com.crearo.okayphone.commands.ExecutePack;

/**
 * Created by francescoandreuzzi on 10/06/2017.
 */

public interface Param {

    int[] args();

    String exec(ExecutePack pack);

    String label();

    String onNotArgEnough(ExecutePack pack, int n);

    String onArgNotFound(ExecutePack pack, int index);
}
