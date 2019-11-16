package de.devhill.misc.aoc2018;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class Day01 {
    public static void main(String[] args) throws Exception {
        Integer[] input = readInput("Day01.txt");
        part1(input);
        part2(input);
    }

    public static Integer[] readInput(String fileName) throws Exception {
        ClassLoader cL = Thread.currentThread().getContextClassLoader();
        InputStream is = cL.getResourceAsStream(fileName);

        InputStreamReader sR = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(sR);

        ArrayList<Integer> list = new ArrayList<>();

        for (String line; (line = br.readLine()) != null; ) {
            list.add(Integer.valueOf(line));
        }
        Integer[] array = new Integer[list.size()];
        array = list.toArray(array);

        return array;

    }

    public static void part1(Integer[] input) {
        int freq = 0;

        for (int deltaFreq : input) {
            freq += deltaFreq;
        }

        System.out.println("Output part1: " + freq);
    }


    public static void part2(Integer[] input) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int freq = 0;
        while (true) {
            for (int deltaFreq : input) {
                freq += deltaFreq;
                if (map.get(freq) != null) {
                    System.out.println("Output part2: " + freq);
                    return;
                } else map.put(freq, 1);
            }
        }
    }
}
