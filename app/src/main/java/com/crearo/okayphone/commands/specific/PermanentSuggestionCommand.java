package com.crearo.okayphone.commands.specific;

import com.crearo.okayphone.commands.CommandAbstraction;

/**
 * Created by francescoandreuzzi on 29/01/2017.
 */

public abstract class PermanentSuggestionCommand implements CommandAbstraction {

    public abstract String[] permanentSuggestions();
}
