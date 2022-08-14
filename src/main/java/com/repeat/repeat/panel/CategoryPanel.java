package com.repeat.repeat.panel;

import com.repeat.repeat.Category;
import com.repeat.repeat.Configuration;
import com.repeat.repeat.HelloApplication;
import com.repeat.repeat.data.Database;
import com.repeat.repeat.data.SaveAndReadData;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CategoryPanel implements Panel{

    @Override
    public Scene getScene() {
        GridPane gridPane = new GridPane();
        Configuration.gridSettings(gridPane);

        Text information = new Text();
        information.setFont(Font.font(11));
        information.setFill(Color.GREEN);
        Label addCatLabel = new Label("Name of Category: ");
        TextField catTextField = new TextField();
        Button addCategory = new Button("ADD");
        Button back = HelloApplication.getUniqueButton();
        back.setText("Close");
        back.setGraphic(null);

        gridPane.add(information, 1,0);
        gridPane.add(addCatLabel, 1, 1);
        gridPane.add(catTextField, 1,2);
        gridPane.add(addCategory, 1, 3);
        gridPane.add(back, 2,3);

        addCategory.setOnAction(event -> {
            String categoryName = catTextField.getText();
            Category category = new Category(categoryName);
            Database.getListOfAllCategory().add(category);
            information.setText("You have added new category!");
            catTextField.clear();
            SaveAndReadData.saveChanges();
        });

        return new Scene(gridPane, 273, 194);
    }

}
