package sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink register;

    @FXML
    void initialize() {




        register.setOnAction(event -> {
            register.getScene().getWindow().getOnCloseRequest();//прячем наше первое окно Взамен следующей
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/registerFile.fxml")); // позволяет указать расположение  нужного нам файла

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Parent root = loader.getRoot(); //путь к необходимой сцены передали переменной
            Stage stage = new Stage();
            stage.setTitle("Регистрация");
            stage.setScene(new Scene(root ));//указываем необходимую нам сцену
            register.getScene().getWindow().hide();
            //stageLogin.close();

            stage.show();//показать и подаждать пока появится сцена

        });

        loginButton.setOnAction(event -> {
            boolean zanyat = true;
            boolean nePravilno = false;
            try (Scanner scan = new Scanner(new File("C:\\Users\\renat\\Desktop\\Evropa\\input.txt"))) {
                while(scan.hasNextLine() && zanyat){
                    String[] logon = scan.nextLine().split(",");
                    if (logon[0].equals(textField1.getText()) && logon[1].equals(textField2.getText())) {
                        zanyat = false;
                        nePravilno = false;
                    }else {
                        nePravilno = true;
                    }

                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }


            if (nePravilno){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                System.out.println("Пароль или логин неверно введены");

                alert.setTitle("Ошибка");

                alert.setHeaderText("Сообщение об ошибке");

                alert.setContentText("Проверьте введенные логин и пароль");

                alert.showAndWait();
            }else {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                System.out.println("Вход в учетную запись прошла успешно !!!Мои поздравления *****");

                alert1.setTitle("Ты супер!");

                alert1.setHeaderText("Сообщение об успехе");

                alert1.setContentText("Вход в учетную запись прошла успешно !!!Мои поздравления *****");

                alert1.showAndWait();

            }
        });
    }
}
