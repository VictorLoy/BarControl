/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcontrolwidget;

import javafx.application.Application;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author victor
 */
public class BarControlWidget extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BarControl bc1=new BarControl(100,500,250);
        Label lb1=new Label(""+bc1.Default);
        BarControl bc2=new BarControl(100,500,250);
        Label lb2=new Label(""+bc2.Default);
        BarControl bc3=new BarControl(100,500,250);
        Label lb3=new Label(""+bc3.Default);
        lb3.textProperty().bind(bc3.getProperty());
        bc1.getCanvas().addEventHandler(MouseEvent.MOUSE_DRAGGED,new EventHandler<MouseEvent>(){
        public void handle(MouseEvent m){
           Double d=m.getY();
           bc1.change(d);
           lb1.setText(bc1.evaluateValue());
        }
        });
        bc2.getCanvas().addEventHandler(MouseEvent.MOUSE_DRAGGED,new EventHandler<MouseEvent>(){
        public void handle(MouseEvent m){
           Double d=m.getY();
           bc2.change(d);
           lb2.setText(bc2.evaluateValue());
        }
        });
         bc3.getCanvas().addEventHandler(MouseEvent.MOUSE_DRAGGED,new EventHandler<MouseEvent>(){
        public void handle(MouseEvent m){
           Double d=m.getY();
           bc3.change(d);
           bc3.setValue();
           
        }
        });
         
         Slider slider=new Slider();
        slider.setMin(150.0);
        slider.setMax(1000.0);
        slider.setValue(275.0);
        Label label=new Label(""+ slider.getValue());
        slider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String value=String.format("%.1f", newValue);
                label.setText(value);
            }
            
        });
        Button btn=new Button();
        btn.setText("Quit");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("GoodBye");
                primaryStage.close();
            }
        });
        
        HBox hbox = new HBox();
        hbox.getChildren().addAll(bc1,lb1,bc2,lb2,bc3,lb3);
        VBox vbox=new VBox();
        vbox.getChildren().addAll(hbox,slider,label,btn);
        
        Scene scene = new Scene(vbox, 300, 300);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
