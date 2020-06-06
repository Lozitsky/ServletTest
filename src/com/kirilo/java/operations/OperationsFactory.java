package com.kirilo.java.operations;

import java.io.Serializable;

public class OperationsFactory implements Serializable {
    public OperationsFactory() {
    }

    public Double calculate(OperationType operation, double one, double two) {
        Operation expression;
        switch (operation) {
            case ADD:
                expression = new AddOp(one, two);
                break;
            case DIVIDE:
                expression = new DivideOp(one, two);
                break;
            case MULTIPLY:
                expression = new MultiplyOp(one, two);
                break;
            case SUBTRACT:
                expression = new SubtractOp(one, two);
                break;
            default:
                throw new UnsupportedOperationException();
        }

        return expression.calculate();
    }
}