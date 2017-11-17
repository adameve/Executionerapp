package anulom.executioner5.com3.anulom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by anulom on 27/7/17.
 */

public class nodetails extends AppCompatActivity {
    TextView t1;
    String value;

    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.nodetails);
        value = getIntent().getStringExtra("value");
        t1 = (TextView) findViewById(R.id.textView5);
        t1.setText(value);

    }
}