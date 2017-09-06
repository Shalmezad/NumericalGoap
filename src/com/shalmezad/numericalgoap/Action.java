package com.shalmezad.numericalgoap;

import com.shalmezad.numericalgoap.statements.Statement;

import java.util.Vector;

public class Action
{
    private final String name;
    private Vector<Statement> preconditions;
    private Vector<Statement> postconditions;
    private float cost;

    //region Constructors
    public Action(String name)
    {
        this.name = name;
        preconditions = new Vector<Statement>();
        postconditions = new Vector<Statement>();
        cost = 1;
    }
    //endregion

    //region Getters
    public float getCost()
    {
        return cost;
    }
    //endregion

    //region "Setters"
    public Action addPrecondition(Statement precondition)
    {
        this.preconditions.add(precondition);
        return this;
    }

    public Action addPostcondition(Statement postcondition)
    {
        this.postconditions.add(postcondition);
        return this;
    }

    public Action setCost(float cost)
    {
        this.cost = cost;
        return this;
    }
    //endregion

    public Vector<Statement> newState(Vector<Statement> oldState)
    {
        System.out.println("Applying action: " + name);
        Logger.printState(oldState);
        Vector<Statement> state = (Vector<Statement>) oldState.clone();
        for(Statement pc : postconditions)
        {
            state = pc.modifyState(state);
        }
        Logger.printState(state);
        return state;
    }

    public boolean conditionsMet(Vector<Statement> state)
    {
        System.out.println("Checking conditions: " + name);
        Logger.printState(state);
        boolean allConditionsMet = true;
        for(Statement pc : preconditions)
        {
            allConditionsMet &= pc.conditionsMet(state);
        }
        System.out.println(allConditionsMet);
        return allConditionsMet;
    }

    @Override
    public String toString() {
        return "<Action:" + name + ">";
    }
}
