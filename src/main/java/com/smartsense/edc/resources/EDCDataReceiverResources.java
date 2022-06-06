package com.smartsense.edc.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartsense.edc.Repository.ConsumerRepository;
import com.smartsense.edc.Repository.ProviderRepository;
import com.smartsense.edc.entity.Consumer;
import com.smartsense.edc.entity.Provider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
public class EDCDataReceiverResources {

    private final ObjectMapper objectMapper;
    private final ProviderRepository providerRepository;
    private final ConsumerRepository consumerRepository;
    private final static Map<String, Object> assetData = new HashMap<>();
    private final static Map<String, Object> consumerAssetData = new HashMap<>();

    public EDCDataReceiverResources(ObjectMapper objectMapper, ProviderRepository providerRepository, ConsumerRepository consumerRepository) {
        this.objectMapper = objectMapper;
        this.providerRepository = providerRepository;
        this.consumerRepository = consumerRepository;
    }

    @PostMapping("/consumer/data/{assetId}")
    public Consumer consumerDataHook(@PathVariable("assetId") String assetId, @RequestBody  String data) throws IOException {
        System.out.println("DATA GET SUCCESSFULLY.."+data);
//        consumerAssetData.put(assetId,data);

        if (Objects.isNull(assetId) || Objects.isNull(data)){
            throw new RuntimeException("assetId or data can't be null");
        }
        Consumer consumer = new Consumer();
        consumer.setAssetId(assetId);
        consumer.setData(data);
        Consumer savedconsumer = consumerRepository.save(consumer);
        return savedconsumer;
    }

    @GetMapping("/provider/data/webhook/{assetId}")
    public String providerDataHook(@PathVariable("assetId") String assetId) throws IOException {
        System.out.println("READ DATA FROM PROVIDER..");
//        assetData.put(assetId,Map.of("AssetId",assetId,"Uuid", UUID.randomUUID().toString(),"Name","SacalonCP-"+UUID.randomUUID().toString()));
//        String data = objectMapper.writeValueAsString(assetData.get(assetId));

        Provider provider = providerRepository.findById(assetId).orElse(null);
        return provider.getData();
    }

    @PostMapping("/provider/data/webhook/{assetId}")
    public Provider writeProviderDataHook(@PathVariable("assetId") String assetId, @RequestBody String data) throws IOException {
        System.out.println("WRITE DATA TO PROVIDER.."+data);
        if (Objects.isNull(assetId) || Objects.isNull(data)){
            throw new RuntimeException("assetId or data can't be null");
        }
        Provider provider= new Provider();
        provider.setAssetId(assetId);
        provider.setData(data);
        Provider savedProvider = providerRepository.save(provider);
        return provider;
    }

    @GetMapping("asset/{assetId}")
    public ResponseEntity<Object> getAssetData(@PathVariable("assetId") String assetId){
        if(consumerAssetData.containsKey(assetId)){
            return ResponseEntity.status(HttpStatus.OK).body(assetData.get(assetId));
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<>());
        }
    }
}
