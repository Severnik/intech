package com.intechtest.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.intechtest.model.AbstractModel;
import com.intechtest.ui.event.OnItemClick;
import com.intechtest.ui.holder.AbstractHolder;

import java.util.List;


/**
 * Created by Server Kurtnebiev on 10.07.2016.
 * Copyright (c) 2016 intech
 */
public class AbstractRecyclerViewAdapter<D extends AbstractModel, H extends AbstractHolder> extends RecyclerView.Adapter<H> {

    protected List<D> data;
    protected LayoutInflater inflater;
    protected OnItemClick<D> onItemClick;

    public void setOnItemClick(OnItemClick<D> onItemClick) {
        this.onItemClick = onItemClick;
    }

    public AbstractRecyclerViewAdapter() {
    }

    public AbstractRecyclerViewAdapter(List<D> data) {
        this.data = data;
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        return onInitViewHolder(parent, viewType);
    }

    public H onInitViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(H holder, int position) {
        holder.setData(data.get(position), position);
        holder.setOnItemClick(onItemClick, position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(List<D> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void clearData() {
        if (data != null) data.clear();
        notifyDataSetChanged();
    }

    public List<D> getData() {
        return data;
    }
}
