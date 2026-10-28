package es.fplumara.dam.ad.examen;

import es.fplumara.dam.ad.examen.modelo.Anuncio;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Principal {

    //Rutas
    public static final Path ANUNCIOS = Paths.get("datos", "anuncios.csv");

    //Formato CSV, separado por punto y coma con 9 campos
    public static final String separador = ";";
    public static final int NUMERO_DE_CAMPOS = 9;

    //Leer fichero línea a línea
    public static void leerFichero(Path ruta, int maximo) throws IOException {
        int contador = 0;
        try (BufferedReader lector = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
            String linea =  lector.readLine(); //Lee una linea y no hace nada con ella, importante para saltarse la cabecera!!!!
            while (linea != null) {
                contador++;
                if (contador < maximo) {
                    System.out.println(" " + contador + ": " + linea);
                }
                linea = lector.readLine();
            }
        }
        System.out.println("Lineas en total: " + contador);
    }

    //Separar por campos
    public static void separarPorCampos() throws IOException {
        try (BufferedReader lector = Files.newBufferedReader(ANUNCIOS, StandardCharsets.UTF_8)) {
            String cabecera = lector.readLine();
            String[] nombres = cabecera.split(separador);
            System.out.println("La cabecera tiene " + nombres.length + " campos");
            for (int i = 0; i < nombres.length; i++) {
                System.out.println(" campos[" + i + "]= " + nombres[i]);
                String primero = lector.readLine();
                String[] campos =  primero.split(separador);
                System.out.println("Primer anuncio " + campos[1] + ", de " +  campos[2] + " (" + campos[4] + ")");
            }
        }
    }

    //Convertir una linea a un objeto
    public static Anuncio convertirAObjeto(String linea){

    }


    public static void main(String[] args) throws IOException {

        try {
            leerFichero(ANUNCIOS, 100);
        } catch (IOException e) {
            System.out.println("Error al leer fichero");
        }

        System.out.println("Separar");
        separarPorCampos();
    }
}
