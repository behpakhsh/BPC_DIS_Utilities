package bpc.dis.utilities.DownloadManager;

import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DownloadManager {

    public void download(Context context, DownloadRequest downloadRequest) {
        android.app.DownloadManager downloadManager =
                (android.app.DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadManager != null) {
            DownloadResponse downloadResponse = getDownloadResponse(downloadRequest);
            downloadResponse.onDownloadPreStarted();
            android.app.DownloadManager.Request request = getDownloadManger(
                    downloadRequest.getHeaderParams(),
                    downloadRequest.getDownloadUrl(),
                    downloadRequest.getFilename(),
                    downloadRequest.getTitle(),
                    downloadRequest.getDescription()
            );
            downloadRequest.setId(downloadManager.enqueue(request));
            context.registerReceiver(new DownloadReceiver(
                            downloadRequest.getId(),
                            downloadResponse
                    ),
                    new IntentFilter(android.app.DownloadManager.ACTION_DOWNLOAD_COMPLETE)
            );
            downloadResponse.onDownloadStarted(downloadRequest.getId());
        }
    }

    private DownloadResponse getDownloadResponse(DownloadRequest downloadRequest) {
        return new DownloadResponse() {

            @Override
            public void onDownloadPreStarted() {
                super.onDownloadPreStarted();
                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/" + downloadRequest.getFilename();
                File file = new File(path);
                if (file.exists()) {
                    if (file.getName().equals(downloadRequest.getFilename())) {
                        if (downloadRequest.isDeleteFilePreDownload()) {
                            try {
                                FileUtils.forceDelete(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onDownloadStarted(long id) {
                super.onDownloadStarted(id);
                downloadRequest.getDownloadResponse().onDownloadStarted(id);
            }

            @Override
            public void onDownloadPercent(int percent) {
                super.onDownloadPercent(percent);
                downloadRequest.getDownloadResponse().onDownloadPercent(percent);
            }

            @Override
            public void onDownloadFinish(File file) {
                super.onDownloadFinish(file);
                downloadRequest.getDownloadResponse().onDownloadFinish(file);
            }

            @Override
            public void onDownloadFinish(byte[] bytes) {
                super.onDownloadFinish(bytes);
                downloadRequest.getDownloadResponse().onDownloadFinish(bytes);
            }

            @Override
            public void onDownloadFinish(String path) {
                super.onDownloadFinish(path);
                downloadRequest.getDownloadResponse().onDownloadFinish(path);
            }

            @Override
            public void onDownloadFinish() {
                super.onDownloadFinish();
                try {
                    String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/" + downloadRequest.getFilename();
                    File file = new File(path);
                    if (file.exists()) {
                        if (file.getName().equals(downloadRequest.getFilename())) {
                            downloadRequest.getDownloadResponse().onDownloadFinish(file);
                            if (downloadRequest.isDeleteFileAfterDownload()) {
                                FileUtils.forceDelete(file);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onDownloadFailed() {
                super.onDownloadFailed();
                downloadRequest.getDownloadResponse().onDownloadFailed();
            }

        };
    }

    private android.app.DownloadManager.Request getDownloadManger(List<DownloadRequestHeader> downloadRequestHeaders, String url, String filename, String title, String description) {
        android.app.DownloadManager.Request request = new android.app.DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(android.app.DownloadManager.Request.NETWORK_MOBILE | android.app.DownloadManager.Request.NETWORK_WIFI);
        request.setMimeType(getMimeFromFileName(url));
        request.setDescription(description);
        request.setTitle(title);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            request.setAllowedOverMetered(true);
        }
        request.setAllowedOverRoaming(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
        request.setNotificationVisibility(android.app.DownloadManager.Request.VISIBILITY_VISIBLE);

        if (downloadRequestHeaders != null) {
            for (DownloadRequestHeader downloadRequestHeader : downloadRequestHeaders) {
                request.addRequestHeader(
                        downloadRequestHeader.getKey(),
                        downloadRequestHeader.getValue()
                );
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            request.setRequiresCharging(false);
        }
        return request;
    }

    private String getMimeFromFileName(String url) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
    }

    public File getDownloadFile(String fileName) {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/" + fileName;
        File file = new File(path);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    public void checkDownloadStatus(Context context, String fileName, long downloadId, DownloadHistoryResponse downloadHistoryResponse) {
        if (fileName.equals("") || downloadId == 0) {
            downloadHistoryResponse.mustDownloadIt();
            return;
        }
        android.app.DownloadManager downloadManager = (android.app.DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadManager != null) {
            android.app.DownloadManager.Query query = new android.app.DownloadManager.Query();
            query.setFilterById(downloadId);
            Cursor cursor = downloadManager.query(query);
            if (cursor.moveToFirst()) {
                int status = cursor.getInt(cursor.getColumnIndex(android.app.DownloadManager.COLUMN_STATUS));
                if (status == android.app.DownloadManager.STATUS_SUCCESSFUL) {
                    downloadHistoryResponse.mustInstall(getDownloadFile(fileName));
                } else if (status == android.app.DownloadManager.STATUS_RUNNING) {
                    downloadHistoryResponse.downloadRunning();
                } else {
                    downloadHistoryResponse.mustDownloadIt();
                }
            } else {
                downloadHistoryResponse.mustDownloadIt();
            }
        } else {
            downloadHistoryResponse.mustDownloadIt();
        }
    }

}