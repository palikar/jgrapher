package dev.arnaud;

import dev.arnaud.services.MainModule;

public class Global {


    static public final MainModule mainModule = new MainModule();

    static {
        mainModule.getLoaderService();
        mainModule.getGraphService();
    }

    public Global(){}
}
