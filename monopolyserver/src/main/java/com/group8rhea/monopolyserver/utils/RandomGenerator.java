package com.group8rhea.monopolyserver.utils;

import java.util.List;
import java.util.Random;
import java.util.Set;

public final class RandomGenerator {
    private static RandomGenerator instance;
    private static Random rndm;

    private RandomGenerator(){
        rndm = new Random();
    }

    public static RandomGenerator getInstance(){
        if (instance == null){
            instance = new RandomGenerator();
            return instance;
        }
        else{
            return instance;
        }
    }

    public static int getRandomWithExclusion(int start, int end, Set<Integer> excludeList ) {
        int random = start + rndm.nextInt(end - start + 1 - excludeList.size());

        for (int ex : excludeList) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }

    public static int getRandomRange(int start,int end){
        return start + rndm.nextInt(end - start + 1);
    }
}
