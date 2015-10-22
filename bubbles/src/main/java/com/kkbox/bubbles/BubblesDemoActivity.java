package com.kkbox.bubbles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.kkbox.bubbles.library.BubbleLayout;
import com.kkbox.bubbles.library.BubblesManager;
import com.kkbox.bubbles.library.OnInitializedCallback;

public class BubblesDemoActivity extends AppCompatActivity {
	private BubblesManager bubblesManager;
	private BubbleLayout bubbleLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bubbles_demo);

		// Initial the bubble manager.
		bubblesManager = new BubblesManager.Builder(this)
				.setTrashLayout(R.layout.bubble_trash_layout)
				.setInitializationCallback(new OnInitializedCallback() {
					@Override
					public void onInitialized() {
						Toast.makeText(BubblesDemoActivity.this, "onInitialized", Toast.LENGTH_SHORT).show();
					}
				}).build();
		// bindService
		bubblesManager.initialize();

		// Make the bubble layout.
		bubbleLayout = (BubbleLayout) LayoutInflater.from(this).inflate(R.layout.bubble_layout, null);
		// Set bubble click listener.
		bubbleLayout.setOnBubbleClickListener(new BubbleLayout.OnBubbleClickListener() {
			@Override
			public void onBubbleClick(BubbleLayout bubble) {
				Toast.makeText(BubblesDemoActivity.this, "onBubbleClick", Toast.LENGTH_SHORT).show();
			}
		});
		// Set bubble remove listener.
		bubbleLayout.setOnBubbleRemoveListener(new BubbleLayout.OnBubbleRemoveListener() {
			@Override
			public void onBubbleRemoved(BubbleLayout bubble) {
				Toast.makeText(BubblesDemoActivity.this, "onBubbleRemoved", Toast.LENGTH_SHORT).show();
			}
		});
		// Set Magnetism.
//		bubbleLayout.setShouldStickToWall(false);

		findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Add the bubble to windows.
				bubblesManager.addBubble(bubbleLayout, 60, 30);
				Toast.makeText(BubblesDemoActivity.this, "Add the bubble to windows.", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// unbindService
		bubblesManager.recycle();
	}
}
