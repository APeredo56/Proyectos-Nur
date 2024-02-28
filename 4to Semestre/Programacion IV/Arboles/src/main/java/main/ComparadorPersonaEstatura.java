package main;

import java.util.Comparator;

public class ComparadorPersonaEstatura implements Comparator<Persona> {
    @Override
    public int compare(Persona o1, Persona o2) {
        return o1.getEstatura() - o2.getEstatura();
    }
}