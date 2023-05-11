package co.edu.manizales.listdeleds.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class ListDE {
    private Node head;

    public void addLedToEnd(Led led) {
        Node newNode = new Node();
        newNode.setLed(led);

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

    public void addLedToStart(Led led) {
        Node newNode = new Node();
        newNode.setLed(led);

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
            current.getLed().setState(false);
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
            startNode.getLed().setState(true);
            startNode.getLed().setDateOn(getCurrentTime());

            // Esperar 1 segundo
            Thread.sleep(1000);

            // Apagar LED y mantener la hora de apagado anterior
            startNode.getLed().setState(false);

            // Encender LED y establecer hora de encendido
            endNode.getLed().setState(true);
            endNode.getLed().setDateOn(getCurrentTime());

            // Esperar 1 segundo
            Thread.sleep(1000);

            // Apagar LED y mantener la hora de apagado anterior
            endNode.getLed().setState(false);

            if (startNode.getNext() == null) {
                // Es el último nodo de la lista, mantener encendido
                startNode.getLed().setState(true);
            }

            if (endNode.getNext() == null) {
                // Es el último nodo de la lista, mantener encendido
                endNode.getLed().setState(true);
            }

            startNode = startNode.getNext();
            endNode = endNode.getNext();
        }
    }
    private void resetLedTimes() {
        Node current = head;
        while (current != null) {
            current.getLed().setDateOn(null);
            current.getLed().setDateOff(null);
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