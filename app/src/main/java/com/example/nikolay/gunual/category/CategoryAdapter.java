package com.example.nikolay.gunual.category;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nikolay.gunual.R;
import com.example.nikolay.gunual.models.WeaponCategory;
import com.example.nikolay.gunual.weapon.WeaponActivity;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<WeaponCategory> mWeaponCategories;

    public CategoryAdapter(Context context, ArrayList<WeaponCategory> weaponCategories) {
        mContext = context;
        mWeaponCategories = weaponCategories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.mTitle.setText(mWeaponCategories.get(i).getTitle());
        viewHolder.mImage.setImageResource(mWeaponCategories.get(i).getImage());

        viewHolder.mParentLayout.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, WeaponActivity.class);
            intent.putExtra("Weapon", mWeaponCategories.get(i).getTitle());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mWeaponCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private ImageView mImage;
        private LinearLayout mParentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.text_view_category_item);
            mImage = itemView.findViewById(R.id.image_view_category_item);
            mParentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
