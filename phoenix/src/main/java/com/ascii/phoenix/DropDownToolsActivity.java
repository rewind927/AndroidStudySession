package com.ascii.phoenix;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ascii.phoenix.refresh_view.PullToRefreshView;
import com.ascii.phoenix.toolview.DropDownToolsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DropDownToolsActivity extends ActionBarActivity {

	public static final int REFRESH_DELAY = 2000;

	public static final String KEY_ICON = "icon";
	public static final String KEY_COLOR = "color";

	protected List<Map<String, Integer>> mSampleList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drop_down_tools);

		initData();

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(new SampleAdapter());
	}

	private void initData() {
		Map<String, Integer> map;
		mSampleList = new ArrayList<>();

		int[] icons = {
				R.drawable.icon_1,
				R.drawable.icon_2,
				R.drawable.icon_3};

		int[] colors = {
				R.color.saffron,
				R.color.eggplant,
				R.color.sienna};

		for (int i = 0; i < icons.length; i++) {
			map = new HashMap<>();
			map.put(KEY_ICON, icons[i]);
			map.put(KEY_COLOR, colors[i]);
			mSampleList.add(map);
		}
	}

	private class SampleAdapter extends RecyclerView.Adapter<SampleHolder> {

		@Override
		public SampleHolder onCreateViewHolder(ViewGroup parent, int pos) {
			View view = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.phoenix_list_item, parent, false);
			return new SampleHolder(view);
		}

		@Override
		public void onBindViewHolder(SampleHolder holder, int pos) {
			Map<String, Integer> data = mSampleList.get(pos);
			holder.bindData(data);
		}

		@Override
		public int getItemCount() {
			return mSampleList.size();
		}
	}

	private class SampleHolder extends RecyclerView.ViewHolder {

		private View mRootView;
		private ImageView mImageViewIcon;

		private Map<String, Integer> mData;

		public SampleHolder(View itemView) {
			super(itemView);

			mRootView = itemView;
			mImageViewIcon = (ImageView) itemView.findViewById(R.id.image_view_icon);
		}

		public void bindData(Map<String, Integer> data) {
			mData = data;

			mRootView.setBackgroundResource(mData.get(KEY_COLOR));
			mImageViewIcon.setImageResource(mData.get(KEY_ICON));
		}
	}
}
