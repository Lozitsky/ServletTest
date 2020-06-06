package com.kirilo.java.operations;

public class AddOp extends AbstractOperation{


    public AddOp(double one, double two) {
        super(one, two);
    }

    @Override
    public double calculate() {
        return getOne() + getTwo();
    }
}
