package bpc.dis.utilities.DownloadManager;

import java.util.List;

public class DownloadRequestBuilder {

    private boolean deleteFileAfterDownload = false;
    private boolean deleteFilePreDownload = true;
    private String title = "";
    private String description = "";
    private String downloadUrl = "";
    private String filename = "";
    private DownloadResponse downloadResponse;
    private List<DownloadRequestHeader> headerParams;

    public List<DownloadRequestHeader> getHeaderParams() {
        return headerParams;
    }

    public DownloadRequestBuilder setHeaderParams(List<DownloadRequestHeader> headerParams) {
        this.headerParams = headerParams;
        return this;
    }

    public boolean isDeleteFilePreDownload() {
        return deleteFilePreDownload;
    }

    public DownloadRequestBuilder setDeleteFilePreDownload(boolean deleteFilePreDownload) {
        this.deleteFilePreDownload = deleteFilePreDownload;
        return this;
    }

    public boolean isDeleteFileAfterDownload() {
        return deleteFileAfterDownload;
    }

    public DownloadRequestBuilder setDeleteFileAfterDownload(boolean deleteFileAfterDownload) {
        this.deleteFileAfterDownload = deleteFileAfterDownload;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public DownloadRequestBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DownloadRequestBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public DownloadRequestBuilder setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public DownloadRequestBuilder setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public DownloadResponse getDownloadResponse() {
        return downloadResponse;
    }

    public DownloadRequestBuilder setDownloadResponse(DownloadResponse downloadResponse) {
        this.downloadResponse = downloadResponse;
        return this;
    }

    public DownloadRequest build() {
        DownloadRequest downloadRequest = new DownloadRequest();
        downloadRequest.setTitle(getTitle());
        downloadRequest.setDeleteFilePreDownload(isDeleteFilePreDownload());
        downloadRequest.setDeleteFileAfterDownload(isDeleteFileAfterDownload());
        downloadRequest.setDescription(getDescription());
        downloadRequest.setDownloadUrl(getDownloadUrl());
        downloadRequest.setFilename(getFilename());
        downloadRequest.setDownloadResponse(getDownloadResponse());
        downloadRequest.setHeaderParams(getHeaderParams());
        return downloadRequest;
    }

}