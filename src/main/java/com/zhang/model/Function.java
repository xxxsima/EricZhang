package com.zhang.model;

/**
 * @Author: King
 * @Create: 2019-11-21 20:35
 * @Desc:
 **/
public class Function {
    private Long id;
    private String code;
    private String name;
    private String displayName;
    private  int position;
    private byte[] ioc;
    private  String platform;
    private String  remark;
    private  String runConfig;
    private Long parentId;
    private String icon;
    private boolean readOnly;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public byte[] getIoc() {
        return ioc;
    }

    public void setIoc(byte[] ioc) {
        this.ioc = ioc;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRunConfig() {
        return runConfig;
    }

    public void setRunConfig(String runConfig) {
        this.runConfig = runConfig;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
