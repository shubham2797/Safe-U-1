package in.shgupta.safe_u;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class danger extends AppCompatActivity {

    Button addno, danger;
    LocationManager locMan;
    LocationListener locLis;
    TextView ltln, ln;
    public double lat, lon;
    public static final String TAG = "location";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangerhome);
        addno = (Button) findViewById(R.id.addno);
        danger = (Button) findViewById(R.id.danger);
        ltln = (TextView) findViewById(R.id.ltln);
        ln = (TextView) findViewById(R.id.ln);
        addno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(danger.this, addcontacts.class);
                startActivity(i);
            }
        });
        locMan = (LocationManager) getSystemService(LOCATION_SERVICE);
        locLis = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();
                Log.d(TAG, "onLocationChanged: " + location.getLatitude());
                Log.d(TAG, "onLocationChanged: " + location.getLongitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locMan.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1,
                5,
                locLis
        );


       danger.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ltln.setText(String.valueOf(lat));
               ln.setText(String.valueOf(lon));
               String message = "http://maps.google.com/maps?saddr=" + lat + "," + lon;
               String number1 = models.getNo1();
               String number2 = models.getNo2();
               String number3 = models.getNo3();
               SmsManager smsManager = SmsManager.getDefault();
               StringBuffer smsBody = new StringBuffer();
               smsBody.append(Uri.parse(message));

                   android.telephony.SmsManager.getDefault().sendTextMessage("+918527231729", null, smsBody.toString(), null, null);
               /*
               else if(models.getNo2()!=null) {
                   android.telephony.SmsManager.getDefault().sendTextMessage(number2, null, smsBody.toString(), null, null);
               }
               else if(models.getNo3()!=null) {
                   android.telephony.SmsManager.getDefault().sendTextMessage(number3, null, smsBody.toString(), null, null);
               }else{
                   Toast.makeText(danger.this, "please add numbers", Toast.LENGTH_SHORT).show();
               } */
           }
       });
    }
}



