package com.kirilo.java.operations;

public class MultiplyOp extends AbstractOperation {
    public MultiplyOp(double one, double two) {
        super(one, two);
    }

    @Override
    public double calculate() {
        return getOne() * getTwo();
    }
}
