package co.edu.manizales.listdeleds.service;

import co.edu.manizales.listdeleds.model.Led;
import co.edu.manizales.listdeleds.model.ListDE;
import co.edu.manizales.listdeleds.model.Node;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
@Service
public class ListDEService {
    private Node head;
    private Node tail;
    private int size;
    private final ListDE listDE;

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
    public void addLedToStart(Led led) {
        Node newNode = new Node();

        if (head == null) {
            // Lista vacía, el nuevo nodo se convierte en la cabeza y la cola
            head = newNode;
            tail = newNode;
        } else {
            // Agregar el nuevo nodo al inicio de la lista
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }

        size++;
    }
    public ListDEService() {
        this.listDE = new ListDE();
    }

    public void addLedToEnd(Led led) {
        addLed(led);
    }

    public void resetList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    private String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter);
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
}



