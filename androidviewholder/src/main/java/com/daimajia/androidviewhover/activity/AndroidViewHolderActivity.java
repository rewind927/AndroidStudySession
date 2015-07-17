package com.daimajia.androidviewhover.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.daimajia.androidviewhover.R;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.androidviewhover.BlurLayout;

public class AndroidViewHolderActivity extends ActionBarActivity {

    private Context mContext;
    private BlurLayout mSampleLayout, mSampleLayout2, mSampleLayout3, mSampleLayout4, mSampleLayout5, mSampleLayout6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_androidviewholder);
        BlurLayout.setGlobalDefaultDuration(450);
        mSampleLayout = (BlurLayout)findViewById(R.id.blur_layout);
        View hover = LayoutInflater.from(mContext).inflate(R.layout.hover_sample, null);
	    hover.findViewById(R.id.heart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Tada)
                    .duration(550)
                    .playOn(v);
            }
        });
        hover.findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Swing)
                        .duration(550)
                        .playOn(v);
            }
        });
        mSampleLayout.setHoverView(hover);
        mSampleLayout.setBlurDuration(550);
        mSampleLayout.addChildAppearAnimator(hover, R.id.heart, Techniques.FlipInX, 550, 0);
        mSampleLayout.addChildAppearAnimator(hover, R.id.share, Techniques.FlipInX, 550, 250);
        mSampleLayout.addChildAppearAnimator(hover, R.id.more, Techniques.FlipInX, 550, 500);
        mSampleLayout.addChildDisappearAnimator(hover, R.id.heart, Techniques.FlipOutX, 550, 500);
        mSampleLayout.addChildDisappearAnimator(hover, R.id.share, Techniques.FlipOutX, 550, 250);
        mSampleLayout.addChildDisappearAnimator(hover, R.id.more, Techniques.FlipOutX, 550, 0);
        mSampleLayout.addChildAppearAnimator(hover, R.id.description, Techniques.FadeInUp);
        mSampleLayout.addChildDisappearAnimator(hover, R.id.description, Techniques.FadeOutDown);

        //sample 2
        mSampleLayout2 = (BlurLayout)findViewById(R.id.blur_layout2);
        View hover2 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample2, null);
        hover2.findViewById(R.id.avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Pretty Cool, Right?", Toast.LENGTH_SHORT).show();
            }
        });
        mSampleLayout2.setHoverView(hover2);
        mSampleLayout2.addChildAppearAnimator(hover2, R.id.description, Techniques.FadeInUp);
        mSampleLayout2.addChildDisappearAnimator(hover2, R.id.description, Techniques.FadeOutDown);
        mSampleLayout2.addChildAppearAnimator(hover2, R.id.avatar, Techniques.DropOut, 1200);
        mSampleLayout2.addChildDisappearAnimator(hover2, R.id.avatar, Techniques.FadeOutUp);
        mSampleLayout2.setBlurDuration(1000);

        //sample3
        mSampleLayout3 = (BlurLayout)findViewById(R.id.blur_layout3);
        View hover3 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample3, null);
        mSampleLayout3.setHoverView(hover3);
        mSampleLayout3.addChildAppearAnimator(hover3, R.id.eye, Techniques.Landing);
        mSampleLayout3.addChildDisappearAnimator(hover3, R.id.eye, Techniques.TakingOff);
        mSampleLayout3.enableZoomBackground(true);
        mSampleLayout3.setBlurDuration(1200);

        //sample 4

        mSampleLayout4 = (BlurLayout)findViewById(R.id.blur_layout4);
        View hover4 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample4,null);
        mSampleLayout4.setHoverView(hover4);
        mSampleLayout4.addChildAppearAnimator(hover4, R.id.cat, Techniques.SlideInLeft);
        mSampleLayout4.addChildAppearAnimator(hover4, R.id.mail, Techniques.SlideInRight);

        mSampleLayout4.addChildDisappearAnimator(hover4, R.id.cat, Techniques.SlideOutLeft);
        mSampleLayout4.addChildDisappearAnimator(hover4, R.id.mail, Techniques.SlideOutRight);

        mSampleLayout4.addChildAppearAnimator(hover4, R.id.content, Techniques.BounceIn);
        mSampleLayout4.addChildDisappearAnimator(hover4, R.id.content, Techniques.FadeOutUp);


        hover4.findViewById(R.id.cat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getWebPage = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia"));
                startActivity(getWebPage);
            }
        });

        hover4.findViewById(R.id.mail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"daimajia@gmail.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "About AndroidViewHover");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "I have a good idea about this project..");

                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });


	    //sample 5
	    mSampleLayout5 = (BlurLayout)findViewById(R.id.blur_layout5);
	    final View layout5 = findViewById(R.id.source5);
	    Palette.from(BitmapFactory.decodeResource(getResources(),
			    R.drawable.album)).generate(new Palette.PaletteAsyncListener() {
		    @Override
		    public void onGenerated(Palette palette) {
			    layout5.setBackgroundColor(palette.getDarkVibrantColor(palette.getDarkMutedColor(Color.DKGRAY)));
		    }
	    });
	    View hover5 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample5, null);
	    mSampleLayout5.setHoverView(hover5);
	    mSampleLayout5.addChildAppearAnimator(hover5, R.id.button_play, Techniques.BounceInLeft);
	    mSampleLayout5.addChildDisappearAnimator(hover5, R.id.button_play, Techniques.SlideOutLeft);
	    mSampleLayout5.addChildAppearAnimator(hover5, R.id.content, Techniques.BounceIn);
	    mSampleLayout5.addChildDisappearAnimator(hover5, R.id.content, Techniques.FadeOutUp);
	    mSampleLayout5.enableZoomBackground(true);
	    mSampleLayout5.setBlurDuration(600);
	    hover5.findViewById(R.id.button_play).setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("kkbox://playlist_144_186906"));
			    startActivity(intent);
		    }
	    });

	    //sample 6
	    mSampleLayout6 = (BlurLayout)findViewById(R.id.blur_layout6);
	    final View layout6 = findViewById(R.id.source6);
	    Palette.from(BitmapFactory.decodeResource(getResources(),
			    R.drawable.blur)).generate(new Palette.PaletteAsyncListener() {
		    @Override
		    public void onGenerated(Palette palette) {
			    layout6.setBackgroundColor(palette.getDarkVibrantColor(palette.getDarkMutedColor(Color.DKGRAY)));
		    }
	    });
	    View hover6 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample6, null);
	    mSampleLayout6.setHoverView(hover6);
	    mSampleLayout6.addChildAppearAnimator(hover6, R.id.button_play, Techniques.FadeIn);
	    mSampleLayout6.addChildDisappearAnimator(hover6, R.id.button_play, Techniques.FadeOut);
	    mSampleLayout6.addChildAppearAnimator(hover6, R.id.content, Techniques.Wave);
	    mSampleLayout6.addChildDisappearAnimator(hover6, R.id.content, Techniques.FadeOutDown);
	    mSampleLayout6.setBlurDuration(600);
	    hover6.findViewById(R.id.button_play).setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("kkbox://playspecial_548_758"));
			    startActivity(intent);
		    }
	    });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.androidviewholder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
