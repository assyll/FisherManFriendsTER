package com.fishermanfriendz.dtc.fishermanfriendster;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.bluetooth.BluetoothClient;

import com.bluetooth.BluetoothServer;

import java.util.Set;


public class MainActivity extends ActionBarActivity {

    private BluetoothAdapter mBluetoothAdapter;
    private String myBluetoothName;
    private BluetoothDevice bluetoothDeviceServerName;
    private BluetoothDevice bluetoothDeviceClientName;
    private Set<BluetoothDevice> pairedDevices;
    private Boolean server;
    private String serverName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);
    }


    public void setMeAsServer(View v){
        this.server = true;
        initialisationConnexxion();
        server();
    }

    public void setMeAsClient(View v){
        this.server = false;
        EditText e = (EditText) findViewById(R.id.editText);
        serverName = e.getText().toString();
        if (!serverName.trim().equals("")) {
            initialisationConnexxion();
            client();
        }
    }

    public void initialisationConnexxion(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null) {
            // Le terminal ne possÃ¨de pas le Bluetooth
            Toast.makeText(getApplicationContext(), "pas de BT", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (mBluetoothAdapter.isEnabled()) {
                myBluetoothName = mBluetoothAdapter.getName();
                //Toast.makeText(getApplicationContext(), myBluetoothName, Toast.LENGTH_SHORT).show();

                pairedDevices = mBluetoothAdapter.getBondedDevices();
                String s = "";
                // If there are paired devices
                if (pairedDevices.size() > 0) {
                    // Loop through paired devices
                    for (BluetoothDevice device : pairedDevices) {
                        //Affichage des devices pairÃ©s
                        s+="NAME:"+device.getName() + "\n" + "adress:"+device.getAddress()+"\n";
                        //Je cherche celui dont j'ai besoin (en fait les 2 tel avec lesquels j'ai testÃ©
                        if (server) {
                            Toast.makeText(getApplicationContext(), "Je suis appairé avec : " + device.getName(), Toast.LENGTH_SHORT).show();
                            this.bluetoothDeviceClientName=device;
                        }
                        if (!server && device.getName().equals(serverName)) {
                            this.bluetoothDeviceServerName=device;
                            //System.out.println("Claude FOUND");
                        }
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Nothing is paired", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public Boolean isAlreadyRegistered(BluetoothDevice bluetoothDevice){
        return pairedDevices.contains(bluetoothDevice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void client() {

        if (this.bluetoothDeviceServerName == null)
        {
            Toast.makeText(getApplicationContext(), "server est null", Toast.LENGTH_SHORT).show();
            Log.e("ClientBT", "Device is null");
        }
        else
        {
            Log.i("ClientBT", "Starting client");
            BluetoothClient btclient = new BluetoothClient(this.bluetoothDeviceServerName, mBluetoothAdapter); //le client est HTCC
            btclient.start();
            //Toast.makeText(getApplicationContext(), this.serverName.getName(), Toast.LENGTH_SHORT).show();
            //System.out.println(this.bluetoothDeviceServerName.toString());
        }
    }

    public void server() {
        Log.i("ServerBT","Starting server");
        BluetoothServer btserv = new BluetoothServer(mBluetoothAdapter);
        btserv.start();
        Intent i = new Intent(MainActivity.this, LakeActivity.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(), "serveur lancé", Toast.LENGTH_SHORT).show();
    }
}
