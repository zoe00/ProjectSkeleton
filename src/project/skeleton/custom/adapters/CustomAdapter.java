package project.skeleton.custom.adapters;

import java.util.List;

import project.skeleton.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CustomAdapter extends BaseAdapter {

	private Context context;
	private List<String> data;

	public CustomAdapter(Context context, List<String> c) {
		this.context = context;   
		data = c;
	}

	public Object getItem(int position) {
		return data.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder{
		public TextView text;
	}

	public View getView(final int position, View convertView, ViewGroup viewGroup) {

		ViewHolder holder;
		try{    		
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.rowview, null);
				holder = new ViewHolder();
				holder.text = (TextView)convertView.findViewById(R.id.text);
				convertView.setTag(holder);
			}
			else
				holder=(ViewHolder)convertView.getTag();
			
			holder.text.setText(data.get(position));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return convertView;
	} 


	public int getCount() {
		return data.size();
	}

}
