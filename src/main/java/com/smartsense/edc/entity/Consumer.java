package com.smartsense.edc.entity;

import javax.persistence.*;

@Entity
@Table(name = "consumer")
public class Consumer {

    @Id
    @Column(name = "asset_id")
    private String assetId;

    @Column(name = "data")
    private String data;

    public Consumer() {
    }

    public Consumer(String assetId, String data) {
        this.assetId = assetId;
        this.data = data;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
