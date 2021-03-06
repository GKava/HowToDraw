package guide.drawings.drawing_3d.howtodraw;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.codemybrainsout.ratingdialog.RatingDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import guide.drawings.drawing_3d.howtodraw.Fragments.MainFragment;

public class MainActivity extends AppCompatActivity  {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if (findViewById(R.id.fragment_container)!=null){
            if (savedInstanceState!=null){
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new MainFragment()).commit();
        }

        // Просит поставить оценку после 2 запуска, спустя 20 сек
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rating();
            }
        }, 20000);

    }

    public void rating (){

        final RatingDialog ratingDialog = new RatingDialog.Builder(MainActivity.this)
                .session(2)
                .threshold(5)
                .onRatingBarFormSumbit(new RatingDialog.Builder.RatingDialogFormListener() {
                    @Override
                    public void onFormSubmitted(String feedback) {
                        String mailto = "mailto:pinrocketteam@gmail.com" +
                                "?subject=" + Uri.encode("Creative Mode user feedback") +
                                "&body=" + Uri.encode(feedback);

                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.setData(Uri.parse(mailto));
                        try {
                            startActivity(emailIntent);
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(MainActivity.this, ":(", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).build();

        ratingDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdView.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdView.pause();
    }
}
