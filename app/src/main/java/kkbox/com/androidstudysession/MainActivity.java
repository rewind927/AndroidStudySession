package kkbox.com.androidstudysession;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.johnny.materialripplelayout.activity.RippleActivity;

import kkbox.com.circluarfloatingactionmenu.activity.MenuWithFABActivity;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new ContentFragment())
					.commit();
		}
	}

	public static class ContentFragment extends Fragment implements AdapterView.OnItemClickListener {

		private ListView demosListView;

		public ContentFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		                         Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_demo, container, false);
			// 要新增 demo 的入口請修改 arrays.xml 裡的 entries item
			String[] items = getResources().getStringArray(R.array.entries);
			ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
			demosListView = (ListView) rootView.findViewById(R.id.demosListView);
			demosListView.setAdapter(simpleAdapter);
			demosListView.setOnItemClickListener(this);
			return rootView;
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// 要新增 demo 的入口請放這
			switch (position) {
				case 0:
					startActivity(new Intent(getActivity(), MenuWithFABActivity.class));
					break;
				case 1:
					startActivity(new Intent(getActivity(), RippleActivity.class));
					break;
			}
		}
	}
}