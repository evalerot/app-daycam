package com.example.PapeleriaPaRFiles.Controller;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.PapeleriaPaRFiles.R;
import java.util.ArrayList;

public class AdaptadorProductosFacturar extends BaseAdapter {

    private Context context;
    private ArrayList<ProductosFacturar> list;

    public long getItemId(int i) {
        return 0;
    }

    public AdaptadorProductosFacturar(Context context2, ArrayList<ProductosFacturar> arrayList) {
        this.list = arrayList;
        this.context = context2;
    }

    public ArrayList<ProductosFacturar> getList() {
        return this.list;
    }

    public int getCount() {
        return this.list.size();
    }

    public ProductosFacturar getItem(int i) {
        return this.list.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ProductosFacturar item = getItem(i);
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.dialog_productos_facturar, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.nombreProducto)).setText(item.getProducto());
        ((TextView) inflate.findViewById(R.id.codigoProducto)).setText("Cod:" + item.getCodigo());
        ((TextView) inflate.findViewById(R.id.cantidad)).setText(item.getCantidad());
        ((TextView) inflate.findViewById(R.id.precio)).setText("X " + item.getPrecio());
        ((TextView) inflate.findViewById(R.id.subtotal)).setText("$" + item.getSubtotal());
        ((TextView) inflate.findViewById(R.id.can)).setText(item.getCan());
        if(Integer.parseInt(item.getCan()) % 2 == 0){
            inflate.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }else{
            inflate.setBackgroundColor(Color.WHITE);
        }
        return inflate;
    }
}
