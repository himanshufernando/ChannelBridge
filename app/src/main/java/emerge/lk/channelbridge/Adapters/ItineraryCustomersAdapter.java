package emerge.lk.channelbridge.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.annotations.Nullable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import emerge.lk.channelbridge.Entity.ItineraryCustomersEntity;
import emerge.lk.channelbridge.R;


/**
 * Created by Himanshu on 01/05/2017.
 */
public class ItineraryCustomersAdapter extends RecyclerView.Adapter<ItineraryCustomersAdapter.MyViewHolder> implements View.OnClickListener {

    Context mContext;
    ArrayList<ItineraryCustomersEntity> itineraryCustomersEntities;

    public ItineraryCustomersAdapter(Context mContext, ArrayList<ItineraryCustomersEntity> itineraryCustomersEntities) {
        this.mContext = mContext;
        this.itineraryCustomersEntities = itineraryCustomersEntities;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_itinerary_customer, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String cutomerImagePath = "file:///android_asset/image/" + itineraryCustomersEntities.get(position).getItineraryCustomersImageName();

        holder.textViewCustomerName.setText(itineraryCustomersEntities.get(position).getItineraryCustomersName());
        holder.textViewCustomerCity.setText(itineraryCustomersEntities.get(position).getItineraryCustomersCity());
        Picasso.with(mContext).load(cutomerImagePath).into(holder.imageViewCustomerImage);

        holder.linLayout_itinerary_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Dialog dialog = new Dialog(mContext);
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialogbox_customer_details);
                TextView customerName = (TextView)dialog.findViewById(R.id.dialogbox_customer_name);
                ImageView customerImage = (ImageView)dialog.findViewById(R.id.imgView_dialogbox_customer);
                ImageView ivCloseDialog = (ImageView)dialog.findViewById(R.id.imgView_dialogbox_close);

                ivCloseDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                customerName.setText(itineraryCustomersEntities.get(position).getItineraryCustomersName());
                Picasso.with(mContext).load("file:///android_asset/image/" + itineraryCustomersEntities.get(position).getItineraryCustomersImageName()).into(customerImage);


                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return itineraryCustomersEntities.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgView_itinerary_customer)
        ImageView imageViewCustomerImage;
        @BindView(R.id.textView_itinerary_customername)
        TextView textViewCustomerName;
        @BindView(R.id.textView_itinerary_customercity)
        TextView textViewCustomerCity;
        @BindView(R.id.linLayout_itinerary_item)
        LinearLayout linLayout_itinerary_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
