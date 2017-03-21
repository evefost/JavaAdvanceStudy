package com.xie.java.spi;

import com.xie.java.spi.test.DivisionOperationImpl;
import com.xie.java.spi.test.IOperation;
import com.xie.java.spi.test.PlusOperationImpl;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Main {

    public static void main(String[] args) {

        IOperation plus = new PlusOperationImpl();

        IOperation division = new DivisionOperationImpl();

        System.out.println(plus.operation(6, 3));

        System.out.println(division.operation(6, 3));

        ServiceLoader<IOperation> operations = ServiceLoader.load(IOperation.class);

        Iterator<IOperation> operationIterator = operations.iterator();

        System.out.println("classPath:" + System.getProperty("java.class.path"));

        while (operationIterator.hasNext()) {

            IOperation operation = operationIterator.next();

            System.out.println(operation.operation(6, 3));

        }
    }

}

