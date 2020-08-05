package bpc.dis.utilities.DownloadManager;

import java.io.File;

public abstract class DownloadResponse {

    public void onDownloadStarted(long id) {

    }

    public void onDownloadFinish(File file) {

    }

    public void onDownloadFinish(byte[] bytes) {

    }

    public void onDownloadFinish(String path) {

    }

    public void onDownloadFailed() {

    }

    public void onDownloadFinish() {

    }

    public void onDownloadPercent(int percent) {

    }

}