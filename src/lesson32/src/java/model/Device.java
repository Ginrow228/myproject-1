package model;

import java.util.Date;

public class Device {
    private Long id;
    private Long networkId;
    private String name;
    private String ipAddress;
    private String macAddress;
    private String type;
    private String status;
    private Date created_at;

    public Device(String name, String ipAddress, String macAddress, String type, String status) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.type = type;
        this.status = status;
    }

    public Device(Long id, Long networkId, String name, String ipAddress, String macAddress, String type, String status, Date created_at) {
        this.id = id;
        this.networkId = networkId;
        this.name = name;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.type = type;
        this.status = status;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public Long getNetworkId() {
        return networkId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setNetworkId(Long networkId) {
        this.networkId = networkId;
    }
}
