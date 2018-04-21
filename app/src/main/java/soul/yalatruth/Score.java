package soul.yalatruth;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Score extends AppCompatActivity {
    private AdView mAdView;
    InterstitialAd mInterstitialAd;
    protected ArrayList<Player> playerList ;
protected ArrayList<Player> playerList_sorted;
    LinearLayout layout;
    TextView tvplayer;
private String lang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);





        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.inter));
        AdRequest adRequest2 = new AdRequest.Builder()
                .build();
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });


        playerList =  (ArrayList<Player>) getIntent().getExtras().getSerializable("my object");
        playerList_sorted = playerList;
        Collections.sort(playerList_sorted);




       layout = (LinearLayout) findViewById(R.id.scorelayout);

        for (int i=playerList.size()-1;i>=0;i--) {
            String player = playerList_sorted.get(i).toString();

            tvplayer = new TextView(this);
            tvplayer.setTextColor(Color.parseColor("#FFFFFF"));
            tvplayer.setText(player);
            tvplayer.setTextSize(20);
            tvplayer.setGravity(Gravity.CENTER);


            layout.addView(tvplayer);

        }
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
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
}
