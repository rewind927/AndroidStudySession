package com.daimajia.androidviewhover.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
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

    private Context context;
    private BlurLayout layoutSample, layoutSample2, layoutSample3, layoutSample4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_androidviewholder);
        BlurLayout.setGlobalDefaultDuration(450);
        layoutSample = (BlurLayout)findViewById(R.id.blur_layout);
        View hover = LayoutInflater.from(context).inflate(R.layout.hover_sample, null);
	    hover.findViewById(R.id.heart).setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    YoYo.with(Techniques.Tada).duration(550).playOn(v);
		    }
	    });
        hover.findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
		        YoYo.with(Techniques.Swing).duration(550).playOn(v);
	        }
        });
        layoutSample.setHoverView(hover);
        layoutSample.setBlurDuration(550);
        layoutSample.addChildAppearAnimator(hover, R.id.heart, Techniques.FlipInX, 550, 0);
        layoutSample.addChildAppearAnimator(hover, R.id.share, Techniques.FlipInX, 550, 250);
        layoutSample.addChildAppearAnimator(hover, R.id.more, Techniques.FlipInX, 550, 500);
        layoutSample.addChildDisappearAnimator(hover, R.id.heart, Techniques.FlipOutX, 550, 500);
        layoutSample.addChildDisappearAnimator(hover, R.id.share, Techniques.FlipOutX, 550, 250);
        layoutSample.addChildDisappearAnimator(hover, R.id.more, Techniques.FlipOutX, 550, 0);
        layoutSample.addChildAppearAnimator(hover, R.id.description, Techniques.FadeInUp);
        layoutSample.addChildDisappearAnimator(hover, R.id.description, Techniques.FadeOutDown);

        //sample 2
        layoutSample2 = (BlurLayout)findViewById(R.id.blur_layout2);
        View hover2 = LayoutInflater.from(context).inflate(R.layout.hover_sample2, null);
        hover2.findViewById(R.id.avatar).setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
		        Toast.makeText(context, "Pretty Cool, Right?", Toast.LENGTH_SHORT).show();
	        }
        });
        layoutSample2.setHoverView(hover2);
        layoutSample2.addChildAppearAnimator(hover2, R.id.description, Techniques.FadeInUp);
        layoutSample2.addChildDisappearAnimator(hover2, R.id.description, Techniques.FadeOutDown);
        layoutSample2.addChildAppearAnimator(hover2, R.id.avatar, Techniques.DropOut, 1200);
        layoutSample2.addChildDisappearAnimator(hover2, R.id.avatar, Techniques.FadeOutUp);
        layoutSample2.setBlurDuration(1000);

        //sample3
        layoutSample3 = (BlurLayout)findViewById(R.id.blur_layout3);
        View hover3 = LayoutInflater.from(context).inflate(R.layout.hover_sample3, null);
        layoutSample3.setHoverView(hover3);
        layoutSample3.addChildAppearAnimator(hover3, R.id.eye, Techniques.Landing);
        layoutSample3.addChildDisappearAnimator(hover3, R.id.eye, Techniques.TakingOff);
        layoutSample3.enableZoomBackground(true);
        layoutSample3.setBlurDuration(1200);

        //sample 4
        layoutSample4 = (BlurLayout)findViewById(R.id.blur_layout4);
        View hover4 = LayoutInflater.from(context).inflate(R.layout.hover_sample4,null);
        layoutSample4.setHoverView(hover4);
        layoutSample4.addChildAppearAnimator(hover4, R.id.cat, Techniques.SlideInLeft);
        layoutSample4.addChildAppearAnimator(hover4, R.id.mail, Techniques.SlideInRight);
        layoutSample4.addChildDisappearAnimator(hover4, R.id.cat, Techniques.SlideOutLeft);
        layoutSample4.addChildDisappearAnimator(hover4, R.id.mail, Techniques.SlideOutRight);
        layoutSample4.addChildAppearAnimator(hover4, R.id.content, Techniques.BounceIn);
        layoutSample4.addChildDisappearAnimator(hover4, R.id.content, Techniques.FadeOutUp);
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


	    //TODO: Practice 1-1 Add palette color to layout background
	    Palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.album)).generate(new Palette.PaletteAsyncListener() {
		    @Override
		    public void onGenerated(Palette palette) {
			   // use color palette.getDarkVibrantColor(palette.getDarkMutedColor(Color.DKGRAY));
		    }
	    });
	    //TODO: Practice 1-2 add child appear and disappear animator
	    //TODO: Practice 1-3 set play button intent
	    //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("kkbox://playlist_144_186906"));
	    //startActivity(intent);


	    //TODO: Practice 2
	    //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("kkbox://playspecial_548_758"));
	    //startActivity(intent);
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
