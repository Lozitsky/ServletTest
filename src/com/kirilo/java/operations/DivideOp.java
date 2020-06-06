package com.kirilo.java.operations;

public class DivideOp extends AbstractOperation {
    public DivideOp(double one, double two) {
        super(one, two);
    }

    @Override
    public double calculate() {
        return getOne() / getTwo();
    }
}
