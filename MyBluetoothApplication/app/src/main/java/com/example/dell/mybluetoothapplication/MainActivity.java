package com.example.dell.mybluetoothapplication;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
private static final String TAG = "MainActivity";
    BluetoothAdapter mBluetoothAdapter;
    public ArrayList<BluetoothDevice> mBTDevices = new ArrayList<>();
    public DeviceListAdapter mDeviceListAdapter;
    public ListView lvNewDevices;

    private final BroadcastReceiver mBroadCastReciever1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(mBluetoothAdapter.ACTION_STATE_CHANGED)){
               final int state = intent.getIntExtra(mBluetoothAdapter.EXTRA_STATE,mBluetoothAdapter.ERROR);

               switch (state){
                   case BluetoothAdapter.STATE_OFF:
                       break;
                   case BluetoothAdapter.STATE_TURNING_OFF:
                       break;
                   case BluetoothAdapter.STATE_ON:
                       break;
                   case BluetoothAdapter.STATE_TURNING_ON:
                       break;
               }
            }
        }
    };

    private final BroadcastReceiver mBroadCastReciever2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if(action.equals(mBluetoothAdapter.ACTION_SCAN_MODE_CHANGED)){
                int mode = intent.getIntExtra(mBluetoothAdapter.EXTRA_SCAN_MODE,mBluetoothAdapter.ERROR);

                switch (mode){
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                        break;
                    case BluetoothAdapter.SCAN_MODE_NONE:
                        break;
                    case BluetoothAdapter.STATE_CONNECTING:
                        break;
                    case BluetoothAdapter.STATE_CONNECTED:
                        break;
                }
            }
        }
    };

    private BroadcastReceiver mBroadCastReciever3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mBTDevices.add(device);
                mDeviceListAdapter = new DeviceListAdapter(context, R.layout.device_adapter_view, mBTDevices);
                lvNewDevices.setAdapter(mDeviceListAdapter);
            }
        }
    };

    private BroadcastReceiver mBroadCastReciever4 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
                BluetoothDevice mDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (mDevice.getBondState() == BluetoothDevice.BOND_BONDED) {

                }
                if(mDevice.getBondState() == BluetoothDevice.BOND_BONDING){

                }if(mDevice.getBondState() == BluetoothDevice.BOND_NONE){

                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadCastReciever1);
        unregisterReceiver(mBroadCastReciever2);
        unregisterReceiver(mBroadCastReciever3);
        unregisterReceiver(mBroadCastReciever4);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnONOFF = (Button) findViewById(R.id.btnONOFF);
        lvNewDevices = (ListView)findViewById(R.id.lvNewDevices);
        mBTDevices = new ArrayList<>();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(mBroadCastReciever4,filter);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        lvNewDevices.setOnItemClickListener(MainActivity.this);

        btnONOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableDisableBT();
            }
        });
    }
    public void OnDiscover(View view){
        if(mBluetoothAdapter.isDiscovering()){
            mBluetoothAdapter.cancelDiscovery();

            checkBTPermission();

            mBluetoothAdapter.startDiscovery();
            IntentFilter discoverDevicesFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadCastReciever3,discoverDevicesFilter);
        }if(!mBluetoothAdapter.isDiscovering()){
            checkBTPermission();
            mBluetoothAdapter.startDiscovery();

            IntentFilter discoverDevicesFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadCastReciever3,discoverDevicesFilter);
        }
    }
    public void enableDisableBT(){
         if(mBluetoothAdapter == null){
         }
         if(!mBluetoothAdapter.isEnabled()){
             Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
             startActivity(intent);

             IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
             registerReceiver(mBroadCastReciever1,BTIntent);
         }
         if(mBluetoothAdapter.isEnabled()){
             mBluetoothAdapter.disable();

             IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
             registerReceiver(mBroadCastReciever1,BTIntent);
         }
    }
    public void OnClick(View view){
    Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
    discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);

    IntentFilter intentFilter =  new IntentFilter(mBluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
    registerReceiver(mBroadCastReciever2,intentFilter);

    }

    public void checkBTPermission(){
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            permissionCheck += this.checkSelfPermission("Manifest.permission.ACCESS_COURSE_LOCATION");

            if(permissionCheck !=0){
                this.requestPermissions(new String[]{ Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1001);
            }else{

            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        mBluetoothAdapter.cancelDiscovery();

        String deviceName = mBTDevices.get(i).getName();
        String deviceAddress = mBTDevices.get(i).getAddress();

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2){
            mBTDevices.get(i).getAddress();
        }
    }
}
