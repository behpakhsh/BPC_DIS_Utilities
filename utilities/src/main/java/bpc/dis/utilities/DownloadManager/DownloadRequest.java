package bpc.dis.utilities.DownloadManager;

import java.util.List;

public class DownloadRequest {

    private long id;
    private boolean deleteFileAfterDownload;
    private boolean deleteFilePreDownload;
    private String title;
    private String description;
    private String downloadUrl;
    private String filename;
    private DownloadResponse downloadResponse;
    private List<DownloadRequestHeader> headerParams;

    public static DownloadRequestBuilder builder() {
        return new DownloadRequestBuilder();
    }

    public List<DownloadRequestHeader> getHeaderParams() {
        return headerParams;
    }

    public void setHeaderParams(List<DownloadRequestHeader> headerParams) {
        this.headerParams = headerParams;
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