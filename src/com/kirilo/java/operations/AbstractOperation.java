package com.kirilo.java.operations;

public abstract class AbstractOperation implements Operation {
    public double getOne() {
        return one;
    }

    public double getTwo() {
        return two;
    }

    private double one;
    private double two;

    public AbstractOperation(double one, double two) {
        this.one = one;
        this.two = two;
    }
}
