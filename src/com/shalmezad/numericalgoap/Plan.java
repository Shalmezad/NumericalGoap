package com.shalmezad.numericalgoap;

import com.shalmezad.numericalgoap.statements.IPrecondition;
import com.shalmezad.numericalgoap.statements.Statement;

import java.io.*;
import java.util.Vector;

public class Plan
{
    public Vector<Statement> lastState;
    public float cost;
    public Vector<Action> actions;

    public Plan()
    {
        lastState = new Vector<Statement>();
        cost = 0;
        actions = new Vector<Action>();
    }

    public boolean meetsConditions(IPrecondition goal)
    {
        return goal.conditionsMet(lastState);
    }

    @Override
    public String toString() {
        String returnString = "";
        returnString += "[";
        for(Action action : actions)
        {
            returnString += action.toString();
            returnString += ",";
        }
        returnString += "]";
        return  returnString;
    }

    public Vector<Action> deepCloneActions()
    {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(actions);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Vector<Action>) ois.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
