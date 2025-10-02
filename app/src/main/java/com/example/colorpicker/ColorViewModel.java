package com.example.colorpicker;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;

public class ColorViewModel extends ViewModel {
    private MutableLiveData<LinkedList<String>> colorsLL;

    public ColorViewModel() {
        colorsLL = new MutableLiveData<>();
        LinkedList<String> l = new LinkedList<>();
        colorsLL.setValue(l);
    }

    public ColorViewModel(MutableLiveData<LinkedList<String>> colorsLL) {
        this.colorsLL = colorsLL;
    }

    public MutableLiveData<LinkedList<String>> getColors() {
        if (colorsLL == null) {
            colorsLL = new MutableLiveData<>(new LinkedList<>());
        }
        return colorsLL;
    }

    public void addColor(String color) {
        LinkedList<String> l = getColors().getValue();
        l.add(color);
        Log.i("COLORS", l.toString());
        colorsLL.setValue(l);
    }

    public void setColors(LinkedList<String> colors) {
        colorsLL.setValue(colors);
    }
}
