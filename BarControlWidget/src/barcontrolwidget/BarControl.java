/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcontrolwidget;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 *
 * @author victor
 */
public class BarControl extends Pane{
    public int width=50;
    public int height=205;//This is 205 so as to get the bar to go to 200,the class works
    public int minValue;
    public int maxValue;
    public Canvas can;
    public GraphicsContext gc;
    public Double yValue;
    public int Default=250;
    private StringProperty hValue=new SimpleStringProperty();//Height Value;
    
    public BarControl(int min,int max,int init){
        minValue=changeValueToScale(min);
        maxValue=changeValueToScale(max);
        Default=changeValueToScale(init);
        System.out.println(Default);
                
        can=new Canvas(width,height);
        gc=can.getGraphicsContext2D();
        setPrefSize(width+5,height+5);
        setStyle("-fx-background-color: black");
        draw();
        getChildren().add(can);
    }
    
    public void  draw(){
        gc.setFill(Color.YELLOW);
        gc.fillRect(5,5,width,Default);
        
    }
    public Canvas getCanvas(){
    return can;
    }

  
    public void change(Double value){
        
        if((value.intValue()<=200)&&(value.intValue()>=0)){//Setting the max and min values so it doesnt go over the bar
        gc.clearRect(0, 0, width,height);
        System.out.println("The y vlaue is :"+value.intValue());
        gc.fillRect(5, value.intValue(), can.getWidth(),can.getHeight()-value.intValue());
        yValue=value;
        }else if(value.intValue()>200){
            value=200.0;
            gc.clearRect(0, 0, width,height);
          // System.out.println("The y vlaue is :"+value.intValue());
            gc.fillRect(5, value.intValue(), can.getWidth(),can.getHeight()-value.intValue());
            yValue=value;
        }else{
            value=0.0;
            gc.clearRect(0, 0, width,height);
           // System.out.println("The y vlaue is :"+value.intValue());
            gc.fillRect(5, value.intValue(), can.getWidth(),can.getHeight()-value.intValue());
            yValue=value;
          }
        }
    public String evaluateValue(){
     String str;
     /* Whatever the y value is , i subtract the hieght then 5 from it then i divide it by 190/500=0.38.Then 
     i add 100 to the value
     */
     Double d=(height-yValue-5);///0.38;//I have calculated the number for change
     d=(d*2)+100;
     str=String.format("%.1f", d);
     return str;
    }
    public int changeValueToScale(int value){//Changes the values passed in as reference to the scale of the yellow bar
        value=value-100;
        value=value/2;
        value=value+5+height;
        return value;
    }
    public void setValue(){
        hValue.setValue(evaluateValue());
    }
    public StringProperty getProperty(){
      
        return hValue;
    }
    

    /**
     * @param args the command line arguments
     */
    
}
