package com.shalmezad.numericalgoap.statements;

import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class BooleanStatementTest {

    // region conditionsMet()
    // 1) Test that true statement is false if ! present
    // 2) Test that true statement is false if present & false
    // 3) Test that true statement is true if present & true
    // 4) Test that false statement is true if ! present
    // 5) Test that false statement is true if present & false
    // 6) Test that false statement is false if present & true

    @Test
    public void conditionsMetTrueNotPresent() throws Exception
    {
        BooleanStatement trueStatement = new BooleanStatement("CONDITION", true);
        Vector<Statement> currentState = new Vector<>();
        assertFalse(trueStatement.conditionsMet(currentState));
    }

    @Test
    public void conditionsMetTruePresentFalse() throws Exception
    {
        BooleanStatement trueStatement = new BooleanStatement("CONDITION", true);
        Vector<Statement> currentState = new Vector<>();
        currentState.add(new BooleanStatement("CONDITION", false));
        assertFalse(trueStatement.conditionsMet(currentState));
    }

    @Test
    public void conditionsMetTruePresentTrue() throws Exception
    {
        BooleanStatement trueStatement = new BooleanStatement("CONDITION", true);
        Vector<Statement> currentState = new Vector<>();
        currentState.add(new BooleanStatement("CONDITION", true));
        assertTrue(trueStatement.conditionsMet(currentState));
    }

    @Test
    public void conditionsMetFalseNotPresent() throws Exception
    {
        BooleanStatement falseStatement = new BooleanStatement("CONDITION", false);
        Vector<Statement> currentState = new Vector<>();
        assertTrue(falseStatement.conditionsMet(currentState));
    }

    @Test
    public void conditionsMetFalsePresentFalse() throws Exception
    {
        BooleanStatement falseStatement = new BooleanStatement("CONDITION", false);
        Vector<Statement> currentState = new Vector<>();
        currentState.add(new BooleanStatement("CONDITION", false));
        assertTrue(falseStatement.conditionsMet(currentState));
    }

    @Test
    public void conditionsMetFalsePresentTrue() throws Exception
    {
        BooleanStatement falseStatement = new BooleanStatement("CONDITION", false);
        Vector<Statement> currentState = new Vector<>();
        currentState.add(new BooleanStatement("CONDITION", true));
        assertFalse(falseStatement.conditionsMet(currentState));
    }

    // I noticed that for this state:
    // [<HAS_WOOD:1.0>,<HAS_AX:true>,]
    // "Get ax" was saying conditions were met:
    @Test
    public void conditionsMetFalsePresentTrueMulti() throws Exception
    {
        BooleanStatement falseStatement = new BooleanStatement("CONDITION", false);
        Vector<Statement> currentState = new Vector<>();
        currentState.add(new NumericalStatement("OTHER_CONDITION", 1));
        currentState.add(new BooleanStatement("CONDITION", true));
        assertFalse(falseStatement.conditionsMet(currentState));
    }

    //endregion

    // region modifyState

    //1) assert true works if not present
    //2) assert true works if true present
    //3) assert true works if false present
    //4) assert false works if not present
    //5) assert false works if true present
    //6) assert false works if false present

    @Test
    public void modifyStateTrueNotPresent() throws Exception
    {
        BooleanStatement trueStatement = new BooleanStatement("CONDITION", true);
        Vector<Statement> currentState = new Vector<>();
        Vector<Statement> newState = trueStatement.modifyState(currentState);
        assertEquals(1, newState.size());
        assertTrue(newState.get(0) instanceof BooleanStatement);
        assertTrue(((BooleanStatement)(newState.get(0))).expectedValue);
    }

    @Test
    public void modifyStateTrueTruePresent() throws Exception
    {
        BooleanStatement trueStatement = new BooleanStatement("CONDITION", true);
        Vector<Statement> currentState = new Vector<>();
        currentState.add(new BooleanStatement("CONDITION", true));
        Vector<Statement> newState = trueStatement.modifyState(currentState);
        assertEquals(1, newState.size());
        assertTrue(newState.get(0) instanceof BooleanStatement);
        assertTrue(((BooleanStatement)(newState.get(0))).expectedValue);
    }

    @Test
    public void modifyStateTrueFalsePresent() throws Exception
    {
        BooleanStatement trueStatement = new BooleanStatement("CONDITION", true);
        Vector<Statement> currentState = new Vector<>();
        currentState.add(new BooleanStatement("CONDITION", false));
        Vector<Statement> newState = trueStatement.modifyState(currentState);
        assertEquals(1, newState.size());
        assertTrue(newState.get(0) instanceof BooleanStatement);
        assertTrue(((BooleanStatement)(newState.get(0))).expectedValue);
    }
    //endregion

}