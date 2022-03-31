package com.example.proyectofinal;

import java.net.URL;

import Model.Automata;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    RadioButton rdFor;
    @FXML
    RadioButton rdEach;
    @FXML
    TextField entrada;
    @FXML
    Button iniciar ;
    @FXML
    TextArea proceso;
    @FXML
    Label mensaje;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        ToggleGroup tg = new ToggleGroup();
        this.rdFor.setToggleGroup(tg);
        this.rdEach.setToggleGroup(tg);
    }

    public void start(ActionEvent event){

        boolean select = false;
        this.reset();
        Automata automata = new Automata();

        if(this.rdFor.isSelected())
        {
            automata.mapFor();
            select = true;
        }
        if(this.rdEach.isSelected())
        {
            automata.mapEach();
            select = true;
        }


        int i = 0;
        boolean bandera = true;
        String entrada = this.entrada.getText();
        String[] cadena = entrada.split("\s");

        if(!entrada.isEmpty() && select)
        {
            do{

                bandera = automata.pushDown(cadena[i], this.proceso);
                if(automata.si)
                {
                    i++;
                }

            }while(bandera && !automata.getPila().isEmpty());

            if(automata.getPila().isEmpty())
            {
                this.mensaje.setText("Cadena aceptada :)");
            }
            else
            {
                this.mensaje.setText("Cadena rechazada :(");
            }

        }

    }

    public void reset()
    {
        this.proceso.clear();
        this.mensaje.setText("");
    }
}