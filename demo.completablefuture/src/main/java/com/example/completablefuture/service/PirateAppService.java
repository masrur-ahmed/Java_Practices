package com.example.completablefuture.service;

import com.example.completablefuture.entity.Pirates;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PirateAppService {
    public CompletableFuture<List<Pirates>> savePirate(MultipartFile file) throws Exception;
    public Pirates getPirate(String crewid);
    public CompletableFuture<List<Pirates>> getAllPirates();
}
