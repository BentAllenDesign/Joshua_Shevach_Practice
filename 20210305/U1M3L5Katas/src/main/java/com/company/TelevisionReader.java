package com.company;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class TelevisionReader {

    public static void main(String[] args) {

        List<Television> televisions = null;
        String fileName = "televisions.csv";

        try {
            televisions = getTelevisions(fileName);
        } catch (FileNotFoundException e) {
            System.out.println(e.getClass() + ": The file '" + fileName + "' was not found. Goodbye!");
            return;
        }

        getListOfScreensLargerThanN(televisions, 60);

        mapTelevisionsByBrand(televisions);

        getAverageScreenSize(televisions);

        getLargestScreenInCollection(televisions);

        getTelevisionsByScreenSizeAscending(televisions);
    }

    private static void getListOfScreensLargerThanN(List<Television> televisions, int n) {
        System.out.println("\nThe televisions that are greater than 60 inches are: ");
        televisions.stream()
                .filter(x -> x.getScreenSize() > n)
                .forEach(System.out::println);
    }

    private static void mapTelevisionsByBrand(List<Television> televisions) {
        Map<String, List<Television>> televisionMap = televisions.stream()
                .collect(Collectors.groupingBy(Television::getBrand));
        System.out.println("\nThe brands that we have in our map are:");
        System.out.println(televisionMap.keySet());
    }

    private static void getAverageScreenSize(List<Television> televisions) {
        double average = televisions.stream()
                .mapToInt(Television::getScreenSize)
                .average()
                .getAsDouble();
        System.out.println("\nThe average screen size is: " + average);
    }

    private static void getLargestScreenInCollection(List<Television> televisions) {
        int largestScreen = televisions.stream()
                .mapToInt(Television::getScreenSize)
                .max()
                .getAsInt();
        System.out.println("\nThe largest screen is: " + largestScreen + " inches!");
    }

    private static void getTelevisionsByScreenSizeAscending(List<Television> televisions) {
        Collections.sort(televisions, Comparator.comparing(Television::getScreenSize));
        System.out.println("\nThe list sorted by screen size is: ");
        televisions.forEach(System.out::println);
    }

    public static List<Television> getTelevisions(String filename) throws FileNotFoundException {
        return FileIO.getTelevisions(filename);
    }
}
