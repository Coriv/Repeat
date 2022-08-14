package com.repeat.repeat.panel;

import com.repeat.repeat.Category;
import com.repeat.repeat.Configuration;
import com.repeat.repeat.HelloApplication;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import java.util.List;
import java.util.Random;
public class RepeatPanel implements Panel {

    private final static Image qAndAImg = new Image("File:/Users/sebastianboron/Repeat/src/main/resources/question.png");
    private final static Image leftArrowImg = new Image("File:/Users/sebastianboron/Repeat/src/main/resources/left-arrow.png");
    private final static Image rightArrowImg = new Image("File:/Users/sebastianboron/Repeat/src/main/resources/right-arrow.png");
    private final static Image randomImg = new Image("File:/Users/sebastianboron/Repeat/src/main/resources/random.png");
    private final static Image removeImg = new Image("File:/Users/sebastianboron/Repeat/src/main/resources/basket.png");
    private final static Image closeImg = new Image("File:/Users/sebastianboron/Repeat/src/main/resources/close.png");
    private int number = 0;
    private Category category;
    private String[] data;
    private List<String> listOfQuestion;
    private TextField questionArea = new TextField();
    private TextArea answerArea = new TextArea();
    private Button showAnswer = new Button("", new ImageView(qAndAImg));
    private Button leftArrow = new Button("", new ImageView(leftArrowImg));
    private Button rightArrow = new Button("", new ImageView(rightArrowImg));
    private Button random = new Button("", new ImageView(randomImg));
    private Button remove = new Button("", new ImageView(removeImg));
    private Button close;

    public RepeatPanel(Category category, List<String> list) {
        this.listOfQuestion = list;
        this.category = category;
    }

    public void displayQuestion(int i) {
        data = listOfQuestion.get(i).split("/");
        questionArea.setText(data[0]);
    }
    public void displayAnswer(int i) {
        answerArea.setText(data[1]);
    }

    @Override
    public Scene getScene() {
        GridPane gridPane = new GridPane();
        close = HelloApplication.getUniqueButton();
        close.setGraphic(new ImageView(closeImg));
        close.setText("");
        ToolBar toolBar = new ToolBar(showAnswer, leftArrow, rightArrow, random, remove, close);
        Configuration.setToolBarSettings(toolBar);
        showAnswer.setDefaultButton(true);

        questionArea.setEditable(false);
        answerArea.setEditable(false);
        answerArea.setWrapText(true);
        answerArea.setPrefColumnCount(24);
        ScrollPane scp = new ScrollPane(answerArea);
        scp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        gridPane.add(questionArea, 0 ,0);
        gridPane.add(scp, 0, 1);
        gridPane.add(toolBar, 0, 2);
        Configuration.gridSettings(gridPane);

        setListener();
        toolTip();
        System.out.println(listOfQuestion.size());

        Scene scene = new Scene(gridPane, 373, 294);
        displayQuestion(number);
        return scene;
    }

    public void setListener() {
        showAnswer.setOnAction(event -> {
            displayAnswer(number);
        });

        leftArrow.setOnAction(event -> {
            if(number > 0) {
                number--;
                displayQuestion(number);
                answerArea.clear();
            }

        });

        rightArrow.setOnAction(event -> {
            if(number < listOfQuestion.size() -1) {
                number++;
                displayQuestion(number);
                answerArea.clear();
            }
        });

        random.setOnAction(event -> {
            number = new Random().nextInt(listOfQuestion.size());
            displayQuestion(number);
            answerArea.clear();
        });

        remove.setOnAction(event -> {
            RemoveScene.removeQuestion(category, listOfQuestion.get(number));
        });
    }

    public void toolTip() {
        showAnswer.setTooltip(new Tooltip("Show the answer"));
        rightArrow.setTooltip(new Tooltip("Next Question"));
        leftArrow.setTooltip(new Tooltip("Previous question"));
        random.setTooltip(new Tooltip("Random question"));
        remove.setTooltip(new Tooltip("Remove the question"));
        close.setTooltip(new Tooltip("Back to Main Menu"));
    }
}
