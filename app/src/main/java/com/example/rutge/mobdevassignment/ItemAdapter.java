package com.example.rutge.mobdevassignment;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private Context mCtx;
    private List<RecyclerItem> RecyclerItemList;

    public ItemAdapter(Context mCtx, List<RecyclerItem> recyclerItemList) {
        this.mCtx = mCtx;
        RecyclerItemList = recyclerItemList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_layout, null);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        RecyclerItem item = RecyclerItemList.get(position);

        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                RecyclerItemList.remove(holder.getAdapterPosition());
                notifyItemRemoved(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return RecyclerItemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        public TextView title, description;
        public ConstraintLayout layout;

        public ItemViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.editTextTitle);
            description = itemView.findViewById(R.id.editTextDescription);
            layout = itemView.findViewById(R.id.constraintLayout);
        }
    }


}
