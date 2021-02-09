package com.example.junk.ui.cart;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.junk.R;
import com.example.junk.controllers.CartController;

/**
 * A fragment representing a list of Items.
 */
public class CartFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    private CartController cartController;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CartFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CartFragment newInstance(int columnCount) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cartController = CartController.getInstance();

        View view = inflater.inflate(R.layout.fragment_cart_list, container, false);
        View recView = view.findViewById(R.id.list);

        // Set the adapter
        if (recView instanceof RecyclerView) {
            Context context = recView.getContext();
            RecyclerView recyclerView = (RecyclerView) recView;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(new CartRecyclerViewAdapter(getContext(),cartController.getCart()));
        }

        TextView subTotalView = (TextView) view.findViewById(R.id.summary_subtotal);
        TextView shippingView = (TextView) view.findViewById(R.id.summary_shippingFee);
        TextView totalView = (TextView) view.findViewById(R.id.summary_total);

        subTotalView.setText(String.format("$%.02f",cartController.getSubTotal()));
        shippingView.setText(String.format("$%.02f",cartController.getShipping()));

        totalView.setText(String.format("$%.02f",cartController.getTotal()));

        return view;
    }


}