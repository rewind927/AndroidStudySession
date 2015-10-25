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

import com.ascii.phoenix.PhoenixActivity;
import com.daimajia.androidviewhover.activity.AndroidViewHolderActivity;
import com.daimajia.numberprogressbar.example.NumberProgressBarActivity;
import com.johnny.circleindicator.IndicatorDemoActivity;
import com.johnny.materialripplelayout.activity.RippleActivity;
import com.jpardogo.android.googleprogressbar.GoogleProgressBarActivity;
import com.karen.processbutton.activity.ProcessButtonActivity;
import com.kkbox.bubbles.BubblesDemoActivity;
import com.kkbox.flipviewpager.sample.activity.FriendsActivity;
import com.lorentzos.swipecards.ChooseSwipeCardsActivity;
import com.melnykov.fab.sample.FloatingActionButtonActivity;
import com.wendy.titanic.TitanicActivity;

import is.arontibo.library.sample.ElasticDownloadActivity;
import kkbox.com.circluarfloatingactionmenu.activity.MenuWithFABActivity;
import tyrantgit.widget.sample.HeartLayoutActivity;

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

		public ContentFragment() {}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_demo, container, false);
			// 要新增 demo 的入口請修改 arrays.xml 裡的 entries item
			String[] items = getResources().getStringArray(R.array.entries);
			ArrayAdapter<String> simpleAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
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
				case 2:
					startActivity(new Intent(getActivity(), NumberProgressBarActivity.class));
					break;
                case 3:
                    startActivity(new Intent(getActivity(), ElasticDownloadActivity.class));
                    break;
				case 4:
					startActivity(new Intent(getActivity(), FloatingActionButtonActivity.class));
					break;
				case 5:
					startActivity(new Intent(getActivity(), AndroidViewHolderActivity.class));
					break;
				case 6:
					startActivity(new Intent(getActivity(), GoogleProgressBarActivity.class));
					break;
				case 7:
					startActivity(new Intent(getActivity(), PhoenixActivity.class));
					break;
				case 8:
					startActivity(new Intent(getActivity(), ProcessButtonActivity.class));
					break;
				case 9:
					startActivity(new Intent(getActivity(), TitanicActivity.class));
					break;
				case 10:
					startActivity(new Intent(getActivity(), ChooseSwipeCardsActivity.class));
					break;
				case 11:
					startActivity(new Intent(getActivity(), FriendsActivity.class));
					break;
				case 12:
					startActivity(new Intent(getActivity(), IndicatorDemoActivity.class));
					break;
				case 13:
					startActivity(new Intent(getActivity(), BubblesDemoActivity.class));
					break;
                case 14:
                    startActivity(new Intent(getActivity(), HeartLayoutActivity.class));
                    break;
			}
		}
	}
}
