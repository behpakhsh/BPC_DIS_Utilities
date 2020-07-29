package bpc.dis.utilities.TimeLookup;

import org.apache.commons.net.ntp.NTPUDPClient;

import java.net.InetAddress;
import java.util.Date;

import bpc.dis.utilities.Listener.ObjectTaskResult;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TimeLookup {

    public static void getRealOnlineDate(ObjectTaskResult<Date> dateObjectTaskResult) {
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(
                Completable.fromAction(() -> {
                            Date date =
                                    new Date(new NTPUDPClient().getTime(InetAddress.getByName("time-a.nist.gov")).getReturnTime());
                            dateObjectTaskResult.onResult(date);
                        }

                )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(() -> {
                            if (!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        })
                        .subscribe()
        );
    }

}