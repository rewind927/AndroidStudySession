package com.lorentzos.swipecards;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.lorentzos.swipecards.practice.SwipeFlingAdapterView;

import java.util.ArrayList;

public class PracticeSwipeCardsActivity extends Activity {

	private ArrayList<String> al;
	private ArrayAdapter<String> arrayAdapter;
	private int i;

	SwipeFlingAdapterView flingContainer;
	Button left;
	Button right;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice);
		flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
		left = (Button) findViewById(R.id.left);
		left.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				left();
			}
		});
		right = (Button) findViewById(R.id.right);
		right.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				right();
			}
		});

		al = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			al.add(Math.random()<0.5? "左" : "右");
		}

		arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al);

		flingContainer.setAdapter(arrayAdapter);
		flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
			@Override
			public boolean canRemoveCard(Object dataObject, boolean isLeft) {
				//TODO check left or right;
				return true;
			}

			@Override
			public void removeFirstObjectInAdapter() {
				// this is the simplest way to delete an object from the Adapter (/AdapterView)
				Log.d("LIST", "removed object!");
				al.remove(0);
				arrayAdapter.notifyDataSetChanged();
			}

			@Override
			public void onLeftCardExit(Object dataObject) {
				//Do something on the left!
				//You also have access to the original object.
				//If you want to use it just cast it (String) dataObject
				makeToast(PracticeSwipeCardsActivity.this, "Left!");
			}

			@Override
			public void onRightCardExit(Object dataObject) {
				makeToast(PracticeSwipeCardsActivity.this, "Right!");
			}

			@Override
			public void onAdapterAboutToEmpty(int itemsInAdapter) {
				// Ask for more data here
				al.add("XML ".concat(String.valueOf(i)));
				arrayAdapter.notifyDataSetChanged();
				Log.d("LIST", "notified");
				i++;
			}

			@Override
			public void onScroll(float scrollProgressPercent) {
				View view = flingContainer.getSelectedView();
				view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
				view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
			}
		});

		// Optionally add an OnItemClickListener
		flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
			@Override
			public void onItemClicked(int itemPosition, Object dataObject) {
				makeToast(PracticeSwipeCardsActivity.this, "Clicked!");
			}
		});

	}

	static void makeToast(Context ctx, String s) {
		Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
	}

	public void right() {
		/**
		 * Trigger the right event manually.
		 */
		flingContainer.getTopCardListener().selectRight();
	}

	public void left() {
		flingContainer.getTopCardListener().selectLeft();
	}

}
