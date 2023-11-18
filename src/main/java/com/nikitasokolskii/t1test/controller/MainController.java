package com.nikitasokolskii.t1test.controller;

import com.nikitasokolskii.t1test.payload.RequestDetails;
import com.nikitasokolskii.t1test.service.MainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Nikita Sokolskii
 */
@RestController
@RequestMapping("/api/v1/countCharacters")
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping
    public ResponseEntity<Map<Character, Integer>> count(@RequestBody RequestDetails requestDetails) {
        return ResponseEntity.ok(this.mainService.countCharacters(requestDetails.getInput()));
    }
}
