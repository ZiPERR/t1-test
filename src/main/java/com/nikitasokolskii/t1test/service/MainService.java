package com.nikitasokolskii.t1test.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

/**
 * @author Nikita Sokolskii
 */
@Service
@Validated
public class MainService {

    public Map<Character, Integer> countCharacters(String input) {
        Map<Character, Integer> map = new HashMap<>();
        if (input == null) {
            input = "";
        }

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            map.merge(c, 1, Integer::sum);
        }

        return sortByValue(map);
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        Map<K, V> result = new LinkedHashMap<>();

        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
