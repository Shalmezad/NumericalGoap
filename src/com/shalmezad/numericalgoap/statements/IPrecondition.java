package com.shalmezad.numericalgoap.statements;

import java.util.Vector;

public interface IPrecondition
{
    public boolean conditionsMet(Vector<Statement> currentState);
}
