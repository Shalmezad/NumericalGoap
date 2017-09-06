package com.shalmezad.numericalgoap.statements;

import java.util.Vector;

/**
 * An individual piece of a state
 * like "HAS BALL"
 * or "!AT GOAL"
 * or "HAS_WOOD(3)"
 */
public abstract class Statement {

    /**
     * Name of the component
     * ie: !AT_GOAL's name is AT_GOAL
     * HAS_WOOD(3)'s name is HAS_WOOD
     */
    public String name;

    public abstract Vector<Statement> modifyState(Vector<Statement> oldState);

    public abstract boolean conditionsMet(Vector<Statement> currentState);


    @Override
    public String toString()
    {
        return "<" + this.name +  ">";
    }
}
