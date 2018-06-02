package dev.arnaud;

import dev.arnaud.services.MainModule;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.controlsfx.tools.Borders;

import javax.swing.table.TableColumn;
import javax.swing.text.View;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewTab implements Initializable {

  private static final Logger logger = Logger.getLogger(ViewTab.class.getName());

  MainModule mainModule = Global.mainModule;



  @FXML
  Label loadingGraphsLabel;
  @FXML
  TableView loadingGraphsTable;

  @FXML
  Canvas previewPaneCanvas;

  private FileChooser fileChooser;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    previewPaneCanvas.getGraphicsContext2D().setFill(Color.RED);
    previewPaneCanvas.getGraphicsContext2D().fillRect(0,0,previewPaneCanvas.getWidth(), previewPaneCanvas.getHeight());

    fileChooser = new FileChooser();



  }

  public void addFile()
  {
    File f = fileChooser.showOpenDialog(null);
    if (f.isFile()) {
      logger.debug("Opening file: " + f.getAbsolutePath());
      mainModule.getLoaderService().loadFile(f.getAbsolutePath());

    }
    
  }
  public void removeAll()
  {



  }

}
