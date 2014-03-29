package Game.Players;

import Game.ServiceTools.Coordinates;
import Game.ServiceTools.LocationsChips;

import java.awt.*;
import java.util.ArrayList;

/**
 * clever bot
 */
public class NotSoEasyBot extends Bot {
    /**
     * node of the tree
     */
    private class MoveNode {
        private MoveNode parent;
        private LocationsChips chips;
        private int profit;
        private Coordinates lastCoordinates;
        private int depth;

        private MoveNode(LocationsChips chips, int profit, MoveNode parent, Coordinates lastCoordinates, int depth) {
            this.chips = chips;
            this.profit = profit;
            this.parent = parent;
            this.lastCoordinates = lastCoordinates;
            this.depth = depth;
        }
    }

    /**
     * max depth of the tree
     */
    private final int MAXDEPTH = 4;

    /**
     * nodes on each level
     */
    private ArrayList<MoveNode>[] levels;

    /**
     * constructor
     * @param color color
     * @param name name
     * @param rival rival
     */
    public NotSoEasyBot (Color color, String name, Player rival) {
        super(color, name, rival);
    }

    /**
     * make move
     * @param locations chips locations
     * @return coordinates of the optimal move
     */
    @Override
    public Coordinates botMove(LocationsChips locations) {

        levels = new ArrayList[MAXDEPTH + 1];
        for (int i = 0; i < MAXDEPTH + 1; i++)
            levels[i] = new ArrayList<MoveNode>();

        MoveNode root = new MoveNode(locations, locations.getNumberChecks(this) - locations.getNumberChecks(this.rival), null, null, 0);
        levels[0].add(root);

        buildTree(root);

        int lastLevel = MAXDEPTH;
        while (levels[lastLevel].size() == 0)
            lastLevel--;

        MoveNode optimal = new MoveNode(null, -100500, null, null, -1);

        for (MoveNode node : levels[lastLevel])
            if (node.profit > optimal.profit)
                optimal = node;

        while (optimal.parent != root)
            optimal = optimal.parent;

        return optimal.lastCoordinates;
    }

    /**
     * building move tree
     * @param node root of the tree
     */
    private void buildTree (MoveNode node) {
        Player curPlayer = node.depth % 2 == 0 ? this : this.rival;

        ArrayList<Coordinates> border = node.chips.getBorder();
        for (Coordinates coord : border) {
            if (node.chips.canPut(curPlayer, coord)) {
                LocationsChips newLocations = node.chips.clone();
                newLocations.move(curPlayer, coord);
                int profit = newLocations.getNumberChecks(this) - newLocations.getNumberChecks(this.rival);
                MoveNode child = new MoveNode(newLocations, profit, node, coord, node.depth + 1);

                levels[child.depth].add(child);

                if (child.depth < MAXDEPTH)
                    buildTree(child);
            }
        }
    }
}
