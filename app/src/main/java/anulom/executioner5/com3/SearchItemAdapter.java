package anulom.executioner5.com3.anulom.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import anulom.executioner5.com3.anulom.R;

/**
 * Created by anulom on 28/6/17.
 */

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {

    private List<String> getAlldataList;
    private Context context;


    public SearchItemAdapter(List<String> names, Context context) {
        this.getAlldataList = new ArrayList<>(names);
        this.context = context;

    }

    @Override
    public SearchItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchItemAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(getAlldataList.get(position));
    }

    @Override
    public int getItemCount() {
        return getAlldataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;

        public ViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.name);
        }
    }

    public List<String> updateData(List<String> datas, String text) {


        ArrayList<String> newNames = new ArrayList<>();
        for (String name : datas) {

            if (name.contains(text)) {
                newNames.add(name);
            }
        }

        return newNames;
    }

    public void setItems(List<String> datas) {
        getAlldataList = new ArrayList<>(datas);
    }
}