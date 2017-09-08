package com.shalmezad.numericalgoap;

import com.shalmezad.numericalgoap.statements.*;

import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	    // Lumberjack can do the following actions:
        // Get twigs, costs 1, gives wood x1
        // Get ax, cost 1, gives axe
        // Chop, requires ax, gives wood x3

        Vector<Statement> currentState = new Vector<Statement>();
        Vector<Action> possibleActions = new Vector<Action>();
        IPrecondition goal;

        String HAS_AX="HAS_AX";
        String HAS_WOOD="HAS_WOOD";

        // Set up our current state:

        // Set up our actions:
        Action actionGetTwigs = new Action("GET TWIGS");
        actionGetTwigs.addPostcondition(new NumericalStatement(HAS_WOOD, 1));
        possibleActions.add(actionGetTwigs);


        Action actionGetAx = new Action("GET AX");
        actionGetAx.addPrecondition(new BooleanStatement(HAS_AX, false));
        actionGetAx.addPostcondition(new BooleanStatement(HAS_AX, true));
        possibleActions.add(actionGetAx);

        Action actionChop = new Action("CHOP",1);
        actionChop.addPrecondition(new BooleanStatement(HAS_AX, true));
        actionChop.addPostcondition(new NumericalStatement(HAS_WOOD, 2));
        possibleActions.add(actionChop);


        // Set up our goal:
        goal = new ComparisonNumericalStatement(HAS_WOOD, ">=", 10);

        // Plan:
        Plan plan = Goap.planActions(currentState, possibleActions, goal);
        System.out.println(plan);
    }
}
