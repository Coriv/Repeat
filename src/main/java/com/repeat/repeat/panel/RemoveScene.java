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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class RemoveScene {
    private static Button confirm = new Button("Confirm");
    private static Button cancel = new Button("Cancel");
    private static Text information = new Text();
    private static Text text = new Text();
    private static Stage removeStage = new Stage();
    public static Scene prepareScene() {

        GridPane removeGrid = new GridPane();
        Configuration.gridSettings(removeGrid);

        removeGrid.add(information, 1, 0);
        removeGrid.add(text, 1, 1);
        removeGrid.add(confirm, 1, 2);
        removeGrid.add(cancel, 2, 2);

        information.setFill(Color.RED);
        information.setFont(Font.font(10));

        return new Scene(removeGrid, 330, 100);
    }

    public static void removeCategoryScene(Category category){

        removeStage.setScene(prepareScene());
        information.setText("This operation will remove all subcategory\nand all questions with the category");
        text.setText("Category to remove:      " + category.toString().toUpperCase());
        removeStage.show();

        confirm.setOnAction(event -> {
            Database.getListOfAllCategory().remove(category);
            SaveAndReadData.saveChanges();
            HelloApplication.getInformation().setText("CATEGORY: " + category + "\nhas been removed");
            HelloApplication.getCategoryChoice().setItems(FXCollections.observableArrayList(Database.getListOfAllCategory()));
            HelloApplication.getSubCatChoice().setItems(FXCollections.observableArrayList(category.getSubcategoryList()));
            removeStage.close();
        });

        cancel.setOnAction(event -> removeStage.close());
    }

    public static void removeSubcategoryScene(Category category, Subcategory subcategory) {

        removeStage.setScene(prepareScene());
        information.setText("This operation will remove the subcategory\nand all included questions ");
        text.setText("Subcategory to delete:      " + subcategory.toString().toUpperCase());
        removeStage.show();

        confirm.setOnAction(event -> {
            category.getSubcategoryList().remove(subcategory);
            SaveAndReadData.saveChanges();
            HelloApplication.getInformation().setText("SUBCATEGORY: " + subcategory + "\nhas been removed");
            HelloApplication.getCategoryChoice().setItems(FXCollections.observableArrayList(Database.getListOfAllCategory()));
            HelloApplication.getSubCatChoice().setItems(FXCollections.observableArrayList(category.getSubcategoryList()));
            removeStage.close();
        });

        cancel.setOnAction(event -> removeStage.close());
    }

    public static void removeQuestion(Category category, String question) {
        removeStage.setScene(prepareScene());
        text.setText("Are you sure you want to delete the question?");
        information.setText("");
        removeStage.show();

        confirm.setOnAction(event -> {
            for(Subcategory subcategory : category.getSubcategoryList()) {
                subcategory.getListOfQuestions().remove(question);
            }
            SaveAndReadData.saveChanges();
            removeStage.close();
        });

        cancel.setOnAction(event -> removeStage.close());
    }
}

