package com.hada.virtual.hsm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hada.virtual.hsm.audit.AbstractAuditingEntity;
import com.hada.virtual.hsm.config.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "virtual_hsm_device")
public class VHSMDevice extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Size(max = 100)
    @Column(name = "device_name", length = 100)
    private String deviceName;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "virtual_hsm_device_data_record",
            joinColumns = {@JoinColumn(name = "virtual_hsm_device_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "data_record_id", referencedColumnName = "id")}
    )
    private Set<DataRecord> dataRecords;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<DataRecord> getDataRecords() {
        return dataRecords;
    }

    public void setDataRecords(Set<DataRecord> dataRecords) {
        this.dataRecords = dataRecords;
    }
}
