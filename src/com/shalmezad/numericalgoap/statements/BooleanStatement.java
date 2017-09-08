package com.shalmezad.numericalgoap.statements;

import java.io.Serializable;
import java.util.Vector;

public class BooleanStatement extends Statement implements IPrecondition, IPostCondition, Serializable
{
    public boolean expectedValue;

    public BooleanStatement(String name, boolean expectedValue)
    {
        this.name = name;
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean conditionsMet(Vector<Statement> currentState)
    {
        //System.out.println("Checking conditions met: " + this);
        for(Statement statement : currentState)
        {
            if(statement.name.equals(this.name))
            {
                //System.out.println("Found matching statement: " + statement);
                BooleanStatement st = (BooleanStatement)statement;
                return st.expectedValue == this.expectedValue;
            }
        }
        //System.out.println("No matching statement found");
        return !expectedValue;
    }

    @Override
    public Vector<Statement> modifyState(Vector<Statement> oldState)
    {
        boolean hadState = false;
        for(Statement statement : oldState)
        {
            if(statement.name.equals(this.name))
            {
                BooleanStatement st = (BooleanStatement)statement;
                st.expectedValue = this.expectedValue;
                hadState = true;
            }
        }

        if(!hadState)
        {
            oldState.add(new BooleanStatement(this.name, this.expectedValue));
        }
        return oldState;
    }


    @Override
    public String toString()
    {
        return "<" + this.name + ":" + this.expectedValue + ">";
    }
}
