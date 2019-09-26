package com.example.pwpb_ujianakhirsemester;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    String tanggal;
    String dataid;
    String datatitle;
    String dataisi;

    public Data() { }

    public Data(String tanggal, String dataid, String datatitle, String dataisi) {
        this.tanggal = tanggal;
        this.dataid = dataid;
        this.datatitle = datatitle;
        this.dataisi = dataisi;
    }

    protected Data(Parcel in) {
        tanggal = in.readString();
        dataid = in.readString();
        datatitle = in.readString();
        dataisi = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getTanggal() { return tanggal; }

    public String getDataid() { return dataid; }

    public String getDatatitle() { return datatitle; }

    public String getDataisi() { return dataisi; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tanggal);
        parcel.writeString(dataid);
        parcel.writeString(datatitle);
        parcel.writeString(dataisi);
    }
}
