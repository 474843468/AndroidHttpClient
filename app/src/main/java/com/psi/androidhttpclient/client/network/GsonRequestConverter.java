package com.psi.androidhttpclient.client.network;

import com.google.gson.TypeAdapter;
import java.io.IOException;
import okhttp3.RequestBody;

/**
 * Created by XieDu on 2016/4/13.
 */
public class GsonRequestConverter<D> implements Converter<D, RequestBody> {
    private TypeAdapter<D> adapter;

    public GsonRequestConverter(TypeAdapter<D> adapter) {
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(D data) {
        String json = null;
        try {
            json = adapter.toJson(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RequestBody.create(ContentType.JSON, json);
    }
}
