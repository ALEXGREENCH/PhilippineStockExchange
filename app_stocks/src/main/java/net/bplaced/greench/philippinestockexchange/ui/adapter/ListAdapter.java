package net.bplaced.greench.philippinestockexchange.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.bplaced.greench.philippinestockexchange.R;
import net.bplaced.greench.philippinestockexchange.model.Stock;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements IUpdate {

    private List<Stock> stockList;

    public ListAdapter(List<Stock> stockList) {
        this.stockList = stockList;
    }

    @Override
    public void setStocks(List<Stock> stockList) {
        this.stockList = stockList;
        Log.i("TAG", "list size adapter = " + stockList.size());
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stock stockItem = stockList.get(position);
        holder.name.setText(stockItem.getName());
        holder.volume.setText(String.valueOf(stockItem.getVolume()));

        String formattedDouble = new DecimalFormat("#0.00").format(stockItem.getPrice().getAmount());
        holder.amount.setText(formattedDouble);
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.volume)
        TextView volume;
        @BindView(R.id.amount)
        TextView amount;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
