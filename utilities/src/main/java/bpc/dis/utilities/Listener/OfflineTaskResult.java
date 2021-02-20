package bpc.dis.utilities.Listener;

import java.util.List;

public interface OfflineTaskResult<T> {

    void onResult(List<T> ts);

}