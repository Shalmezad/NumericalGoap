package com.shalmezad.numericalgoap.statements;

import java.io.*;
import java.util.Vector;

/**
 * An individual piece of a state
 * like "HAS BALL"
 * or "!AT GOAL"
 * or "HAS_WOOD(3)"
 */
public abstract class Statement implements Serializable {

    /**
     * Name of the component
     * ie: !AT_GOAL's name is AT_GOAL
     * HAS_WOOD(3)'s name is HAS_WOOD
     */
    public String name;

    /*
    public abstract Vector<Statement> modifyState(Vector<Statement> oldState);
    public abstract boolean conditionsMet(Vector<Statement> currentState);


    @Override
    public String toString()
    {
        return "<" + this.name +  ">";
    }
    */

    public static Vector<Statement> deepCloneState(Vector<Statement> state)
    {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(state);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Vector<Statement>) ois.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
