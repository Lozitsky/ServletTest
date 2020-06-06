package com.kirilo.java.operations;

public class SubtractOp extends AbstractOperation{
    public SubtractOp(double one, double two) {
        super(one, two);
    }

    @Override
    public double calculate() {
        return getOne() - getTwo();
    }
}
