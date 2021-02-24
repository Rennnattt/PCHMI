package sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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


public class Registration {
    boolean zanyat = true;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textField3;

    @FXML
    private TextField textField4;

    @FXML
    private Button RegButton;

    @FXML
    void initialize() throws IOException {
        RegButton.setOnAction(event -> {
            try (Scanner scan = new Scanner(new File("C:\\Users\\renat\\Desktop\\Evropa\\input.txt"))) {
                while (scan.hasNextLine() && zanyat) {
                    String[] logon = scan.nextLine().split(",");
                    if (logon[0].equals(textField3.getText())) {
                        zanyat = false;
                    }
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (zanyat) {
                String data = textField3.getText() + "," + textField4.getText() + "\n";
                OutputStream os = null;
                try {
                    //в конструкторе FileOutputStream используем флаг true, который обозначает обновление содержимого файла
                    os = new FileOutputStream(new File("C:\\Users\\renat\\Desktop\\Evropa\\input.txt"), true);
                    os.write(data.getBytes(), 0, data.length());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    System.out.println("Создание учетной записи прошла успешно !!!Мои поздравления *****");

                    alert.setTitle("Ты супер!");

                    alert.setHeaderText("Сообщение об успехе");

                    alert.setContentText("Создание учетной записи прошла успешно !!!Мои поздравления *****");

                    alert.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                RegButton.getScene().getWindow().getOnCloseRequest();//прячем наше первое окно Взамен следующей
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/loginFile.fxml")); // позволяет указать расположение  нужного нам файла

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Parent root = loader.getRoot(); //путь к необходимой сцены передали переменной
                Stage stage = new Stage();
                stage.setTitle("Регистрация");
                stage.setScene(new Scene(root));//указываем необходимую нам сцену
                RegButton.getScene().getWindow().hide();
                //stageLogin.close();

                stage.close();//показать и подаждать пока появится сцена

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                System.out.println("Логин занят");

                alert.setTitle("Ошибка");

                alert.setHeaderText("Сообщение об ошибке");

                alert.setContentText("Логин занят");

                alert.showAndWait();


            }

        });

    }
}