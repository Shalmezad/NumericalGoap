package com.shalmezad.numericalgoap;

import com.shalmezad.numericalgoap.statements.IPrecondition;
import com.shalmezad.numericalgoap.statements.Statement;

import java.util.Comparator;
import java.util.Optional;
import java.util.Vector;

public class Goap {
    public static Plan planActions(Vector<Statement> currentState, Vector<Action> possibleActions, IPrecondition goal)
    {
        return planActions(currentState,possibleActions,goal,20);
    }

    public static Plan planActions(Vector<Statement> currentState, Vector<Action> possibleActions, IPrecondition goal, int maxActions)
    {
        Vector<Plan> possibilities = new Vector<Plan>();
        // Add our current state onto there:
        Plan currentPossibility = new Plan();
        //currentPossibility.lastState = (Vector<Statement>) currentState.clone();
        currentPossibility.lastState = Statement.deepCloneState(currentState);
        currentPossibility.cost = 0;

        possibilities.add(currentPossibility);

        Plan planOfAction = null;
        boolean maxActionsReached = false;
        int numLoops = 0;
        // Loop through:
        while(planOfAction == null && !maxActionsReached)
        {
            numLoops += 1;
            System.out.println("__________________________________");
            Plan leaf = possibilities.stream().min(Comparator.comparing(p -> p.cost)).get();
            maxActionsReached = leaf.cost > maxActions;
            possibilities.remove(leaf);
            System.out.println("Current leaf:");
            Logger.printState(leaf.lastState,1);
            Logger.printActions(leaf.actions, 1);
            // Go through each action:
            System.out.println("Evaluating possible actions");
            for(Action action: possibleActions)
            {
                // See if we can apply it:
                if(action.conditionsMet(leaf.lastState))
                {
                    // Add it to our BFS tree:
                    Plan possiblePlan = new Plan();
                    possiblePlan.lastState = action.newState(Statement.deepCloneState(leaf.lastState));
                    possiblePlan.cost = leaf.cost + action.getCost();
                    possiblePlan.actions = leaf.deepCloneActions();
                    possiblePlan.actions.add(action);
                    possibilities.add(possiblePlan);
                }
            }
            System.out.println("Checking end state");
            Optional<Plan> endPlan = possibilities.stream().filter(p -> p.meetsConditions(goal)).findFirst();
            if(endPlan.isPresent())
            {
                planOfAction = endPlan.get();
            }
        }
        System.out.println("Number of loops to find plan: " + numLoops);

        return planOfAction;
    }
}
