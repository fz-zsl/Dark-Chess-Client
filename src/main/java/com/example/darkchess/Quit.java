package com.example.darkchess;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class Quit
{
    @FXML
    void closing(MouseEvent event)
    {
        Platform.exit();
    }

}

