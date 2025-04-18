package model;

import java.util.Date;

public class Connection {
    private Long id;
    private Long deviceFromId;
    private Long deviceToId;
    private String connectionType;
    private String status;
    private Date createdAt;

    public Connection(Long id, Long deviceFromId, Long deviceToId, String connectionType, String status, Date createdAt) {
        this.id = id;
        this.deviceFromId = deviceFromId;
        this.deviceToId = deviceToId;
        this.connectionType = connectionType;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getDeviceFromId() {
        return deviceFromId;
    }

    public Long getDeviceToId() {
        return deviceToId;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDeviceFromId(Long deviceFromId) {
        this.deviceFromId = deviceFromId;
    }

    public void setDeviceToId(Long deviceToId) {
        this.deviceToId = deviceToId;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", deviceFromId=" + deviceFromId +
                ", deviceToId=" + deviceToId +
                ", connectionType='" + connectionType + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
