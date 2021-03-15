package com.movie.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.movie.springboot.Utils.CountDirectionUtil;
import com.movie.springboot.bean.Input;
import com.movie.springboot.bean.Output;
import com.movie.springboot.bean.Result;
import com.movie.springboot.mapper.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class HelloWorldController {

    @Autowired
    ResultRepository resultRepository;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World-fuck me";
    }

    /**
     * query the final hoover position and the number of patches of dirt the robot cleaned up
     by the room dimensions, the locations of the dirt patches, the hoover location and the driving instructions
     *
     * @return
     */
    @RequestMapping(value = "/robotClean", method = RequestMethod.POST)
    @ResponseBody
    public Output findPatches(@RequestBody Input input) {
//        System.out.println(""+ jsonParam.toJSONString());

        String jsonStr = JSONObject.toJSONString(input); //check if there is data
        System.out.println("the input json data is===="+ jsonStr);

        int patchCount =0;
        List<List<Integer>> finalCoords = new ArrayList<List<Integer>>();

        //driving instructions respectively
        int count = input.getInstructions().length();
        for (int j=0; j<count; j++){
            String direction = String.valueOf(input.getInstructions().charAt(j));  //get the cardinal direction
            List<Integer> newCoords = CountDirectionUtil.countDirection(direction,input.getCoords(),input.getRoomSize()); // the current hoover location
            Iterator<Integer> iterator = input.getCoords().iterator();
            while (iterator.hasNext()) {
                    iterator.remove();//delete the raw hoover location
            }
            input.getCoords().addAll(newCoords);

            //query the patch of dirt the robot is hoovering
            for (int i=0; i<input.getPatches().size(); i++){
                if(newCoords.get(0)== input.getPatches().get(i).get(0) && newCoords.get(1)==input.getPatches().get(i).get(1)){
                    System.out.println(newCoords.get(0)+ input.getPatches().get(i).get(0) + newCoords.get(1) + input.getPatches().get(i).get(1));
                    if(!finalCoords.contains(newCoords)){  // remove the the same patch of dirt
                        finalCoords.add(newCoords);
                    }
                }
            }
        }
        System.out.println("the number of patches======"+finalCoords.size());

        // return the final hoover position and the number of patches of dirt in a json
        Output output = new Output();
        output.setPatches(finalCoords.size());
        output.setCoords(input.getCoords());

        // insert a record of input and output into mysql
        Result result = new Result();
        result.setInput(jsonStr);
        result.setOutput(JSONObject.toJSONString(output));
        resultRepository.save(result);

        return output;
    }



    public static void main(String[] args) {

        Input input = new Input();
        List<Integer> room = new ArrayList<Integer>();
        room.add(5);
        room.add(5);
        input.setRoomSize(room);
//        if(room.size() !=2){
//            System.out.println(11111111);
//        }else{
//            System.out.println(2222);
//        }
        List<Integer> cor = new ArrayList<Integer>();
        cor.add(1);
        cor.add(2);
        input.setCoords(cor);

        List<Integer> pat1 = new ArrayList<Integer>();
        pat1.add(1);
        pat1.add(0);
        List<Integer> pat2 = new ArrayList<Integer>();
        pat2.add(2);
        pat2.add(2);
        List<Integer> pat3 = new ArrayList<Integer>();
        pat3.add(2);
        pat3.add(3);
        List<List<Integer>> x = new ArrayList<List<Integer>>();
        x.add(pat1);
        x.add(pat2);
        x.add(pat3);
        input.setPatches(x);
        String instructions = "NNESEESWNWW";
        input.setInstructions(instructions);
//        System.out.println(input.toString());




        int count = instructions.length();

        int patchCount =0;
        List<List<Integer>> finalCoords = new ArrayList<List<Integer>>();
//        List<Integer> newCoords = new ArrayList<Integer>();
//        newCoords.add(input.getCoords().get(0));
//        newCoords.add(input.getCoords().get(1));
        for (int j=0; j<count; j++){
//            System.out.println(instructions.charAt(j));
            String direction = String.valueOf(instructions.charAt(j));
           List<Integer> newCoords = CountDirectionUtil.countDirection(direction,input.getCoords(),input.getRoomSize());
            input.getCoords().remove(0);
            input.getCoords().remove(0);
            input.getCoords().addAll(newCoords);
//            System.out.println( input.getCoords());
//            finalCoords.add(newCoords);
            for (int i=0; i<x.size(); i++){
//                System.out.println(x.get(i).toString());
                if(newCoords.get(0)== x.get(i).get(0) && newCoords.get(1)==x.get(i).get(1)){
                    System.out.println(newCoords.get(0)+ x.get(i).get(0) + newCoords.get(1) + x.get(i).get(1));
                    if(!finalCoords.contains(newCoords)){
                        finalCoords.add(newCoords);
                    }
                }
            }
        }
        System.out.println("patches数量======"+finalCoords.size());
    }



}
