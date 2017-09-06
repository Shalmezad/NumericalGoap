package com.shalmezad.numericalgoap.statements;

import java.util.Vector;

public class NumericalStatement extends Statement implements IPostCondition
{
    public float number;

    public NumericalStatement(String name, float number)
    {
        this.name = name;
        this.number = number;
    }

    @Override
    public Vector<Statement> modifyState(Vector<Statement> oldState)
    {
        boolean hadState = false;
        for(Statement statement : oldState)
        {
            if(statement.name == this.name)
            {
                NumericalStatement st = (NumericalStatement)statement;
                st.number += this.number;
                hadState = true;
            }
        }

        if(!hadState)
        {
            oldState.add(new NumericalStatement(this.name, this.number));
        }
        return oldState;
    }

    @Override
    public String toString()
    {
        return "<" + this.name + ":" + this.number + ">";
    }

}
