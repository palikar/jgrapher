package dev.arnaud.services;


import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphService {
    private static final Logger logger = Logger.getLogger(GraphService.class.getName());
    private LoaderService loader;
    private Map<String, Image> graphImages;
    private String engineType = "dot";
    private List<String> engineTypes = Arrays.asList("dot");

    private static final String TEMP_DIR = "~/tmp/".replaceFirst("^~", System.getProperty("user.home"));
    private static final String DOT_COMMAND = "dot";


    public  GraphService(LoaderService loader){
        this.loader = loader;
        graphImages = new HashMap<>();
        this.loader.getFilesList().addListener(this::filesListChanged);

    }
    private void filesListChanged(ListChangeListener.Change<? extends String> c)
    {
        c.next();
        if(c.wasAdded())
            c.getAddedSubList().forEach(this::generateGraph);
        else
            c.getRemoved().forEach(file -> this.graphImages.remove(file));

    }
    private void generateGraph(String filePath){
        try {
            logger.debug("Generating graph for " + filePath);
            File img = File.createTempFile("graph_", ".png", new File(GraphService.TEMP_DIR));
            Runtime rt = Runtime.getRuntime();
            String[] args = {DOT_COMMAND, "-Tpng", filePath, "-o", img.getAbsolutePath()};
            Process p = rt.exec(args);
            p.waitFor();
            logger.debug("File generated: " + img.toURI().toString());
            Image genereatedImage = new Image(img.toURI().toString());
            graphImages.put(filePath, genereatedImage);

            if (img.delete() == false)
                logger.warn("Warning: " + img.getAbsolutePath() + " could not be deleted!");

        } catch (IOException e) {
            logger.error("Exception during creaion of image from .dot file");
            e.printStackTrace();
            logger.trace("Printing stack trace");
        } catch (InterruptedException e) {
            logger.error("Exception during executing dot command.");
            logger.trace("Printing stack trace");
        }
    }


    public Image getImage(String fileName) {
        if(graphImages.containsKey(fileName))
           return graphImages.get(fileName);
        else {
            logger.error("Graphing service does not contain image for file: " + fileName);
            return null;
        }
    }

    public void refreshFile(String fileName){
        if(graphImages.containsKey(fileName))
            generateGraph(fileName);
        else
            logger.warn("Graphing service does not contain image for file: " + fileName);
    }

}