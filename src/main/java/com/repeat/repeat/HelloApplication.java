package com.repeat.repeat;

import com.repeat.repeat.data.Database;
import com.repeat.repeat.data.SaveAndReadData;
import com.repeat.repeat.panel.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    private Category category = null;
    private Subcategory subcategory = null;

    private final static Button uniqueButton = new Button();
    private static Text information = new Text("");
    private static ChoiceBox categoryChoice = new ChoiceBox();
    private static ChoiceBox subCatChoice = new ChoiceBox();
    public static Text getInformation() {return information;};
    public static Button getUniqueButton() {
        return uniqueButton;
    }

    public static ChoiceBox getCategoryChoice() {return categoryChoice;}

    public static ChoiceBox getSubCatChoice() {return subCatChoice;}

    private final Image settingsImage = new Image("File:/Users/sebastianboron/Repeat/src/main/resources/settings.png");
    private final Image basketImage = new Image("File:/Users/sebastianboron/Repeat/src/main/resources/basket.png");


    @Override
    public void start(Stage stage) throws IOException {

        SaveAndReadData sd = new SaveAndReadData();
        SaveAndReadData.readFile();
        GridPane gridPane = new GridPane();
        information.setFill(Color.RED);
        information.setFont(Font.font(11));
        Text labelCategory = new Text("Select category:");
        Text labelSubcatogry = new Text("Select subcategory:");
        categoryChoice = new ChoiceBox<>(FXCollections.observableArrayList(Database.getListOfAllCategory()));
        subCatChoice = new ChoiceBox<>();
        Button addCategory = new Button("Add category");
        Button addSubcat = new Button("Add subcategory");
        Button removeCategory = new Button("Remove category");
        Button removeSubcategory = new Button("Remove subcategory");
        Button startRepeat = new Button("REPEAT!");
        Button addQuestion = new Button("New question");
        Menu menuSettings = new Menu("", new ImageView(settingsImage));
        Menu menuBasket = new Menu("", new ImageView(basketImage));
        MenuItem menuAdd1 = new MenuItem("", addCategory);
        MenuItem menuAdd2 = new MenuItem("", addSubcat);
        MenuItem menuRemove1 = new MenuItem("", removeCategory);
        MenuItem menuRemove2 = new MenuItem("", removeSubcategory);
        menuSettings.getItems().addAll(menuAdd1, menuAdd2);
        menuBasket.getItems().addAll(menuRemove1, menuRemove2);
        MenuBar menuBar = new MenuBar(menuSettings, menuBasket);

            gridPane.add(information, 1, 0);
            gridPane.add(menuBar, 4, 5);
            gridPane.add(labelCategory, 1, 1);
            gridPane.add(categoryChoice, 1, 2);
            gridPane.add(labelSubcatogry, 1, 3);
            gridPane.add(subCatChoice, 1, 4);
            gridPane.add(startRepeat, 4, 1);
            gridPane.add(addQuestion, 4, 2);
            Configuration.gridSettings(gridPane);

            Scene scene = new Scene(gridPane, 273, 194);

            stage.setTitle("Repeat");
            stage.setScene(scene);
            stage.show();

            addCategory.setOnAction(event ->
                    stage.setScene(new CategoryPanel().getScene())
            );

            addSubcat.setOnAction(event -> {
                stage.setScene(new SubcategoryPanel().getScene());
            });

            addQuestion.setOnAction(event -> {
                if(subcategory != null) {
                    stage.setScene(new QuestionPanel(category, subcategory).getScene());
                } else {
                    information.setText("Select category!");
                }});

            startRepeat.setOnAction(event -> {
                if(subcategory != null) {
                    if(subcategory.getListOfQuestions().size() == 0) {
                        information.setText("This subcategory is empty.\nAdd some questions first.");
                    } else {
                    stage.setScene(new RepeatPanel(category, subcategory.getListOfQuestions()).getScene());
                }} else {
                    information.setText("Select category!");
                }});

            removeCategory.setOnAction(event -> {
                if(category != null) {
                    RemoveScene.removeCategoryScene(category);
                } else {
                    information.setText("Select category!");
                }
            });

            removeSubcategory.setOnAction(event -> {
                boolean allQuestion = subcategory.equals(category.getSubcategoryList().get(0));
                if(subcategory != null && !allQuestion) {
                    RemoveScene.removeSubcategoryScene(category, subcategory);
                } else {
                    if(!allQuestion) {
                    information.setText("Select subcategory!");
                } else {
                        RemoveScene.removeCategoryScene(category);
                    }
                    }
            });

            uniqueButton.setOnAction(event -> {
                stage.setScene(scene);
                categoryChoice.setItems(FXCollections.observableArrayList(Database.getListOfAllCategory()));
                categoryChoice.setValue(category);
                if(category != null) {
                HelloApplication.getSubCatChoice().setItems(FXCollections.observableArrayList(category.getSubcategoryList()));
                }
                subCatChoice.setValue(subcategory);
                });

            categoryChoice.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
                int index = newValue.intValue();
                if(index >= 0) {
                    category = Database.getListOfAllCategory().get(newValue.intValue());
                }
                if(category != null) {
                    subCatChoice.setItems(FXCollections.observableArrayList(category.getSubcategoryList()));
                }});

                subCatChoice.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
                int index = newValue.intValue();
                if(index>=0) {
                    subcategory = Database.getListOfSubcategory().get(index);
                }
                });
    }

    public static void main(String[] args) {
        launch();
    }
}