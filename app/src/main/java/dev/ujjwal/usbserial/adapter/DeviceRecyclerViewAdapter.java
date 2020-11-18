package dev.ujjwal.usbserial.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dev.ujjwal.usbserial.DevicesFragment;
import dev.ujjwal.usbserial.R;
import dev.ujjwal.usbserial.model.Device;


@SuppressLint("SetTextI18n")
public class DeviceRecyclerViewAdapter extends RecyclerView.Adapter<DeviceRecyclerViewAdapter.ViewHolder> {

    private final DevicesFragment devicesFragment;
    private final List<Device> devices = new ArrayList<>();

    public DeviceRecyclerViewAdapter(DevicesFragment devicesFragment) {
        this.devicesFragment = devicesFragment;
    }

    public void updateData(List<Device> devices) {
        this.devices.clear();
        this.devices.addAll(devices);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_device, parent, false);
        return (new ViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(devicesFragment, devices.get(position));
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvText1, tvText2;
        private final LinearLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);

            tvText1 = itemView.findViewById(R.id.device_tv_text1);
            tvText2 = itemView.findViewById(R.id.device_tv_text2);
            layout = itemView.findViewById(R.id.device_layout);
        }

        public void bind(DevicesFragment devicesFragment, Device device) {
            if (device.getDriver() == null)
                tvText1.setText("<no driver>");
            else if (device.getDriver().getPorts().size() == 1)
                tvText1.setText(device.getDriver().getClass().getSimpleName().replace("SerialDriver", ""));
            else
                tvText1.setText(device.getDriver().getClass().getSimpleName().replace("SerialDriver", "") + ", Port " + device.getPort());
            tvText2.setText(String.format(Locale.US, "Vendor %04X, Product %04X", device.getDevice().getVendorId(), device.getDevice().getProductId()));

            layout.setOnClickListener(v -> devicesFragment.openDevice(device));
        }
    }
}
