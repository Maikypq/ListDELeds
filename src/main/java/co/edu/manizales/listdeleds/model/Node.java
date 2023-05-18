package co.edu.manizales.listdeleds.model;

import lombok.Data;

@Data
public class Node {
    private Led data;
    private Node prev;
    private Node next;

    public Node(Led data){
        this.data=data;
    }
}