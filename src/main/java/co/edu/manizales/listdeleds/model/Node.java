package co.edu.manizales.listdeleds.model;

import lombok.Data;

@Data
public class Node {
    private Led led;
    private Node prev;
    private Node next;
}