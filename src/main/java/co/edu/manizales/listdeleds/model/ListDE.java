package co.edu.manizales.listdeleds.model;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

 @Data
public class ListDE {
    private Node head;
    private Node tail;
    private int size;
    private List<Led>leds = new ArrayList<>();

    public void addLedToEnd(Led led) {
        Node newNode = new Node();
        newNode.setData(led);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
            newNode.setPrev(current);
        }
    }

    public void addLed(Led led) {
        Node newNode = new Node();

        if (head == null) {
            // Lista vacía, el nuevo nodo se convierte en la cabeza y la cola
            head = newNode;
            tail = newNode;
        } else {
            // Agregar el nuevo nodo al final de la lista
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }

        size++;
    }

     public List<Led> getAllLEDs() {
         List<Led> leds = new ArrayList<>();
         Node current = head;

         while (current != null) {
             leds.add(current.getData());
             current = current.getNext();
         }

         return leds;
     }
    public void addLedToStart(Led led) {
        Node newNode = new Node();
        newNode.setData(led);

        if (head == null) {
            head = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
    }

    public void resetList() {
        Node current = head;
        while (current != null) {
            current.getData().setState(false);
            current = current.getNext();
        }
    }


    public void lightUpMiddleLeds() throws InterruptedException {
        int size = getSize();
        if (size == 0) {
            return;
        }

        Node current = head;
        Node startNode;
        Node endNode;

        // Calcular el índice del nodo central
        int middleIndex = size / 2;

        // Mover al nodo central
        for (int i = 0; i < middleIndex; i++) {
            current = current.getNext();
        }

        if (size % 2 == 0) {
            // Lista par
            startNode = current;
            endNode = current.getNext();
        } else {
            // Lista impar
            startNode = current.getNext();
            endNode = current.getNext();
        }

        // Reiniciar la hora de encendido y apagado de los LEDs
        resetLedTimes();


        while (startNode != null && endNode != null) {
            // Encender LED y establecer hora de encendido
            startNode.getData().setState(true);
            startNode.getData().setDateOn(getCurrentTime());

            // Esperar 1 segundo
            Thread.sleep(1000);

            // Apagar LED y mantener la hora de apagado anterior
            startNode.getData().setState(false);

            // Encender LED y establecer hora de encendido
            endNode.getData().setState(true);
            endNode.getData().setDateOn(getCurrentTime());

            // Esperar 1 segundo
            Thread.sleep(1000);

            // Apagar LED y mantener la hora de apagado anterior
            endNode.getData().setState(false);

            if (startNode.getNext() == null) {
                // Es el último nodo de la lista, mantener encendido
                startNode.getData().setState(true);
            }

            if (endNode.getNext() == null) {
                // Es el último nodo de la lista, mantener encendido
                endNode.getData().setState(true);
            }

            startNode = startNode.getNext();
            endNode = endNode.getNext();
        }
    }
    private void resetLedTimes() {
        Node current = head;
        while (current != null) {
            current.getData().setDateOn(null);
            current.getData().setDateOff(null);
            current = current.getNext();
        }
    }

    private int getSize() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }

    private String getCurrentTime() {
        // Obtener la hora actual
        LocalDateTime currentTime = LocalDateTime.now();

        // Formatear la hora actual en el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        return formattedTime;
    }

}