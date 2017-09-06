package com.shalmezad.numericalgoap.statements;

import java.util.Vector;

/**
 * Needed for some paths
 * ie: HAS_WOOD > 3
 */
public class ComparisonNumericalStatement extends Statement
{

    String comparator;
    float number;

    public ComparisonNumericalStatement(String name, String comparator, float number)
    {
        this.name = name;
        this.comparator = comparator;
        this.number = number;
    }

    @Override
    public Vector<Statement> modifyState(Vector<Statement> oldState) {
        throw new RuntimeException("Cannot modify state with comparison statement");
    }

    @Override
    public boolean conditionsMet(Vector<Statement> currentState) {
        // Figure out if we have a member in the current state:
        for(Statement statement:currentState)
        {
            if(statement.name == this.name)
            {
                // TODO: Remove hardcoded >=:
                // TODO: Add type checking
                NumericalStatement st = (NumericalStatement)statement;
                return st.number >= this.number;
            }
        }
        // TODO: If <, this should be true if < some positive number.
        return false;
    }


    @Override
    public String toString()
    {
        return "<" + this.name + ":" + this.comparator + ":" + this.number + ">";
    }
}
