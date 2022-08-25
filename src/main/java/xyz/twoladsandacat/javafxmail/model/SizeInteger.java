package xyz.twoladsandacat.javafxmail.model;

public class SizeInteger {

    private int size;

    public SizeInteger(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        if (size <= 0) {
            return "0";
        } else if (size < 1024) {
            return size + "B";
        } else if (size < 1048576) {
            return size / 1024 + "kB";
        } else {
            return size / 1048576 + "MB";
        }
    }
}