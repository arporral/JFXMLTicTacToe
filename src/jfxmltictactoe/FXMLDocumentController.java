/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxmltictactoe;

import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author arpor
 */
public class FXMLDocumentController implements Initializable {

    String jugador;
    String[] jugadas = new String[9];
    boolean pantallaCompleta;
    
    @FXML
    private Label player;
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // expresión lambda para inicializar el array de jugadas.
        Arrays.setAll(jugadas, i -> ""); 
                                
        pantallaCompleta = false;
        jugador = "X";
    }    

    @FXML
    private void b1(ActionEvent event) {
         marcarJugada(0,b1);
    }

    @FXML
    private void b2(ActionEvent event) {
        marcarJugada(1,b2);
    }

    @FXML
    private void b3(ActionEvent event) {
        marcarJugada(2,b3);
    }

    @FXML
    private void b4(ActionEvent event) {
        marcarJugada(3,b4);
    }
    
    @FXML
    private void b5(ActionEvent event) {
        marcarJugada(4,b5);
    }

    @FXML
    private void b6(ActionEvent event) {
        marcarJugada(5,b6);
    }

    @FXML
    private void b7(ActionEvent event) {
        marcarJugada(6,b7);
    }

    @FXML
    private void b8(ActionEvent event) {
        marcarJugada(7,b8);
    }
    
    @FXML
    private void b9(ActionEvent event) {
        marcarJugada(8,b9);
    }
    
    private void marcarJugada(int i, Button fxmlButton) {
        if (pantallaCompleta) {          
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Pantalla Completa");
            alert.setHeaderText("");
            alert.setContentText("No hay más jugadas disponibles. Desea volver a empezar?");

            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == ButtonType.OK) {
                Stage stage = (Stage) fxmlButton.getScene().getWindow();
                stage.close();
                try {
                    restart(stage);
                } catch (Exception ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        } else {
            if (jugadas[i].equals("")) {
                jugadas[i] = jugador;
                fxmlButton.setText(jugadas[i]);
                calcularGanador(fxmlButton);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Jugada incorrecta");
                alert.setContentText("Casilla ya ocupada!");
                alert.showAndWait();
            }
        }
    }

    private void calcularGanador(Button fxmlButton) {
        String ganador = "";

        if (jugadas[0].equals(jugadas[1]) && jugadas[0].equals(jugadas[2]) && !jugadas[0].equals("")) {
            ganador = jugadas[0];
        } else if (jugadas[3].equals(jugadas[4]) && jugadas[3].equals(jugadas[5]) && !jugadas[3].equals("")) {
            ganador = jugadas[3];
        } else if (jugadas[6].equals(jugadas[7]) && jugadas[6].equals(jugadas[8]) && !jugadas[6].equals("")) {
            ganador = jugadas[6];
        } else if (jugadas[0].equals(jugadas[3]) && jugadas[0].equals(jugadas[6]) && !jugadas[0].equals("")) {
            ganador = jugadas[0];
        } else if (jugadas[1].equals(jugadas[4]) && jugadas[1].equals(jugadas[7]) && !jugadas[1].equals("")) {
            ganador = jugadas[1];
        } else if (jugadas[2].equals(jugadas[5]) && jugadas[2].equals(jugadas[8]) && !jugadas[2].equals("")) {
            ganador = jugadas[2];
        } else if (jugadas[0].equals(jugadas[4]) && jugadas[0].equals(jugadas[8]) && !jugadas[0].equals("")) {
            ganador = jugadas[0];
        } else if (jugadas[2].equals(jugadas[4]) && jugadas[2].equals(jugadas[6]) && !jugadas[2].equals("")) {
            ganador = jugadas[2];
        }

        if (ganador.equals("X")) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Ganó jugador 1");
            alert.setHeaderText("");
            alert.setContentText("El jugador 1 ha ganado. Desea volver a empezar?");

            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == ButtonType.OK) {
                Stage stage = (Stage) fxmlButton.getScene().getWindow();
                stage.close();  
                try {
                    restart(stage);
                } catch (Exception ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                pantallaCompleta = true;
                return;
            }
        } else if (ganador.equals("O")) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Ganó jugador 2");
            alert.setHeaderText("");
            alert.setContentText("El jugador 2 ha ganado. Desea volver a empezar?");

            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == ButtonType.OK) {
                Stage stage = (Stage) fxmlButton.getScene().getWindow();
                stage.close(); 
                try {
                    restart(stage);
                } catch (Exception ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                pantallaCompleta = true;
                return;
            }
        }

        pantallaCompleta=true;
        
        for (int j = 0; j < 9; j++) {
            if (jugadas[j].equals("")) {
                pantallaCompleta = false;
            }
        }
        
        if (pantallaCompleta) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Pantalla completa");
            alert.setContentText("No hay más jugadas disponibles!");
            alert.showAndWait();            
            return;
        }
        if (jugador.equals("X")) {
            jugador = "O";
            player.setText("JUGADOR 2");
        } else {
            jugador = "X";
            player.setText("JUGADOR 1");
        }
    }
    
    public void restart(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe"); 
        stage.show();
    }
}
