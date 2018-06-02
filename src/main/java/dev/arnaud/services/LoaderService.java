package dev.arnaud.services;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;
import org.apache.log4j.Logger;

public class LoaderService {
    private ObservableList<String> observableFilesList;
    private static final Logger logger = Logger.getLogger(LoaderService.class.getName());


    public LoaderService() {
        observableFilesList = FXCollections.observableArrayList();
    }

    public void loadFile(String fileName) {
        logger.debug("Loading file: " + fileName);
        File f = new File(fileName);
        if(f.exists() && !f.isDirectory()) {
            logger.debug("Adding file to list");
            observableFilesList.add(fileName);
        }
    };

    public void unloadFile(String fileName){
        if (observableFilesList.contains(fileName))
            observableFilesList.remove(fileName);
    }


    public ObservableList<String> getFilesList(){
        return observableFilesList;
    }
}
