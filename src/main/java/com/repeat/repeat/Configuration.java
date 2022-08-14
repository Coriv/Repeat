package com.repeat.repeat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Configuration {
    private static final Image backgroundImage = new Image("File:/Users/sebastianboron/Repeat/src/main/resources/kartka.jpg");

    public static void gridSettings(GridPane gridPane) {
        Background background = getBackground();
        gridPane.setBackground(background);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(15, 15, 15, 15));
    }

    public static void setToolBarSettings(ToolBar toolBar) {
        toolBar.setBackground(getBackground());
    }

    private static Background getBackground() {
        BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, false);
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, bgSize);
        Background background = new Background(bgImage);
        return background;
    }
}
