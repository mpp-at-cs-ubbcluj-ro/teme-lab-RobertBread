package org.example;

import com.google.common.collect.ImmutableList;
import com.google.common.base.Preconditions;


public class Main {
    public static void main(String[] args) {
        // Creare lista imutabilă folosind Guava
        ImmutableList<String> lista = ImmutableList.of("Apple", "Banana", "Cherry");
        System.out.println("Lista: " + lista);

        // Verificare condiție folosind Preconditions
        String element = "Orange";
        Preconditions.checkArgument(!lista.contains(element), "Elementul %s există deja în listă", element);
        System.out.println("Elementul nu există în listă.");
        boolean rezultat = !lista.contains(element);
    }

}
