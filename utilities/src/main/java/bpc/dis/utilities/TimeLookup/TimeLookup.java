package bpc.dis.utilities.TimeLookup;

import org.apache.commons.net.ntp.NTPUDPClient;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

public class TimeLookup {

    public static Date getRealDate() {
        try {
            return new Date(new NTPUDPClient().getTime(InetAddress.getByName("time-a.nist.gov")).getReturnTime());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Date();
    }

}