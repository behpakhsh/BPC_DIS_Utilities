package bpc.dis.utilities.GoogleMapHelper;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import bpc.dis.utilities.VectorHelper.VectorHelper;

public class GoogleMapHelper {

    public static void drawSpaceLine(GoogleMap googleMap, LatLng point1, LatLng point2, int borderColor, int borderWidth) {
        Location sourceLocation = new Location("");
        sourceLocation.setLatitude(point1.latitude);
        sourceLocation.setLongitude(point1.longitude);
        Location destinationLocation = new Location("");
        destinationLocation.setLatitude(point2.latitude);
        destinationLocation.setLongitude(point2.longitude);
        ArrayList<Location> locations = new ArrayList<>();
        locations.add(destinationLocation);
        locations.add(sourceLocation);
        PolylineOptions options = new PolylineOptions();
        options.color(borderColor);
        options.width(borderWidth);
        options.visible(true);
        for (Location location : locations) {
            options.add(new LatLng(location.getLatitude(), location.getLongitude()));
        }
        googleMap.addPolyline(options);
    }

    public static void setMark(Context context, GoogleMap googleMap, String title, int vectorRes, double lat, double lng) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(lat, lng));
        BitmapDescriptor bitmapDescriptor = VectorHelper.bitmapDescriptorFromVector(context, vectorRes);
        if (bitmapDescriptor != null) {
            markerOptions.icon(bitmapDescriptor);
        }
        markerOptions.title(title);
        googleMap.addMarker(markerOptions).showInfoWindow();
    }

    public static void moveCameraBetweenPoints(GoogleMap googleMap, LatLng point1, LatLng point2) {
        List<LatLng> points = new ArrayList<>();
        points.add(point1);
        points.add(point2);
        double minLat = 0;
        double maxLat = 0;
        double minLon = 0;
        double maxLon = 0;
        for (LatLng point : points) {
            maxLat = Math.max(point.latitude, Integer.MAX_VALUE);
            minLat = Math.min(point.latitude, Integer.MAX_VALUE);
            maxLon = Math.max(point.longitude, Integer.MAX_VALUE);
            minLon = Math.min(point.longitude, Integer.MAX_VALUE);
        }
        LatLngBounds latLngBounds = new LatLngBounds.Builder().include(new LatLng(maxLat, maxLon)).include(new LatLng(minLat, minLon)).build();
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 50));
    }

}
