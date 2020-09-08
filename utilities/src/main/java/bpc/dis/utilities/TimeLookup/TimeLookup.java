package bpc.dis.utilities.TimeLookup;

import android.annotation.SuppressLint;

import org.apache.commons.net.ntp.NTPUDPClient;

import java.net.InetAddress;
import java.util.Date;

import bpc.dis.utilities.Listener.ObjectTaskResult;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TimeLookup {

    @SuppressLint("CheckResult")
    public static void getRealOnlineDate(ObjectTaskResult<Date> dateObjectTaskResult) {
        io.reactivex.Observable.fromCallable(() -> {
            Date date =
                    new Date(new NTPUDPClient().getTime(InetAddress.getByName("time-a.nist.gov")).getReturnTime());
            return date;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<Date>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Date date) {
                        dateObjectTaskResult.onResult(date);
                    }

                    @Override
                    public void onError(Throwable e) {
                        dateObjectTaskResult.onResult(null);
                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }

}