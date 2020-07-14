package bpc.dis.utilities.DownloadManager;

import bpc.dis.utilities.PermissionHelper.Enum.PermissionType;

public class DownloadRequest {

    private long id;
    private boolean deleteFileAfterDownload;
    private boolean deleteFilePreDownload;
    private String title;
    private String description;
    private String downloadUrl;
    private String filename;
    private DownloadResponse downloadResponse;
    private PermissionType permissionType;

    public static DownloadRequestBuilder Builder() {
        return new DownloadRequestBuilder();
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public boolean isDeleteFilePreDownload() {
        return deleteFilePreDownload;
    }

    public void setDeleteFilePreDownload(boolean deleteFilePreDownload) {
        this.deleteFilePreDownload = deleteFilePreDownload;
    }

    public boolean isDeleteFileAfterDownload() {
        return deleteFileAfterDownload;
    }

    public void setDeleteFileAfterDownload(boolean deleteFileAfterDownload) {
        this.deleteFileAfterDownload = deleteFileAfterDownload;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public DownloadResponse getDownloadResponse() {
        return downloadResponse;
    }

    public void setDownloadResponse(DownloadResponse downloadResponse) {
        this.downloadResponse = downloadResponse;
    }

}