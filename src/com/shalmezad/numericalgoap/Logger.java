package com.shalmezad.numericalgoap;

import com.shalmezad.numericalgoap.statements.Statement;

import java.util.Vector;

public class Logger {

    public static void printState(Vector<Statement> state)
    {
        printState(state,0);
    }

    public static void printState(Vector<Statement> state, int indent)
    {
        String returnString = "";
        for(int i=0; i< indent; i++)
        {
            returnString += "  ";
        }
        returnString += "State: ";
        returnString += "[";
        for(Statement statement : state)
        {
            returnString += statement.toString();
            returnString += ",";
        }
        returnString += "]";

        System.out.println(returnString);
    }


    public static void printActions(Vector<Action> actions) {
        printActions(actions,0);
    }

    public static void printActions(Vector<Action> actions, int indent) {
        String returnString = "";

        for(int i=0; i< indent; i++)
        {
            returnString += "  ";
        }
        returnString += "Actions: ";
        returnString += "[";
        for(Action action : actions)
        {
            returnString += action.toString();
            returnString += ",";
        }
        returnString += "]";
        System.out.println(returnString);
    }
}
