package in.shgupta.safe_u;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by shubhanshugupta on 03/01/18.
 */

public class danger extends AppCompatActivity {
    Button addno = (Button) findViewById(R.id.addno);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangerhome);
        addno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(danger.this,addcontacts.class);
                startActivity(i);
            }
        });
    }
}
