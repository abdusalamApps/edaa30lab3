package textproc;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

public class BookReaderController extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Book Reader");
        primaryStage.setScene(scene);
        primaryStage.show();

        List<TextProcessor> textProcessors = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/undantagsord.txt"));
        scanner.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
        Set<String> stopWords = new HashSet<>();
        while (scanner.hasNext()) {
            stopWords.add(scanner.next().toLowerCase());
        }
        GeneralWordCounter generalWordCounter = new GeneralWordCounter(stopWords);
        textProcessors.add(generalWordCounter);

        Scanner s = null;
        for (TextProcessor textProcessor : textProcessors) {
            s = new Scanner(new File("src/nilsholg.txt"));
            s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

            while (s.hasNext()) {
                String word = s.next().toLowerCase();

                textProcessor.process(word);
            }
//                textProcessor.report();
        }
        s.close();

        ObservableList<Map.Entry<String, Integer>> words = FXCollections.observableArrayList(generalWordCounter.getWords());
        ListView<Map.Entry<String, Integer>> listView = new ListView<>(words);
        root.setCenter(listView);

        HBox hBox = new HBox();
        Button alphabetical = new Button("Alphabetic");
        Button frequency = new Button("Frequency");
        hBox.getChildren().addAll(alphabetical, frequency);
        root.setBottom(hBox);

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
