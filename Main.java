import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {
    Button button1, button2, button3;
    Scene scene1, scene2;
    private TableView<FoodItem> table1 = new TableView<FoodItem>();
    private ObservableList<FoodItem> data = FXCollections.observableArrayList(new FoodItem("Tomato",
            10, 5, 2, 5, 1), new FoodItem("Pancakes", 599, 5,
            1, 10, 4), new FoodItem("Hamburger", 800, 13, 4, 20,
            27));

    public static void main(String[] args) {
        launch(args); // method that sets up program as javafx program
    }

    @Override
    public void start(Stage primaryStage) {
//        // window is called the stage
//        primaryStage.setTitle("Meal Plan");
//
//        // Button 1
//        button1 = new Button("Go to scene2.");
//        // click the button handle method handles it
//        // button.setOnAction(this);
//        // change the position in the Y direction
//        button1.setTranslateY(30);
//        button1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Anonymous Inner Class");
//            }
//        });
//        // e represents the event, function is on the right side of arrow operator
//        button1.setOnAction(e -> primaryStage.setScene(scene2));
//
//
//        StackPane layout = new StackPane();
//        layout.getChildren().add(button1);
//
//        // content inside the window is called the scene
//        // Scene scene = new Scene(layout, 300, 250);
//
//
//        Label label1 = new Label("Welcome to the first scene!");
//
//        // Layout 1 - children are laid out in vertical column
//        StackPane layout1 = new StackPane();
//        layout1.getChildren().addAll(label1, button1);
//
//        scene1 = new Scene(layout1, 600, 600);
//
//        // Button 2
//        button2 = new Button("Go back to scene1.");
//        button2.setOnAction(e -> primaryStage.setScene(scene1));
//
//        // Button 3
//        button3 = new Button("AlertBox");
//        button3.setOnAction(e -> AlertBox.display("AlertBox", "Opened AlertBox."));
//        button3.setTranslateY(50);
//        VBox layout3 = new VBox(20);
//        layout3.getChildren().add(button3);
//
//        // Layout 2
//        StackPane layout2 = new StackPane();
//        layout2.getChildren().addAll(button2, button3);
//        scene2 = new Scene(layout2, 600, 600);
        primaryStage.setTitle("Meal Plan");
        primaryStage.setWidth(450);
        primaryStage.setHeight(550);

        Label label1 = new Label("Food Options");
        Button button4 = new Button("Your Meal Plan");
        button4.setOnAction(e -> YourMealPlan.display("Your Meal Plan", "You have no items in your meal plan yet."));
        button4.setTranslateX(1690);

        table1.setEditable(true);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<FoodItem, String>("Name"));

        TableColumn caloriesCol = new TableColumn("Calories");
        caloriesCol.setMinWidth(50);
        caloriesCol.setCellValueFactory(
                new PropertyValueFactory<FoodItem, Integer>("calories"));

        TableColumn fatsCol = new TableColumn("Fats");
        fatsCol.setMinWidth(50);
        fatsCol.setCellValueFactory(
                new PropertyValueFactory<FoodItem, String>("fats"));

        TableColumn fiberCol = new TableColumn("Fiber");
        fiberCol.setMinWidth(50);
        fiberCol.setCellValueFactory(
                new PropertyValueFactory<FoodItem, String>("fiber"));

        TableColumn carbohydratesCol = new TableColumn("Carbohydrates");
        carbohydratesCol.setMinWidth(50);
        carbohydratesCol.setCellValueFactory(
                new PropertyValueFactory<FoodItem, String>("carbohydrates"));

        TableColumn proteinsCol = new TableColumn("Proteins");
        proteinsCol.setMinWidth(50);
        proteinsCol.setCellValueFactory(
                new PropertyValueFactory<FoodItem, String>("proteins"));

        FilteredList<FoodItem> filterMacros = new FilteredList(data, p -> true);
        table1.setItems(filterMacros);//Set the table's items using the filtered list
        table1.getColumns().addAll(nameCol, caloriesCol, fatsCol, fiberCol, carbohydratesCol, proteinsCol);

        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Name", "Calories", "Fats", "Fiber", "Carbohydrates", "Proteins");
        choiceBox.setValue("Calories");

        TextField textField = new TextField();
        textField.setPromptText("Search Here!");
        textField.setOnKeyReleased(keyEvent ->
        {
            switch (choiceBox.getValue()) {
                case "Name":
                    filterMacros.setPredicate(p -> p.getName().toLowerCase().contains(textField.getText().toLowerCase().trim()));
                case "Calories":
                    filterMacros.setPredicate(p -> p.getCalories() == Integer.parseInt(textField.getText()));
                    break;
                case "Fats":
                    filterMacros.setPredicate(p -> p.getFats() == Integer.parseInt(textField.getText()));
                    break;
                case "Fiber":
                    filterMacros.setPredicate(p -> p.getFiber() == Integer.parseInt(textField.getText()));
                    break;
                case "Carbohydrates":
                    filterMacros.setPredicate(p -> p.getCarbohydrates() == Integer.parseInt(textField.getText()));
                    break;
                case "Proteins":
                    filterMacros.setPredicate(p -> p.getProteins() == Integer.parseInt(textField.getText()));
                    break;
            }
            choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) ->{
                if (newValue != null) {
                    textField.setText("");
                    filterMacros.setPredicate(null);
                }
            } );
        });
        HBox layout4 = new HBox(choiceBox, textField);
        layout4.setAlignment(Pos.CENTER);
        VBox layout5 = new VBox();

        layout5.setSpacing(5);
        layout5.setPadding(new Insets(10, 0, 0 , 10));

        HBox layout6 = new HBox(button4);
        layout6.setAlignment(Pos.TOP_RIGHT);

        HBox layout3 = new HBox(label1, layout6);
        layout5.getChildren().addAll(layout3, table1, layout4);

        Scene scene3 = new Scene(layout5);
        primaryStage.setScene(scene3);
        primaryStage.show();

    }

    @Override
    public void handle(ActionEvent event) {
        // get the source of the button to identify it from other buttons
        if (event.getSource() == button2) {
            System.out.println("Clicked the button.");
        }
    }
}
