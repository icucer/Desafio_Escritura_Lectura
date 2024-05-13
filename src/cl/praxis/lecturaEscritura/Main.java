package cl.praxis.lecturaEscritura;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String nombreCarpeta = "directorio";
        String nombreArchivo = "archivo.txt";

        try {
            crearDirectorioArchivo(nombreCarpeta, nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }

        buscarTexto(new File(nombreCarpeta, nombreArchivo), "Perro");
    }

    public static void crearDirectorioArchivo(String nombreCarpeta, String nombreArchivo) throws IOException {
        // Crear la carpeta
        File carpeta = new File(nombreCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        } else {
            System.out.println("La carpeta ya existe.");
        }

        // Crear el archivo dentro de la carpeta
        File archivo = new File(carpeta, nombreArchivo);
        if (!archivo.exists()) {
            archivo.createNewFile();
        } else {
            System.out.println("El archivo ya existe.");
        }

        // Escribir en el archivo
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("Perro");
        lista.add("Gato");
        lista.add("Juan");
        lista.add("Daniel");
        lista.add("Juan");
        lista.add("Gato");
        lista.add("Perro");
        lista.add("Camila");
        lista.add("Daniel");
        lista.add("Camila");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (String item : lista) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void buscarTexto(File archivo, String texto) {
        // Verificar si el archivo existe
        if (!archivo.exists()) {
            System.out.println("El archivo especificado no existe.");
            return;
        }

        // Contador
        int contador = 0;

        // Leer el archivo y buscar el texto
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] palabras = linea.split("\\s+");
                for (String palabra : palabras) {
                    if (palabra.equals(texto)) {
                        contador++;
                    }
                }
            }
            // Mostrar la cantidad de repeticiones del texto
            System.out.println("Cantidad de repeticiones del texto '" + texto + "' -> " + contador);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}