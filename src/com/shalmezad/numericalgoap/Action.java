package com.shalmezad.numericalgoap;

import com.shalmezad.numericalgoap.statements.IPostCondition;
import com.shalmezad.numericalgoap.statements.IPrecondition;
import com.shalmezad.numericalgoap.statements.Statement;

import java.io.Serializable;
import java.util.Vector;

public class Action implements Serializable
{
    private final String name;
    private Vector<IPrecondition> preconditions;
    private Vector<IPostCondition> postconditions;
    private float cost;

    //region Constructors
    public Action(String name)
    {
        this(name,1);
    }
    public Action(String name,float cost)
    {
        this.name = name;
        preconditions = new Vector<IPrecondition>();
        postconditions = new Vector<IPostCondition>();
        this.cost = cost;
    }
    //endregion

    //region Getters
    public float getCost()
    {
        return cost;
    }
    //endregion

    //region "Setters"
    public Action addPrecondition(IPrecondition precondition)
    {
        this.preconditions.add(precondition);
        return this;
    }

    public Action addPostcondition(IPostCondition postcondition)
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
        System.out.println("    Applying action: " + name);
        Logger.printState(oldState,3);
        Vector<Statement> state = (Vector<Statement>) oldState.clone();
        for(IPostCondition pc : postconditions)
        {
            state = pc.modifyState(state);
        }
        Logger.printState(state,3);
        return state;
    }

    public boolean conditionsMet(Vector<Statement> state)
    {
        System.out.println("    Checking conditions: " + name);
        Logger.printState(state,3);
        boolean allConditionsMet = true;
        for(IPrecondition pc : preconditions)
        {
            allConditionsMet &= pc.conditionsMet(state);
        }
        System.out.println("      " + allConditionsMet);
        return allConditionsMet;
    }

    @Override
    public String toString() {
        return "<Action:" + name + ">";
    }
}
