package com.shalmezad.numericalgoap;

import com.shalmezad.numericalgoap.statements.Statement;

import java.util.Comparator;
import java.util.Optional;
import java.util.Vector;

public class Goap {
    public static Plan planActions(Vector<Statement> currentState, Vector<Action> possibleActions, Statement goal)
    {
        return planActions(currentState,possibleActions,goal,20);
    }

    public static Plan planActions(Vector<Statement> currentState, Vector<Action> possibleActions, Statement goal, int maxActions)
    {
        Vector<Plan> possibilities = new Vector<Plan>();
        // Add our current state onto there:
        Plan currentPossibility = new Plan();
        currentPossibility.lastState = (Vector<Statement>) currentState.clone();
        currentPossibility.cost = 0;

        possibilities.add(currentPossibility);

        Plan planOfAction = null;
        boolean maxActionsReached = false;
        // Loop through:
        while(planOfAction == null && !maxActionsReached)
        {
            Plan leaf = possibilities.stream().min(Comparator.comparing(p -> p.cost)).get();
            maxActionsReached = leaf.cost > maxActions;
            possibilities.remove(leaf);
            // Go through each action:
            for(Action action: possibleActions)
            {
                // See if we can apply it:
                if(action.conditionsMet(leaf.lastState))
                {
                    // Add it to our BFS tree:
                    Plan possiblePlan = new Plan();
                    possiblePlan.lastState = action.newState(leaf.lastState);
                    possiblePlan.cost = leaf.cost + action.getCost();
                    possiblePlan.actions = (Vector<Action>) leaf.actions.clone();
                    possiblePlan.actions.add(action);
                    possibilities.add(possiblePlan);
                }
            }

            Optional<Plan> endPlan = possibilities.stream().filter(p -> p.meetsConditions(goal)).findFirst();
            if(endPlan.isPresent())
            {
                planOfAction = endPlan.get();
            }
        }

        return planOfAction;
    }
}
