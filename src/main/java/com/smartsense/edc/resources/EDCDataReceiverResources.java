package com.smartsense.edc.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class EDCDataReceiverResources {

    private final ObjectMapper objectMapper;

    private final static Map<String, Object> assetData = new HashMap<>();
    private final static Map<String, Object> consumerAssetData = new HashMap<>();

    public EDCDataReceiverResources(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/consumer/data/{assetId}")
    public String consumerDataHook(@PathVariable("assetId") String assetId,@RequestBody  String data) throws IOException {
        System.out.println("DATA GET SUCCESSFULLY.."+data);
        consumerAssetData.put(assetId,data);
        return "Got it!";
    }

    @GetMapping("/provider/data/webhook/{assetId}")
    public String providerDataHook(@PathVariable("assetId") String assetId) throws IOException {
        System.out.println("READ DATA FROM PROVIDER..");
        assetData.put(assetId,Map.of("AssetId",assetId,"Uuid", UUID.randomUUID().toString(),"Name","SacalonCP-"+UUID.randomUUID().toString()));
        String data = objectMapper.writeValueAsString(assetData.get(assetId));
        return data;
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
