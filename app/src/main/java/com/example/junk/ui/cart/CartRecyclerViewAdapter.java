package com.example.junk.ui.cart;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.junk.R;
import com.example.junk.datamodel.Cart;
import com.example.junk.datamodel.Product;

import java.io.IOException;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Product}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder> {

    private final Cart cart;
    private Context context;

    public CartRecyclerViewAdapter(Context context, Cart cart) {
        this.cart = cart;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Product product = cart.getProducts().get(position);
        holder.mItem = product;
        holder.mNameView.setText(product.getName());
        holder.mStyleView.setText(product.getStyle());
        holder.mUnitPriceView.setText(String.format("$%.02f", product.getUnitprice()));
        holder.mQuantityView.setText("QTY: "+product.getQuantity());
        holder.mColorView.setTextColor(Color.parseColor(product.getColor().getSwatch()));

        try {
            Drawable d = Drawable.createFromStream(context.getAssets().open(product.getImage()+".png"), null);
            holder.mImageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return cart.getProducts().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mNameView;
        public final TextView mStyleView;
        public final TextView mUnitPriceView;
        public final TextView mColorView;
        public final TextView mQuantityView;
        public Product mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.product_image);
            mNameView = (TextView) view.findViewById(R.id.product_name);
            mStyleView = (TextView) view.findViewById(R.id.product_style);
            mUnitPriceView = (TextView) view.findViewById(R.id.product_unitprice);
            mColorView = (TextView) view.findViewById(R.id.product_swatch);
            mQuantityView = (TextView) view.findViewById(R.id.product_quantity);
        }


        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}