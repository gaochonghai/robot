package com.movie.springboot.Utils;

import java.util.ArrayList;
import java.util.List;

public class CountDirectionUtil {
    /**
     * query the current hoover location by parameters
     * direction --- the driving instructions
     * coords --- the hoover location
     * roomSize --- the room dimensions
     * @return
     */
    public static List<Integer> countDirection(String direction, List<Integer> coords, List<Integer> roomSize){
        List<Integer> newCoords = new ArrayList<Integer>();
        System.out.println( direction);
        if(direction.equals("N")){   // if driving into a wall, the robot skids in place, else go north
            if(coords.get(1) < roomSize.get(1)){
                int yAxis = coords.get(1)+1;
                newCoords.add(coords.get(0));
                newCoords.add(yAxis);
            }else{
                newCoords.add(coords.get(0));
                newCoords.add(coords.get(1));
            }
        }else if(direction.equals("S")){  // if driving into a wall, the robot skids in place, else go south
            if(coords.get(1) > 0){
                int yAxis = coords.get(1)-1;
                newCoords.add(coords.get(0));
                newCoords.add(yAxis);
            }else{
                newCoords.add(coords.get(0));
                newCoords.add(coords.get(1));
            }
        }else if(direction.equals("W")){  // if driving into a wall, the robot skids in place, else go west
            if(coords.get(0) >0){
                int xAxis = coords.get(0) - 1;
                newCoords.add(xAxis);
                newCoords.add(coords.get(1));
            }else{
                newCoords.add(coords.get(0));
                newCoords.add(coords.get(1));
            }
        }else if(direction.equals("E")){  // if driving into a wall, the robot skids in place, else go east
            if(coords.get(0) < roomSize.get(0)){
                int xAxis = coords.get(0) + 1;
                newCoords.add(xAxis);
                newCoords.add(coords.get(1));
            }else{
                newCoords.add(coords.get(0));
                newCoords.add(coords.get(1));
            }
        }
        System.out.println( "the current hoover location====="+newCoords);
        return newCoords;
    }
}
