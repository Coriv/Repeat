package com.repeat.repeat.panel;

import com.repeat.repeat.Category;
import com.repeat.repeat.Configuration;
import com.repeat.repeat.HelloApplication;
import com.repeat.repeat.Subcategory;
import com.repeat.repeat.data.Database;
import com.repeat.repeat.data.SaveAndReadData;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
public class SubcategoryPanel implements Panel{
    Category category = null;

    @Override
    public Scene getScene(){
        GridPane gridPane = new GridPane();
        Configuration.gridSettings(gridPane);

        ChoiceBox choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(Database.getListOfAllCategory()));
        Text information = new Text();
        information.setFont(Font.font(11));
        Label addSubcLabel = new Label("Name of subcategory:");
        TextField subcTextField = new TextField();
        Button add = new Button("Add");
        Button back = HelloApplication.getUniqueButton();
        back.setText("Close");
        back.setGraphic(null);


        gridPane.add(information, 1, 0);
        gridPane.add(choiceBox, 1 ,1);
        gridPane.add(addSubcLabel, 1, 2);
        gridPane.add(subcTextField, 1,3);
        gridPane.add(add, 1,4);
        gridPane.add(back, 2, 4);

        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            category = Database.getListOfAllCategory().get(newValue.intValue());
        });

        add.setOnAction(event -> {
            if(category != null) {
            String subcName = subcTextField.getText();
            Subcategory subcategory = new Subcategory(subcName);
            category.getSubcategoryList().add(subcategory);
            HelloApplication.getUniqueButton();
            information.setFill(Color.GREEN);
            information.setText("You have added new subcategory!");
            subcTextField.clear();
            SaveAndReadData.saveChanges();
            } else {
            subcTextField.clear();
            information.setFill(Color.RED);
            information.setText("Chose category or create a new one!");
            }});

        return new Scene(gridPane,273,194);

    }
}
