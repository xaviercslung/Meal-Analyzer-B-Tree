import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class YourMealPlan {
    public static void display(String title, String message){
        Stage secondaryStage = new Stage();

        // block input events with other windows until this one is taken care
        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        secondaryStage.setTitle(title);
        secondaryStage.setMinWidth(600);
        secondaryStage.setMinHeight(900);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close the window.");
        closeButton.setOnAction(e -> secondaryStage.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        secondaryStage.setScene(scene);
        secondaryStage.showAndWait();

    }
}
