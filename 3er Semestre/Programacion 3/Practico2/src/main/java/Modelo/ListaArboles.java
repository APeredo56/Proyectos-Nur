package Modelo;

import Ventana.PanelPrincipal;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ListaArboles extends ArrayList<Arbol> {

    private PropertyChangeSupport cambios;

    public ListaArboles(){this.cambios = new PropertyChangeSupport(this);}

    public void addObserver (PanelPrincipal observer){
        cambios.addPropertyChangeListener(observer);
    }

    public void agregarArbol(Arbol arbol){
        this.add(arbol);
        cambios.firePropertyChange("AGREGAR", null, this.size());
    }
}
