package Game.Players;

import Game.ServiceTools.Coordinates;
import Game.ServiceTools.LocationsChips;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Alla on 26.03.14.
 */
public class NotSoEasyBot extends Bot {
    private class MoveNode {
        private MoveNode parent;
        private LocationsChips chips;
        private int profit;
        private Coordinates lastCoordinates;

        public MoveNode(LocationsChips chips, int profit, MoveNode parent, Coordinates lastCoordinates) {
            this.chips = chips;
            this.profit = profit;
            this.parent = parent;
            this.lastCoordinates = lastCoordinates;
        }
    }

    private final int MAXDEPTH = 1;

    private ArrayList<MoveNode> lastLevel = new ArrayList<MoveNode>();


    public NotSoEasyBot (Color color, String name, Player rival) {
        super(color, name, rival);
    }

    @Override
    public Coordinates botMove(LocationsChips locations) {
        Coordinates move;
        MoveNode root = new MoveNode(locations, 0, null, null);

        buildTree(0, root);

        MoveNode max = new MoveNode(null, -100500, null, null);
        for (MoveNode node : lastLevel)
            if (node.profit > max.profit)
                max = node;

        System.out.println(max.profit);

        try {
            while (max.parent != root) {
                System.out.println("Found new max = " + max.parent.profit);
                max = max.parent;
            }
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
        return max.lastCoordinates;
    }

    private void buildTree (int depth, MoveNode node) {
        Player curPlayer = depth % 2 == 0 ? this : this.rival;

        ArrayList<Coordinates> border = node.chips.getBorder();
        for (Coordinates coord : border) {
            if (node.chips.canPut(this, coord)) {
                LocationsChips newLocations = node.chips.clone();
                newLocations.move(curPlayer, coord);

                int profit = newLocations.getNumberChecks(this) - newLocations.getNumberChecks(this.rival);

                MoveNode child = new MoveNode(newLocations, profit, node, coord);
                if (depth < MAXDEPTH)
                    buildTree(depth + 1, child);
            }
        }
        if (depth == MAXDEPTH)
            lastLevel.add(node);
    }

}
