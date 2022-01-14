package com.example.completablefuture.service;

import com.example.completablefuture.entity.Pirates;
import com.example.completablefuture.repository.PirateRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PirateAppServiceImpl implements PirateAppService{
    @Autowired
    private PirateRepository repository;

    Object target;
    Logger logger = (Logger) LoggerFactory.getLogger(PirateAppService.class);

    @Async
    public CompletableFuture<List<Pirates>> savePirate(MultipartFile file) throws Exception {
        long start = System.currentTimeMillis();
        List<Pirates> pirates = parseCSVFile(file);
        logger.info("saving list of pirates of size {}", pirates.size(), "" + Thread.currentThread().getName());
        for (Pirates pirate: pirates) {

            pirate = repository.save(pirate);
            System.out.println(pirate.getEmail());
        }
        long end = System.currentTimeMillis();
        logger.info("Total time {}", (end - start));
        return CompletableFuture.completedFuture(pirates);
    }
    @Async
    public CompletableFuture<List<Pirates>> getAllPirates(){
        logger.info("get list of pirates by "+Thread.currentThread().getName());
        List<Pirates> pirates=repository.findAll();
        return CompletableFuture.completedFuture(pirates);
    }
    @Async
    @Override
    public Pirates getPirate(String crewid) {
        return null;
    }



    private List<Pirates> parseCSVFile(final MultipartFile file) throws Exception {
        final List<Pirates> pirates = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final Pirates pirate = new Pirates();


                    pirate.setId(Integer.parseInt(data[0]));
                    pirate.setSnailPhoneNo(data[1]);
                    pirate.setFirst_name(data[2]);
                    pirate.setLast_name(data[3]);
                    pirate.setEmail(data[4]);
                    pirate.setIp(data[5]);


                    pirates.add(pirate);
                }
                return pirates;
            }
        } catch (final IOException e) {
            logger.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }
}
