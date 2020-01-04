package com.taufiq.ayger.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLapangan {

    @SerializedName("semualapangan")
    private List<SemuaLapangan> semualapangan;
    @SerializedName("success")
    private boolean error;
    @SerializedName("message")
    private String message;

    public List<SemuaLapangan> getSemualapangan() {
        return semualapangan;
    }

    public void setSemualapangan(List<SemuaLapangan> semuadosen) {
        this.semualapangan = semuadosen;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return
                "ResponseDosen{" +
                        "semuadosen = '" + semualapangan + '\'' +
                        ",success = '" + error + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}
