package bpc.dis.utilities.DeviceHelper;

import java.io.Serializable;

public class DeviceInfo implements Serializable {

    private String uniqueId;
    private String deviceName;
    private String screenSize;
    private int resolution;
    private int osVersion;
    private String buildSerial;

    public String getBuildSerial() {
        return buildSerial;
    }

    void setBuildSerial(String buildSerial) {
        this.buildSerial = buildSerial;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getScreenSize() {
        return screenSize;
    }

    void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public int getResolution() {
        return resolution;
    }

    void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public int getOsVersion() {
        return osVersion;
    }

    void setOsVersion(int osVersion) {
        this.osVersion = osVersion;
    }

}