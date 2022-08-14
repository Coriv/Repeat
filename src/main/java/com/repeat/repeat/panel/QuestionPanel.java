package com.repeat.repeat.panel;

import com.repeat.repeat.Category;
import com.repeat.repeat.Configuration;
import com.repeat.repeat.HelloApplication;
import com.repeat.repeat.Subcategory;
import com.repeat.repeat.data.SaveAndReadData;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class QuestionPanel implements Panel {
    private static final String QUESTION = "write your question here";
    private static final String ANSWER = "write your answer here";
    private Category category;
    private Subcategory subcategory;

    public QuestionPanel(Category category, Subcategory subcategory) {
        this.category = category;
        this.subcategory = subcategory;
    }

    @Override
    public Scene getScene() {
        GridPane gridPane = new GridPane();
        Configuration.gridSettings(gridPane);

        Text information = new Text();
        information.setFont(Font.font(11));
        information.setFill(Color.GREEN);
        Button add = new Button("ADD");
        Button cancel = HelloApplication.getUniqueButton();
        cancel.setText("Cancel");
        cancel.setGraphic(null);
        TextField question = new TextField(QUESTION);
        TextArea answer = new TextArea(ANSWER);
        ToolBar toolBar = new ToolBar(add, cancel);
        Configuration.setToolBarSettings(toolBar);

        answer.setWrapText(true);
        answer.setPrefColumnCount(26);
        ScrollPane scp = new ScrollPane(answer);
        scp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        gridPane.add(information, 1, 0);
        gridPane.add(question, 1, 1);
        gridPane.add(scp, 1, 2);
        gridPane.add(toolBar, 1, 3);

        add.setOnAction(event -> {
            if((!question.getText().equals(QUESTION)) && (!answer.getText().equals(ANSWER))) {
                String result = question.getText() + "/" + answer.getText();
                subcategory.getListOfQuestions().add(result);
                Subcategory subCat = category.getSubcategoryList().get(0);
                if(!subcategory.equals(subCat)) {
                    subCat.getListOfQuestions().add(result);
                }
                information.setText("You have added a new question!");
            } else {
                information.setFill(Color.RED);
                information.setText("Fill the question and answer fields");
            }
            question.setText(QUESTION);
            answer.setText(ANSWER);
            SaveAndReadData.saveChanges();
        });

        question.setOnMouseClicked(event -> {
            if(question.getText().equals(QUESTION)) {
                question.clear();
                    }});

        answer.setOnMouseClicked(event -> {
            if(answer.getText().equals(ANSWER)) {
                answer.clear();
            }});

        return new Scene(gridPane, 373, 294);
    }
}
