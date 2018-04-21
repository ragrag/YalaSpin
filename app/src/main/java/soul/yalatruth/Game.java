package soul.yalatruth;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity  {

protected ArrayList<Player> playerList ;
Button but1;
    Button but2;
    Button Q1;
    Button Q2;
    int questions[];
int turn;
int rep;
    private AdView mAdView;
    private Random rand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);


        rand= new Random();
        turn =0;
rep=0;
       but1 = (Button) findViewById(R.id.butans);
        but2 = (Button) findViewById(R.id.butpass);
        Q1=  (Button) findViewById(R.id.q1);
         Q2 = (Button) findViewById(R.id.q2);
        but1.setVisibility(View.INVISIBLE);
        but2.setVisibility(View.INVISIBLE);

        questions = new int[] {R.string.q1,R.string.q2,R.string.q3,R.string.q4,R.string.q5,R.string.q6,R.string.q7,R.string.q8,R.string.q9,
                R.string.q10,R.string.q11,R.string.q12,R.string.q13,R.string.q14,R.string.q15,R.string.q16,R.string.q17,R.string.q18,
                R.string.q19,R.string.q20,R.string.q21,R.string.q22,R.string.q23,R.string.q24,R.string.q25,R.string.q26,R.string.q27,R.string.q28,R.string.q29,R.string.q30
                , R.string.q31,R.string.q32,R.string.q33,R.string.q34,R.string.q35,

                R.string.q36,R.string.q37,R.string.q38,R.string.q39,R.string.q40,R.string.q41,R.string.q42,R.string.q43,R.string.q44,
                R.string.q45,R.string.q46 , R.string.q47,R.string.q48,R.string.q49,R.string.q50,R.string.q51,
                R.string.q52,R.string.q53,R.string.q54,R.string.q55,R.string.q56,R.string.q57,R.string.q58,R.string.q59,R.string.q60,
                R.string.q61,R.string.q62 , R.string.q63,R.string.q64,R.string.q65,R.string.q66,R.string.q67,
                R.string.q68,R.string.q69,R.string.q70,R.string.q71,R.string.q72,R.string.q73,R.string.q74,R.string.q75,R.string.q76,
                R.string.q77,R.string.q78 , R.string.q79,R.string.q80,R.string.q81,R.string.q82,R.string.q83,
                R.string.q84,R.string.q85,R.string.q86,R.string.q87,R.string.q88,R.string.q89,R.string.q90,R.string.q91,R.string.q92,
                R.string.q93,R.string.q94 , R.string.q95,R.string.q96,R.string.q97,R.string.q98,R.string.q99,R.string.q100

        };



        playerList =  (ArrayList<Player>) getIntent().getExtras().getSerializable("my object");



refresh();
    }



    public void refresh()
    {
        if (turn == playerList.size())
        {
            turn =0;
            rep++;
        }


        TextView title = (TextView)findViewById(R.id.a0);
        TextView asker = (TextView)findViewById(R.id.a1);
        TextView asked = (TextView)findViewById(R.id.a3);

        but1.setVisibility(View.INVISIBLE);
        but2.setVisibility(View.INVISIBLE);
        Q1.setBackgroundResource(android.R.drawable.btn_default);
        Q2.setBackgroundResource(android.R.drawable.btn_default);
        int dex1 =  rand.nextInt(playerList.size());
        while (dex1==turn)
        {
            dex1 =rand.nextInt(playerList.size());
        }
        title.setText(playerList.get(turn).getName()+"'s Turn");
        asker.setText(playerList.get(dex1).getName());
        asked.setText(playerList.get(turn).getName());
        int dex2 =  rand.nextInt(100);
        int dex3 =  rand.nextInt(100);
        while (dex3==dex2)
        {
            dex3 =  rand.nextInt(100);
        }


        Q1.setText(questions[dex2]);
        Q2.setText(questions[dex3]);
    }


    public void q1_click(View view) {

Q2.setBackgroundResource(android.R.drawable.btn_default);
        Q1.setBackgroundColor(Color.parseColor("#ffb347"));
        but1.setVisibility(View.VISIBLE);
        but2.setVisibility(View.VISIBLE);
    }

    public void q2_click(View view) {
        Q1.setBackgroundResource(android.R.drawable.btn_default);
        Q2.setBackgroundColor(Color.parseColor("#ffb347"));
        but1.setVisibility(View.VISIBLE);
        but2.setVisibility(View.VISIBLE);

    }

    public void ans_click(View view) {
        playerList.get(turn).answered();
        turn++;
        refresh();

    }

    public void pass_click(View view) {
        playerList.get(turn).passed();
        turn++;
        refresh();


    }

    public void goto_score(View view) {
        Intent intent = new Intent(getApplicationContext(), Score.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("my object", playerList);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
     final   Intent intent = new Intent(this,Select_Players.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Are you sure you want to quit?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();



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
}
