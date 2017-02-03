package lehilti.mapdraws;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity  implements OnMapReadyCallback {
    GoogleMap m_map;
    boolean mapReady=false;
    LatLng casa = new LatLng(-23.503207,-46.720023);
    LatLng casa_hellen = new LatLng(-23.494063,-46.722515);
    LatLng sato = new LatLng(-23.496408,-46.728629);
    LatLng pastorinho = new LatLng(-23.506325,-46.716877);


    static final CameraPosition saopaulo = CameraPosition.builder()
            .target(new LatLng(-23.503207,-46.720023))
            .zoom(13)
            .bearing(0)
            .tilt(0)
            .build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady=true;
        m_map = googleMap;
        // desenha lina entre os pontos
        m_map.addPolyline(new PolylineOptions().geodesic(true)
                .add(casa)
                .add(casa_hellen)
                .add(sato)
                .add(pastorinho));
        // desenha um circulo no mapa
        Circle circle = m_map.addCircle(new CircleOptions()
                .center(casa)
                .radius(100)
                .strokeColor(Color.GREEN)
                .fillColor(Color.argb(64,0,255,0)));

        m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        flyto(saopaulo);

    }

    private void flyto(CameraPosition target){
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target),10000,null);
    }
}
