package com.smartsense.edc.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EDCDataReceiverResources {

    private final ObjectMapper objectMapper;

    private final static Map<String, Object> assetData = new HashMap<>();

    public EDCDataReceiverResources(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/consumer/data/webhook")
    public String poc(@RequestBody  String data) throws IOException {
        System.out.println("DATA GET SUCCESSFULLY.."+data);
        Map<String, Object> asset = objectMapper.readValue(data,Map.class);
        Map<String, String> properties = (Map<String, String>) asset.get("properties");
        String assetId = properties.get("asset:prop:id").toString();
        assetData.put(assetId, asset);
        return "Got it!";
    }

    @GetMapping("asset/{assetId}")
    public ResponseEntity<Object> getAssetData(@PathVariable("assetId") String assetId){
        if(assetData.containsKey(assetId)){
            return ResponseEntity.status(HttpStatus.OK).body(assetData.get(assetId));
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<>());
        }
    }
}
