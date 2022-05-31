package com.smartsense.edc.entity;

import javax.persistence.*;

@Entity
@Table(name = "provider")
public class Provider {
    @Id
    @Column(name = "asset_id")
    private String assetId;

    @Column(name = "data")
    private String data;

    public Provider() {
    }

    public Provider(String assetId, String data) {
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
