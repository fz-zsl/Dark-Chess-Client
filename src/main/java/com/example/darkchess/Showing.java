package com.example.darkchess;

import javafx.scene.control.Alert;

public class Showing
{
    public static void Info(String str)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(str);
        alert.show();
    }

    public static void Alarm(String str)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(str);
        alert.show();
    }
}
