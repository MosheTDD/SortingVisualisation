package me.moshe.sortingvisualisation.ui.controllers;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import me.border.utilities.scheduler.Task;
import me.border.utilities.scheduler.TaskBuilder;

import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SortingVisualisationController implements Initializable {
    @FXML
    public AnchorPane ANCHOR;

    private Map<Integer, Rectangle> rectangleHashMap = new LinkedHashMap<>();
    private final int WIN_WIDTH = 1280;
    private final int BAR_WIDTH = 5;
    private final int NUM_BARS = WIN_WIDTH / BAR_WIDTH;
    private final String REC_COLOR = "#99aab5";
    private int[] a;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        a = randomizeArray();
        createArray(a);
    }

    private void visualizeArray(){
        ANCHOR.getChildren().clear();
        for (int i = 0; i < rectangleHashMap.size(); i++){
            rectangleHashMap.get(i).setFill(Paint.valueOf(REC_COLOR));
            ANCHOR.getChildren().add(rectangleHashMap.get(i));
        }
    }


    private void createArray(int[] a) {
        Rectangle first = new Rectangle(0, 0, BAR_WIDTH, a[0]*20);
        rectangleHashMap.put(0, first);
        TaskBuilder.builder()
                .after(1, TimeUnit.MILLISECONDS)
                .every(20, TimeUnit.MILLISECONDS)
                .task(new TimerTask() {
                    Rectangle oldR = first;
                    private int i = 1;
                    @Override
                    public void run() {
                        if(i < a.length){
                            Platform.runLater(() -> {
                                int recX = getX(oldR);
                                Rectangle newR = new Rectangle(recX+6, 0, BAR_WIDTH, a[i]*20);
                                rectangleHashMap.put(i, newR);
                                oldR = newR;
                                i++;
                                visualizeArray();
                            });
                        } else{
                            cancel();
                            insertionSort(a);
                        }
                    }
                })
                .build();
    }

    private void insertionSort(int a[]) {
        int n = a.length;
        for (int i = 1; i < n; ++i) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                rectangleHashMap.get(j).setFill(Color.RED);
                j = j - 1;
            }
            a[j + 1] = key;
            Platform.runLater(this::visualizeArray);
        }
    }

    private int[] randomizeArray(){
        int[] a = new int[NUM_BARS];
        for (int i = 0; i < a.length; i++){
            int rndNum = (int)(Math.random() * ((36 - 1) + 1) + 1);
            a[i] = rndNum;
        }
        return a;
    }

    public int getX(Rectangle r){
        return (int) r.getX();
    }

    private  <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
