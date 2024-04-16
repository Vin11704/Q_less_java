package com.example.q_less_java.Helper;

// to manage the cart, price compilation using the objects instantiated by the CategoryDomain

import android.content.Context;
import android.widget.Toast;

import com.example.q_less_java.Domain.CategoryDomain;
import com.example.q_less_java.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class CartManager {
    private Context context;
    private TinyDB tinyDB;

    public CartManager(Context context){
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(CategoryDomain item){
        ArrayList<CategoryDomain> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++){
            if(listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }

        if(existAlready){   // if already exist, just need to update the number of the item NOT add the entire cardview
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        } else{
            listFood.add(item);
        }
        tinyDB.putListObject("CardList", listFood);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<CategoryDomain> getListCart(){
        return tinyDB.getListObject("CardList");
    }

    public void plusNumberFood(ArrayList<CategoryDomain> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CardList", listFood);
        changeNumberItemsListener.changed();
    }

    public void minusNumberFood(ArrayList<CategoryDomain> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if(listfood.get(position).getNumberInCart() == 1){
            listfood.remove(position);
        } else{
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee(){
        ArrayList<CategoryDomain> listfood = getListCart();
        double fee = 0.0;
        for ( int i = 0; i < listfood.size(); i++){
            fee = fee + (listfood.get(i).getPrice() * listfood.get(i).getNumberInCart());
        }

        return fee;
    }
}
