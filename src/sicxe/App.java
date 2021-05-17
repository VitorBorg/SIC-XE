package sicxe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sicxe.Carregador.Carregador;
import sicxe.GUI.CPUViewController;
import sicxe.GUI.InputViewController;
import sicxe.GUI.MainViewController;
import sicxe.GUI.MemoryViewController;
import sicxe.Helpers.ParseSourceLine;
import sicxe.Maquina.Maquina;
import sicxe.Memory.Memory;
import sicxe.Memory.Register;
import sicxe.Memory.Variables;
import sicxe.Montador.Montador;
import sicxe.Table.Table;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App extends Application {
    public static Integer line = 1;
    public static Integer endereco = 0;
    public static List<ParseSourceLine> listaCodigoFonte;
    public static Montador montador;
    public static Carregador carregador;
    public static Memory memoria;
    public static Register reg;
    public static Variables vars;
    public static Maquina maquina;

    public static Table table;
    
    //input data and memory
    public static File inputFile;
    public static FileChooser fileChooser = new FileChooser();
    public static String inputFileText;
    public static List<String> addressStringList;
    public static List<String> dataStringList;

    //Stages (GUI windows)
    public static Stage primaryStage;
    public static Stage inputViewStage;
    public static Stage cpuViewStage;
    public static Stage memoryViewStage;
    public static Scene inputViewScene;
    public static Scene cpuViewScene;
    public static Scene memoryViewScene;

    //instances of the controller classes
    public static MainViewController mainViewController;
    public static InputViewController inputViewController;
    public static CPUViewController cpuViewController;
    public static MemoryViewController memoryViewController;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception  {

        //input file window configuration
        App.inputViewStage = new Stage();
        App.inputViewStage.setTitle("Input File");
        inputViewStage.initModality(Modality.WINDOW_MODAL);

        FXMLLoader inputViewLoader = new FXMLLoader();
        inputViewLoader.setLocation(App.class.getResource("GUI/inputFileView.fxml"));
        BorderPane inputViewLayout = inputViewLoader.load();
        inputViewScene = new Scene(inputViewLayout);
        inputViewController = inputViewLoader.getController();

        //cpu window configuration
        App.cpuViewStage = new Stage();
        App.cpuViewStage.setTitle("CPU");
        inputViewStage.initModality(Modality.WINDOW_MODAL);

        FXMLLoader cpuViewLoader = new FXMLLoader();
        cpuViewLoader.setLocation(App.class.getResource("GUI/cpuView.fxml"));
        BorderPane cpuViewLayout = cpuViewLoader.load();
        cpuViewScene = new Scene(cpuViewLayout);
        cpuViewController = cpuViewLoader.getController();

        //memory window configuration
        App.memoryViewStage = new Stage();
        App.memoryViewStage.setTitle("Memory View");
        inputViewStage.initModality(Modality.WINDOW_MODAL);

        FXMLLoader memoryViewLoader = new FXMLLoader();
        memoryViewLoader.setLocation(App.class.getResource("GUI/memoryView.fxml"));
        BorderPane memoryViewLayout = memoryViewLoader.load();
        memoryViewScene = new Scene(memoryViewLayout);
        memoryViewController = memoryViewLoader.getController();

        //memory window configuration
        App.primaryStage = primaryStage;
        App.primaryStage.setTitle("SIC/XE");
        FXMLLoader mainViewLoader = new FXMLLoader();
        mainViewLoader.setLocation(App.class.getResource("GUI/mainView.fxml"));
        BorderPane mainLayout = mainViewLoader.load();
        Scene mainViewScene = new Scene(mainLayout);
        mainViewController = mainViewLoader.getController();
        primaryStage.setScene(mainViewScene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        //shows the main window
        primaryStage.show();
    }

    //opens a new window showing the contents of the input file
    public static void showInputView() {

        inputViewStage.setScene(inputViewScene);
        inputViewStage.setMinWidth(250);
        inputViewStage.setMinHeight(329);
        inputViewStage.show();
    }

    //opens a new window showing the contents of the CPU
    public static void showCPUView() {

        cpuViewStage.setScene(cpuViewScene);
        cpuViewStage.setMinWidth(565);
        cpuViewStage.setMinHeight(329);
        cpuViewStage.show();
    }

    //opens a new window showing the memory data
    public static void showMemoryView() {

        memoryViewStage.setScene(memoryViewScene);
        memoryViewStage.setMinWidth(280);
        memoryViewStage.setMinHeight(329);
        memoryViewStage.show();
    }

    //opens a new window with information about the project and its members
    public static void showAboutView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("GUI/aboutView.fxml"));
        BorderPane aboutWindow = loader.load();

        Stage aboutStage = new Stage();
        aboutStage.setTitle("About");
        aboutStage.initModality(Modality.WINDOW_MODAL);
        aboutStage.initOwner(primaryStage);
        Scene scene = new Scene(aboutWindow);
        aboutStage.setScene(scene);
        aboutStage.showAndWait();
    }

    //opens the file dialog to select the input file
    public static void openFileDialog() throws IOException {
        fileChooser.setTitle("Select ASM file to load");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("ASM Files", "*.asm", ".txt"));
        Stage primaryStage = (Stage) App.primaryStage.getScene().getWindow();

        inputFile = fileChooser.showOpenDialog(primaryStage);
        inputFileText = Files.readString(Paths.get(inputFile.getAbsolutePath()));

        //clears the MemoryView window if a new file is loaded
        if ((App.addressStringList != null) & ((App.dataStringList != null))) {
            App.memoryViewController.clearMemory();
        }
    }
}
