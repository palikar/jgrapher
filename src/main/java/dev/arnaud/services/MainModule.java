package dev.arnaud.services;


import com.vanillasource.jaywire.standalone.StandaloneModule;

public class MainModule extends StandaloneModule {

    public LoaderService getLoaderService() {
        return singleton(() -> new LoaderService());
    }

    public GraphService getGraphService() {
        return singleton(() -> new GraphService(getLoaderService()));
    }



}