package com.microrpg.utils;

import com.microrpg.world.Position;

import java.io.PipedOutputStream;
import java.util.*;

public class A_Star {

    public List<Position> path = new ArrayList<>();

    public void findPath(Position startPosition, Position targetPosition, HashMap<Position, Boolean> searchSpace){
        List<Node> openSet = new ArrayList<>();
        HashSet<Node> closedSet = new HashSet<>();
        Node startNode = new Node(true, startPosition);
        Node targetNode = new Node(true, targetPosition);

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
                return;
            }

            for (Node neighbour : getNeighbours(currentNode.position, searchSpace)) {
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
    }

    private int getDistance(Node a, Node b){
        int distX = Math.abs(a.position.getX() - b.position.getX());
        int distY = Math.abs(a.position.getY() - b.position.getY());

        if(distX > distY)
            return 14 * distY + 10 * (distX - distY);
        else
            return 14 * distX + 10 * (distY - distX);

    }

    private List<Node> getNeighbours(Position position, HashMap<Position, Boolean> searchSpace){
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
            if(searchSpace.containsKey(testPosition) && searchSpace.get(testPosition))
                neighbours.add(new Node(searchSpace.get(testPosition), testPosition));

        }

        return neighbours;
    }

   private List<Position> retracePath(Node startNode, Node endNode){
        List<Position> path = new ArrayList<>();
        Node currentNode = endNode;
        while(currentNode != startNode){
            path.add(currentNode.position);
            currentNode = currentNode.parent;
        }
       Collections.reverse(path);
        return path;
   }

}

class Node{
    Node parent;
    public boolean walkable;
    Position position;

    public int g = 0;
    public int h = 0;

    public int getF() {
        return g + h;
    }

    public Node(boolean walkable, Position position){
        this.walkable = walkable;
        this.position = position;
    }

}
