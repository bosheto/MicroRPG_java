package com.microrpg.utils;

import com.microrpg.world.Overworld;
import com.microrpg.world.Position;
import com.microrpg.world.World;

import java.io.PipedOutputStream;
import java.util.*;

public class A_Star {



    private World world;
    private Grid navGrid;

    public A_Star(World world){
        this.world = world;
        navGrid = new Grid(world);
    }


    public List<Position> findPath(Position startPosition, Position targetPosition){

        List<Node> openSet = new ArrayList<>();
        HashSet<Node> closedSet = new HashSet<>();

        Node startNode = navGrid.getNode(startPosition);
        Node targetNode = navGrid.getNode(targetPosition);

        List<Position> path = new ArrayList<>();
        openSet.add(startNode);

        while(openSet.size() > 0){
            Node currentNode = openSet.get(0);

            for(int i = 1; i < openSet.size(); i++) {
                if (openSet.get(i).getF() < currentNode.getF() || openSet.get(i).getF() == currentNode.getF()){
                    if( openSet.get(i).h < currentNode.h)
                        currentNode = openSet.get(i);
                }
            }

            openSet.remove(currentNode);
            closedSet.add(currentNode);

            if(currentNode == targetNode){
                path = retracePath(startNode, targetNode);
                break;
            }

            for (Node neighbour : getNeighbours(currentNode.getPosition())) {
                if(!neighbour.walkable || closedSet.contains(neighbour))
                    continue;

                int newMoveCostToNeighbour = currentNode.g + getDistance(currentNode, neighbour);

                if(newMoveCostToNeighbour < neighbour.g || !openSet.contains(neighbour)){
                    neighbour.g = newMoveCostToNeighbour;
                    neighbour.h = getDistance(neighbour, targetNode);
                    neighbour.parent = currentNode;
                    if(!openSet.contains(neighbour))
                        openSet.add(neighbour);
                }
            }
        }
        return path;
    }

    private int getDistance(Node a, Node b){
        int distX = Math.abs(a.getPosition().getX() - b.getPosition().getX());
        int distY = Math.abs(a.getPosition().getY() - b.getPosition().getY());

        if(distX > distY)
            return 14 * distY + 10 * (distX - distY);
        return 14 * distX + 10 * (distY - distX);

    }

    private List<Node> getNeighbours(Position position){
        List<Node> neighbours = new ArrayList<>();
        Position[] neighbourPosition = {
                new Position(0,-1),
                new Position(-1, -1),
                new Position(1, 1),
                new Position(-1,0),
                new Position(1, 0),
                new Position(0, 1),
                new Position(-1, 1),
                new Position(1, 1)
        };

        for (Position nPos : neighbourPosition) {
            Position testPosition = position.add(nPos);
            if(navGrid.containsPosition(testPosition))
                neighbours.add(navGrid.getNode(testPosition));

        }

        return neighbours;
    }

   private List<Position> retracePath(Node startNode, Node endNode){
        List<Position> path = new ArrayList<>();
        Node currentNode = endNode;
        while(currentNode != startNode){
            path.add(currentNode.getPosition());
            currentNode = currentNode.parent;
        }
       Collections.reverse(path);
        return path;
   }

}

class Grid{

    World world;

    Node[][] nodes;

    public Grid(World world) {
        this.world = world;
        initGrid();
    }

    private void initGrid(){
        nodes = new Node[world.getySize()][world.getxSize()];
        for(int y = 0; y < world.getySize(); y++){
            for(int x = 0; x < world.getxSize(); x++){
                nodes[y][x] = createNodeFromPos(x, y);
            }
        }
    }

    private Node createNodeFromPos(int x, int y){
        Node node = new Node();
        node.gridX = x;
        node.gridY = y;
        node.walkable = !world.GetTile(new Position(x, y)).hasCollider();
        return node;
    }

    public Node getNode(Position pos){

        return nodes[pos.getY()][pos.getX()];
    }

    public boolean containsPosition(Position pos){
        if(pos.getX() < 0 || pos.getX() > nodes[0].length)
            return false;
        return pos.getY() > 0 && pos.getY() < nodes.length;
    }
}

class Node {
    Node parent;
    public boolean walkable;
    private Position position;

    public int gridX;
    public int gridY;

    public int g = 0;
    public int h = 0;

    public int getF() {
        return g + h;
    }

    public Position getPosition() {
        return new Position(gridX, gridY);
    }

    public Node(boolean walkable, Position position) {
        this.walkable = walkable;
        this.position = position;
    }

    public Node(){}


}
