package com.example.darkchess;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Choice
{

    public static int modeOfHalf = 0;
    public static Stage theChoiceStage;
    public static boolean choiceStageFlage = true;
    @FXML
    void computer(MouseEvent event)
    {
        modeOfHalf = 2;
        theChoiceStage.close();
    }

    @FXML
    void offline(MouseEvent event)
    {
        modeOfHalf = 1;
        theChoiceStage.close();
    }

}
