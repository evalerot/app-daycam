package com.example.PapeleriaPaRFiles.Controller;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.PapeleriaPaRFiles.R;

import java.util.ArrayList;

public class AdaptadorProductosInventarios extends BaseAdapter {

    private final Context context;
    private final ArrayList<ProductosInventarios> list;

    public long getItemId(int i) {
        return 0;
    }

    public AdaptadorProductosInventarios(Context context2, ArrayList<ProductosInventarios> arrayList) {
        this.list = arrayList;
        this.context = context2;
    }

    public ArrayList<ProductosInventarios> getList() {
        return this.list;
    }

    public int getCount() {
        return this.list.size();
    }

    public ProductosInventarios getItem(int i) {
        return this.list.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ProductosInventarios item = getItem(i);
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.dialog_productos_inventario, null);
        ((TextView) inflate.findViewById(R.id.nombreProducto)).setText(item.getProducto());
        ((TextView) inflate.findViewById(R.id.codigoProducto)).setText("Cod: " + item.getCodigo());
        ((TextView) inflate.findViewById(R.id.cantidad)).setText("Can: " + item.getCantidad());
        ((TextView) inflate.findViewById(R.id.costocompra)).setText("CC: " + item.getCostoCompra());
        ((TextView) inflate.findViewById(R.id.costoventa)).setText("CV: " + item.getCostoVenta());
        ((TextView) inflate.findViewById(R.id.can)).setText(item.getCan());
        if(Integer.parseInt(item.getCan()) % 2 == 0){
            inflate.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }else{
            inflate.setBackgroundColor(Color.WHITE);
        }
        return inflate;
    }
}
