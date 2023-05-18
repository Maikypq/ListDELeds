package co.edu.manizales.listdeleds.model;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
public class ListDE {

    private Node head;
    private int size;
    private Node tail;
    private List<Led> leds = new ArrayList<>();

    public void addLed(Led led) {
        if (head != null) {
            Node newNode = new Node(led);
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            newNode.setPrev(temp);
        } else {
            head = new Node(led);
        }
        size++;
    }
    public void addLedToEnd(Led led) {
        if (head == null) {
            head = new Node(led);
        } else {
            Node newNode = new Node(led);
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
            newNode.setPrev(current);
        }
        size++;
    }

    public List<Led> print() {
        leds.clear();
        if (head != null) {
            Node temp = head;
            while (temp != null) {
                leds.add(temp.getData());
                temp = temp.getNext();
            }
        }
        return leds;
    }

    public void addLedToStart(Led led)  {
        Node newNode = new Node(led);
        if (head != null) {
            head.setPrev(newNode);
            newNode.setNext(head);
        }
        head = newNode;
        size++;
    }


    public  void lightUpMiddleLeds(){
        if (head != null) {
            Node temp = head;
            int pasos = 1;
            int medium;
            if ((size%2)!=0){
                medium = (size / 2) + 1;
                while (temp!= null){

                    if (pasos == medium){
                        Node next = temp;
                        temp.getData().setState(true);
                        temp.getData().setDateOn(LocalTime.from(LocalDateTime.now()));

                        while (next.getNext() != null){

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            temp.getData().setState(false);
                            temp.getData().setDateOff(LocalTime.from(LocalDateTime.now()));
                            next.getData().setState(false);
                            next.getData().setDateOff(LocalTime.from(LocalDateTime.now()));

                            temp = temp.getPrev();
                            next= next.getNext();

                            temp.getData().setState(true);
                            temp.getData().setDateOn(LocalTime.from(LocalDateTime.now()));
                            next.getData().setState(true);
                            next.getData().setDateOn(LocalTime.from(LocalDateTime.now()));



                        }
                    }
                    pasos++;
                    temp= temp.getNext();


                }




            } else{
                medium = size/2;

                while (temp!= null){
                    if (pasos == medium){
                        Node Next = temp.getNext();
                        temp.getData().setState(true);
                        temp.getData().setDateOn(LocalTime.from(LocalDateTime.now()));
                        Next.getData().setState(true);
                        Next.getData().setDateOn(LocalTime.from(LocalDateTime.now()));

                        while (Next.getNext() != null) {

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            temp.getData().setState(false);
                            temp.getData().setDateOff(LocalTime.from(LocalDateTime.now()));
                            Next.getData().setState(false);
                            Next.getData().setDateOff(LocalTime.from(LocalDateTime.now()));

                            temp = temp.getPrev();
                            Next = Next.getNext();

                            temp.getData().setState(true);
                            temp.getData().setDateOn(LocalTime.from(LocalDateTime.now()));
                            Next.getData().setState(true);
                            Next.getData().setDateOn(LocalTime.from(LocalDateTime.now()));


                        }
                    }
                    pasos++;
                    temp= temp.getNext();

                }

            }

        }

    }
    public void resetList() {
        Node temp = head;
        while (temp != null){
            temp.getData().setState(false);
            temp.getData().setDateOff(null);
            temp.getData().setDateOn(null);

            temp = temp.getNext();
        }
    }
}
