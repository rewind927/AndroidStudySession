package tyrantgit.widget.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

import tyrantgit.widget.HeartLayout;
import tyrantgit.widget.R;


public class HeartLayoutActivity extends Activity {

    private Random mRandom = new Random();
    private HeartLayout mHeartLayout;
    private ImageView mHeartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartlayout);

        mHeartLayout = (HeartLayout) findViewById(R.id.heart_layout);
        mHeartButton = (ImageView) findViewById(R.id.heart_btn);
        mHeartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRandomBear()) {
                    mHeartLayout.addHeart(randomColor(), R.drawable.bear, R.drawable.bear_border);
                } else {
                    mHeartLayout.addHeart(randomColor(), R.drawable.heart, R.drawable.heart_border);
                }
            }
        });
    }

    private int randomColor() {
        return Color.rgb(mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255));
    }

    private boolean isRandomBear() {
        return mRandom.nextInt(50) % 5 == 0;
    }
}
