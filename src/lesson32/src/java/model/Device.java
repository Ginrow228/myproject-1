package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Device {
    private Long id;
    private Long networkId;
    private String name;
    private String ipAddress;
    private String macAddress;
    private String type;
    private String status;
    private Date created_at;
    private List<Connection> connections;

    public Device(String name, String ipAddress, String macAddress, String type, String status) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.type = type;
        this.status = status;
        this.connections = new ArrayList<>();
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
        this.connections = new ArrayList<>();
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void addConnection(Connection connection) {
        this.connections.add(connection);
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
