package soul.yalatruth;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;


public class Select_Players extends AppCompatActivity implements Serializable {
    LinearLayout layout;

    private ArrayList<Player> playerList;
    private String pname;
int count=-1;


    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select__players);


        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        layout = (LinearLayout) findViewById(R.id.mylin);

        playerList =  new <Player>ArrayList();
pname = " ";
    }



    public void refresh()
    {




                String player = playerList.get(count).getName();

                TextView tvplayer = new TextView(this);
                tvplayer.setTextColor(Color.parseColor("#FFFFFF"));
                tvplayer.setText(player);
        tvplayer.setTextSize(20);
        tvplayer.setGravity(Gravity.CENTER);


                layout.addView(tvplayer);


    }




    public void add_click(View view) {
count++;
     AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Player Name");


        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pname = input.getText().toString();
                playerList.add(new Player(pname));
                refresh();
                Toast.makeText(Select_Players.this,R.string.player_added,
                        Toast.LENGTH_LONG).show();


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


                builder.show();




    }

    public void goto_game(View view) {

if (playerList.size()<2)
{
    Toast.makeText(this, this.getString(R.string.player_min_number),
            Toast.LENGTH_LONG).show();
}

      else {
    Intent intent = new Intent(getApplicationContext(), Game.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("my object", playerList);
   intent.putExtras(bundle);
    startActivity(intent);
}

    }
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }




    public void onBackPressed()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}

