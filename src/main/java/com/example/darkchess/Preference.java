package com.example.darkchess;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Preference
{
    public static double time = 0.1;
    public static double timeS = 0.1;
    public static String version = "v 2.6.7";
    public static double volume = 0.3;
    public double volumeS = 0.3;
    public static double volumeOfFlip = 2;
    public static Boolean soundSwitch = false;
    public Boolean soundSwitchS = false;
    public static Boolean chessSound = false;
    public Boolean chessSoundS = false;
    public static Boolean endSound = false;
    public static Boolean gamingSwitch = false;
    public Boolean gamingSwitchS = false;
    public static String pictureAddressUse = "D:/backgrounds/1.jpg";
    private String pictureAddressUseS = "D:/backgrounds/1.jpg";
    public static String headAddressUse = "D:/backgrounds/hb2.jpg";
    private String headAddressUseS = "D:/backgrounds/hb2.jpg";

    @FXML
    private TextField backAddress;

    @FXML
    private CheckBox backSoundButton;

    @FXML
    private CheckBox chessSoundButton;

    @FXML
    private CheckBox gamingSwitchButton;

    @FXML
    private TextField headAddress;

    @FXML
    private TextField timeField;

    @FXML
    private TextField volumeText;

    @FXML
    void setTime(KeyEvent event)
    {
        timeS = Double.parseDouble(timeField.getText());
    }


    @FXML
    void setBackGround(KeyEvent event)
    {
        pictureAddressUseS = backAddress.getText().trim();
        if(pictureAddressUseS.isEmpty())
            pictureAddressUseS = "D:/backgrounds/1.jpg";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pictureAddressUseS.length(); i++)
        {
            if(pictureAddressUseS.charAt(i) == '\\')
                sb.append('/');
            else if(pictureAddressUseS.charAt(i) == '\"')
                continue;
            else
                sb.append(pictureAddressUseS.charAt(i));
        }
        pictureAddressUseS = sb.toString();
        // TODO: 2022/12/11
    }

    @FXML
    void setBackSound(MouseEvent event)
    {
        if (backSoundButton.isSelected())
            soundSwitchS = true;
        else
            soundSwitchS = false;

    }

    @FXML
    void setChessSound(MouseEvent event)
    {
        if (chessSoundButton.isSelected())
            chessSoundS = true;
        else
            chessSoundS = false;
    }

    @FXML
    void setHead(KeyEvent event)
    {
        headAddressUseS = headAddress.getText().trim();
        if(headAddressUseS.isEmpty())
            headAddressUseS = "D:/backgrounds/hb2.jpg";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < headAddressUseS.length(); i++)
        {
            if(headAddressUseS.charAt(i) == '\\')
                sb.append('/');
            else if(headAddressUseS.charAt(i) == '\"')
                continue;
            else
                sb.append(headAddressUseS.charAt(i));
        }
        headAddressUseS = sb.toString();
        // TODO: 2022/12/11
    }

    @FXML
    void setSwitch(MouseEvent event)
    {
        if (gamingSwitchButton.isSelected())
            gamingSwitchS = true;
        else
            gamingSwitchS = false;
    }

    @FXML
    void setVolume(KeyEvent event)
    {
        volumeS = Double.parseDouble(volumeText.getText());
    }

    @FXML
    void setPreference(MouseEvent event)
    {
        time = timeS;
        pictureAddressUse = pictureAddressUseS;
        chessSound = chessSoundS;
        headAddressUse = headAddressUseS;
        gamingSwitch = gamingSwitchS;
        if(soundSwitch == soundSwitchS)
        {
            if(volumeS != volume)
            {
                volume = volumeS;
                InitializationApplication.startMusic();
            }
            StartPage.thePreferenceStage.close();
            return;
        }
        soundSwitch = soundSwitchS;
        if (soundSwitch)
        {
            InitializationApplication.startMusic();
        }
        else
        {
            InitializationApplication.mediaPlayerFirst.pause();
        }
        StartPage.thePreferenceStage.close();
        firstDet = true;
    }
    private static boolean firstDet = true;
    @FXML
    void startUp(MouseEvent event)
    {
        if(firstDet && !StartPage.preferenceBoolean)
        {
            backSoundButton.setSelected(soundSwitch);
            chessSoundButton.setSelected(chessSound);
            gamingSwitchButton.setSelected(gamingSwitch);
            firstDet = false;
        }
    }


}




