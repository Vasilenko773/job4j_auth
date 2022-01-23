package ru.job4j.chat.model;

public class RoomTDO {

    private int id;

    private String name;

    private int[] arrayId;

    public RoomTDO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getArrayId() {
        return arrayId;
    }

    public void setArrayId(int[] arrayId) {
        this.arrayId = arrayId;
    }
}
