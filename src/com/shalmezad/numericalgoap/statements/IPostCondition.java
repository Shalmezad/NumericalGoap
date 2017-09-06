package com.shalmezad.numericalgoap.statements;

import java.util.Vector;

public interface IPostCondition
{
    public Vector<Statement> modifyState(Vector<Statement> oldState);
}
