package bpc.dis.utilities.DownloadManager;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

public class DownloadReceiver extends BroadcastReceiver {

    private long id;
    private DownloadResponse downloadResponse;

    public DownloadReceiver(long id, DownloadResponse downloadResponse) {
        this.id = id;
        this.downloadResponse = downloadResponse;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0L);
        if (id == this.id) {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            if (downloadManager != null) {
                DownloadManager.Query query = new DownloadManager.Query();
                if (query != null) {
                    Cursor cursor = downloadManager.query(query);
                    if (cursor.moveToFirst()) {
                        downloadResponse.onDownloadPercent(calculateDownloadPercent(cursor));
                        int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                        if (status == DownloadManager.STATUS_SUCCESSFUL) {
                            downloadResponse.onDownloadFinish();
                        } else if (status == DownloadManager.STATUS_FAILED) {
                            downloadResponse.onDownloadFailed();
                        }
                    }
                }
            }
        }
    }

    private int calculateDownloadPercent(Cursor cursor) {
        int totalBytes = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
        int downloadedBytes = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
        return (downloadedBytes * 100) / totalBytes;
    }

}