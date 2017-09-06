package com.shalmezad.numericalgoap.statements;

import java.util.Vector;

public class BooleanStatement extends Statement {
    public boolean expectedValue;

    public BooleanStatement(String name, boolean expectedValue)
    {
        this.name = name;
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean conditionsMet(Vector<Statement> currentState)
    {
        for(Statement statement : currentState)
        {
            if(statement.name == this.name)
            {
                BooleanStatement st = (BooleanStatement)statement;
                return st.expectedValue == this.expectedValue;
            }
        }
        return !expectedValue;
    }

    @Override
    public Vector<Statement> modifyState(Vector<Statement> oldState)
    {
        boolean hadState = false;
        for(Statement statement : oldState)
        {
            if(statement.name == this.name)
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
