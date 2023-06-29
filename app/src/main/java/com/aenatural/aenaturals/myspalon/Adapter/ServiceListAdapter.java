package com.aenatural.aenaturals.myspalon.Adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aenatural.aenaturals.R;
import com.aenatural.aenaturals.apiservices.datamodels.Service;

import java.util.List;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListViewHolder> {
    private List<Service> serviceList;

    public ServiceListAdapter(List<Service> list) {
        this.serviceList = list;
    }

    @NonNull
    @Override
    public ServiceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.servicelistadapterdesign, parent, false);
        return new ServiceListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceListViewHolder holder, int position) {
        Service service = serviceList.get(position);
        holder.servCostTextView.setText(service.getServ_name());
        holder.servCostTextView.setText(service.getServ_cost());
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }
}

class ServiceListViewHolder extends RecyclerView.ViewHolder {
    public TextView servNameTextView;
    public TextView servCostTextView;

    public ServiceListViewHolder(View itemView) {
        super(itemView);
        servNameTextView = itemView.findViewById(R.id.adapter_servicenameTV);
        servCostTextView = itemView.findViewById(R.id.adapter_servicecostTV);
    }
}
