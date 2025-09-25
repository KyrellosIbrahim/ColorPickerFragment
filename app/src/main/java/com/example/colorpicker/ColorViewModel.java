package com.example.colorpicker;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;

public class ColorViewModel extends ViewModel {
    private MutableLiveData<LinkedList<String>> colorsLL;

    public ColorViewModel(MutableLiveData<LinkedList<String>> colorsLL) {
        this.colorsLL = colorsLL;
    }

    public LinkedList<String> getColors() {
        if (colorsLL == null) {
            colorsLL = new MutableLiveData<>(new LinkedList<>());
        }
        return colorsLL.getValue();
    }

    public void addColor(String color) {
        LinkedList<String> l = getColors();
        l.add(color);
        colorsLL.setValue(l);
    }

    public void setColors(LinkedList<String> colors) {
        colorsLL.setValue(colors);
    }
}
