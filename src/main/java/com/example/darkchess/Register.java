package com.example.darkchess;

import fileOperations.LoginInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import oop.LoadUserException;

public class Register
{

    @FXML
    private MenuItem contactUs;

    @FXML
    private Button register;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem preference;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private TextField textFieldUser;

    @FXML
    private MenuItem version;

    private static String account;
    private static String password;

    @FXML
    void cContactUs(ActionEvent event)
    {

    }

    @FXML
    void cRegister(MouseEvent event)
    {
        try
        {
            if(password.length() < 6)
            {
                Showing.Alarm("经过算法分析，您的密码强度过低！");
                return;
            }
            LoginInterface.userRegister(account,password);
            account = null;
            password = null;
        }
        catch(Exception e)
        {
            if (e instanceof LoadUserException)
            {
                Showing.Alarm("用户已经存在！");
                return;
            }
        }
        StartPage.theRegisterStage.close();
        InitializationApplication.theStartPage.show();
        Showing.Info("用户注册成功！");

    }

    private Boolean isSame(String string)
    {
        // TODO: 2022/12/3
        int count = 1;
        for(char a: string.toCharArray())
        {
            for (int i = count; i < string.toCharArray().length; i++)
            {
                if(a == string.charAt(i))
                    return true;
            }
            count++;
        }
        return false;
    }

    @FXML
    void cLogOut(ActionEvent event)
    {
        StartPage.theRegisterStage.close();
        InitializationApplication.theStartPage.show();
    }

    @FXML
    void cPreference(ActionEvent event)
    {

    }

    @FXML
    void cVersion(ActionEvent event)
    {

    }

    @FXML
    void onKeyTypedAccount(KeyEvent event)
    {
        account = textFieldUser.getText();
    }

    @FXML
    void onKeyTypedPassword(KeyEvent event)
    {
        password = textFieldPassword.getText();
    }

}
