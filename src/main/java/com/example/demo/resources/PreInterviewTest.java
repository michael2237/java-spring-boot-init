package com.example.demo.resources;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/PreInterviewTests")
public class PreInterviewTest {

    @RequestMapping(value = "/Q1", method = RequestMethod.GET)
    @PreAuthorize("permitAll()")
    // GET http://localhost:8080/PreInterviewTests/Q1
    public String printBugfix() {
        StringBuilder result = new StringBuilder();

        for (int num = 1; num <= 100; num++) {
            if (num % 3 == 0 && num % 5 == 0) {
                result.append("bugfix\n");
            } else if (num % 3 == 0) {
                result.append("bug\n");
            } else if (num % 5 == 0) {
                result.append("fix\n");
            } else {
                result.append(num).append("\n");
            }
        }

        return result.toString();
    }

    @GetMapping("/Q2")
    @PreAuthorize("permitAll()")
    // GET http://localhost:8080/PreInterviewTests/Q2?array1=1,2,3,4,5&array2=2,3,1,0,5
    public List<Integer> findMissingNumbers(
            @RequestParam String array1,
            @RequestParam String array2
    ) {
        List<Integer> list1 = parseStringToList(array1);
        List<Integer> list2 = parseStringToList(array2);

        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);

        set1.removeAll(set2);

        return new ArrayList<>(set1);
    }

    private List<Integer> parseStringToList(String str) {
        return Stream.of(str.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @GetMapping("/Q3")
    @PreAuthorize("permitAll()")
    // GET http://localhost:8080/PreInterviewTests/Q3?array1=1,2,3,4,5&array2=2,3,1,0,5
    public List<Integer> findCommonNumbers(
            @RequestParam List<Integer> array1,
            @RequestParam List<Integer> array2
    ) {
        Set<Integer> set1 = new HashSet<>(array1);
        Set<Integer> set2 = new HashSet<>(array2);

        set1.retainAll(set2);

        return new ArrayList<>(set1);
    }

    @GetMapping("/Q4")
    @PreAuthorize("permitAll()")
    //GET http://localhost:8080/PreInterviewTests/Q4?array1=1,2,3,4,5&array2=2,3,1,0,5
    public List<Integer> mergeAndDisplayUnique(
            @RequestParam List<Integer> array1,
            @RequestParam List<Integer> array2
    ) {
        List<Integer> mergedArray = new ArrayList<>(array1);
        mergedArray.addAll(array2);

        Set<Integer> uniqueElements = new HashSet<>(mergedArray);

        return new ArrayList<>(uniqueElements);
    }

    @GetMapping("/Q5")
    @PreAuthorize("permitAll()")
    // GET http://localhost:8080/PreInterviewTests/Q5?array=30,1,5,16,19,21,2,55&target=17
    public Integer findClosestInteger(
            @RequestParam int[] array,
            @RequestParam int target
    ) {
        Integer closest = null;
        int minDiff = Integer.MAX_VALUE;

        for (int num : array) {
            int diff = Math.abs(num - target);
            if (diff < minDiff) {
                closest = num;
                minDiff = diff;
            }
        }

        return closest;
    }
}