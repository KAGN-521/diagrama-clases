package Models;

import java.awt.Color;
import java.util.ArrayList;

public class Class {

    // Atributos Principales
    private int id; // Identificador de la clase.
    private String name; // Nombre de la clase.
    private ArrayList<String> attributes; // Array con los atributos de la clase.

    // Atributos de Poscicion
    private int x, y, height, width;

    // Atributos de Aspecto
    private Color color;

    // Constructor de la clase Class.
    public Class(String name, int id, int x, int y) {
        this.id = id;
        this.name = name;
        this.attributes = new ArrayList<String>();

        this.x = x;
        this.y = y;
        this.height = 30; // Ramaño estandar de todos los cuadros clase.
        this.width = 200;

        color = Color.BLACK; // Color estandar de la clase.
    }

    // Metodo para agrandar la altura en caso que se ingrese un nuevo atributo.
    public void updateSize() {
        this.height = height + 30;
    }

    // Metodo que agrega un nuevo atributo a la clase.
    public void addAttribute(String attribute) {
        this.attributes.add(attribute);
        updateSize();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<String> getAttributes() {
        return this.attributes;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

}
