package com.shalmezad.numericalgoap;

import com.shalmezad.numericalgoap.statements.Statement;

import java.util.Vector;

public class Logger {

    public static void printState(Vector<Statement> state)
    {

        String returnString = "";
        returnString += "[";
        for(Statement statement : state)
        {
            returnString += statement.toString();
            returnString += ",";
        }
        returnString += "]";

        System.out.println("State: " + returnString);
    }
}
