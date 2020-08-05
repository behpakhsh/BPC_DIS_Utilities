package bpc.dis.utilities.DownloadManager;

import java.io.File;

public interface DownloadHistoryResponse {

    void downloadRunning();

    void mustDownloadIt();

    void mustInstall(File file);

}