package com.example.nikolay.gunual.weapon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nikolay.gunual.FavoriteDAO;
import com.example.nikolay.gunual.R;
import com.example.nikolay.gunual.information.InformationActivity;
import com.example.nikolay.gunual.models.Weapon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WeaponAdapter extends RecyclerView.Adapter<WeaponAdapter.ViewHolder> {

    private Context mContext;
    private List<Weapon> mWeapons;
    private String mExtra;
    private FavoriteDAO mFavoriteDAO;

    public WeaponAdapter(Context context, ArrayList<Weapon> weapons, String extra, FavoriteDAO favoriteDAO) {
        mContext = context;
        mWeapons = weapons;
        mExtra = extra;
        mFavoriteDAO = favoriteDAO;
    }

    public WeaponAdapter(Context context, List<Weapon> weapons, FavoriteDAO favoriteDAO) {
        mContext = context;
        mWeapons = weapons;
        mFavoriteDAO = favoriteDAO;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weapon_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.mTitle.setText(mWeapons.get(i).getTitle());

        Picasso.get()
                .load("http:" + mWeapons.get(i).getImageUrl())
                .into(viewHolder.mImage);

        if (mFavoriteDAO.isFavorite(mWeapons.get(i).getTitle())) {
            viewHolder.mStar.setImageResource(R.drawable.favorite_star);
        } else {
            viewHolder.mStar.setImageResource(R.drawable.unfavorite_star);
        }

        viewHolder.mStar.setOnClickListener(view -> {
            if (mFavoriteDAO.isFavorite(mWeapons.get(i).getTitle())) {
                mFavoriteDAO.removeFromFavorite(mWeapons.get(i).getTitle());
                viewHolder.mStar.setImageResource(R.drawable.unfavorite_star);
            } else {
                mFavoriteDAO.addToFavorite(mWeapons.get(i));
                viewHolder.mStar.setImageResource(R.drawable.favorite_star);
            }
        });

        viewHolder.mParentLayout.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, InformationActivity.class);
            intent.putExtra("weapon", mExtra);
            intent.putExtra("title", mWeapons.get(i).getTitle());
            intent.putExtra("country", mWeapons.get(i).getCountry());
            intent.putExtra("year_of_production", mWeapons.get(i).getYearOfProduction());
            intent.putExtra("type_of_bullet", mWeapons.get(i).getTypeOfBullet());
            intent.putExtra("effective_range", mWeapons.get(i).getEffectiveRange());
            intent.putExtra("muzzle_velocity", mWeapons.get(i).getMuzzleVelocity());
            intent.putExtra("feed_system", mWeapons.get(i).getFeedSystem());
            intent.putExtra("length", mWeapons.get(i).getLength());
            intent.putExtra("barrel_length", mWeapons.get(i).getBarrelLength());
            intent.putExtra("weight", mWeapons.get(i).getWeight());
            intent.putExtra("description", mWeapons.get(i).getDescription());
            intent.putExtra("image_url", mWeapons.get(i).getImageUrl());

            ((Activity) mContext).startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return mWeapons.size();
    }

    public void updateList(List<Weapon> weapons) {
        mWeapons = new ArrayList<>();
        mWeapons.addAll(weapons);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private ImageView mImage;
        private ImageView mStar;
        private LinearLayout mParentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title);
            mImage = itemView.findViewById(R.id.image);
            mParentLayout = itemView.findViewById(R.id.parent_layout);
            mStar = itemView.findViewById(R.id.star);

        }
    }

}