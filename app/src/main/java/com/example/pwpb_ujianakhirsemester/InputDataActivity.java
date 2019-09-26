package com.example.pwpb_ujianakhirsemester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputDataActivity extends AppCompatActivity {
    Button buttonAdd;
    EditText editJudul;
    EditText editIsi;
    Context context;
    String nomor = "Submit", UPDATE_INTENT = "UPDATE_INTENT", UPDATE_ACTION = "UPDATE_ACTION", dataSubmit;
    Data dataUpdate;
    DatabaseReference databaseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        initComponent();

        if (dataSubmit == null) {
            dataSubmit = "Submit";
        } else {
            nomor = String.valueOf(dataUpdate.getDataid());
        }

        if (dataSubmit.equals("Edit")) {
            buttonAdd.setText("Edit");
            editJudul.setText(dataUpdate.getDatatitle());
            editIsi.setText(dataUpdate.getDataisi());
        }

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String label = buttonAdd.getText().toString();
                if (label.equals("Tambah")) {
                    addData();
                }
                if (label.equals("Edit")) {
                    String id = dataUpdate.getDataid();
                    String judul = editJudul.getText().toString().trim();
                    String isi = editIsi.getText().toString().trim();
                    SimpleDateFormat tanggal = new SimpleDateFormat("dd/MM/yyyy' 'hh:mm:ss");
                    String tglsekarang = tanggal.format(new Date());
                    if (TextUtils.isEmpty(judul)) {
                        editJudul.setError("Judul kosong");
                        return;
                    }
                    Updatedata(tglsekarang, id, judul, isi);
                }
            }
        });
    }

    private void initComponent() {
        context = this;
        buttonAdd = (Button) findViewById(R.id.add_data);
        editJudul = (EditText) findViewById(R.id.editjudul);
        editIsi = (EditText) findViewById(R.id.editisi);

        dataSubmit = getIntent().getStringExtra(UPDATE_ACTION);
        dataUpdate = getIntent().getParcelableExtra(UPDATE_INTENT);

        databaseData = FirebaseDatabase.getInstance().getReference("Data");
    }

    private void addData() {
        databaseData = FirebaseDatabase.getInstance().getReference("Data");

        String judul = editJudul.getText().toString().trim();
        String isi = editIsi.getText().toString().trim();
        SimpleDateFormat tanggal = new SimpleDateFormat("dd/MM/yyyy' 'hh:mm:ss");
        String tglsekarang = tanggal.format(new Date());

        if (!TextUtils.isEmpty(judul)) {
            String id = databaseData.push().getKey();
            Data data = new Data(tglsekarang, id, judul, isi);
            databaseData.child(id).setValue(data);
            Toast.makeText(this, "Data berhasil dimasukan", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Isi judul terlebih dahullu", Toast.LENGTH_LONG).show();
        }
    }

    private boolean Updatedata(String tanggal, String id, String judul, String isi) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Data").child(id);
        Data data = new Data(tanggal, id, judul, isi);
        databaseReference.setValue(data);
        Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_LONG).show();
        return true;
    }
}
