package com.example.completablefuture.controller;

import com.example.completablefuture.entity.Pirates;
import com.example.completablefuture.service.PirateAppService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class PirateController {
    @Autowired
    private PirateAppService service;

    @PostMapping(value = "/pirates", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity savePirates(@RequestParam(value = "files") MultipartFile @NotNull [] files) throws Exception {
        for (MultipartFile file : files) {
            service.savePirate(file);
            System.out.println(file);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/pirates", produces = "application/json")
    public CompletableFuture<ResponseEntity> findAllPirates() {
        return  service.getAllPirates().thenApply(ResponseEntity::ok);
    }


    @GetMapping(value = "/piratesByThread", produces = "application/json")
    public  ResponseEntity getPirates(){
        CompletableFuture<List<Pirates>> pirates1=service.getAllPirates();
        CompletableFuture<List<Pirates>> pirates2=service.getAllPirates();
        CompletableFuture<List<Pirates>> pirates3=service.getAllPirates();
        CompletableFuture.allOf(pirates1,pirates2,pirates3).join();
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
