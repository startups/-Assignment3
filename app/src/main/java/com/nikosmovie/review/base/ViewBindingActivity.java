package com.nikosmovie.review.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

import com.google.android.material.snackbar.Snackbar;
import com.nikosmovie.review.R;
import com.nikosmovie.review.widget.CustomDialog;
import com.novoda.merlin.Bindable;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;


public abstract class ViewBindingActivity<VB extends ViewBinding, VM extends ViewModel> extends AppCompatActivity {
    protected VB binding;

    public abstract VB inflateLayout(LayoutInflater layoutInflater);
    public VM viewModel;

    public abstract void viewListeners();

    public CustomDialog customDialog;
    public Merlin merlin;
    public Boolean isRequestComplete = false;
    public Snackbar mySnackbar;
    private Boolean isFirst = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = inflateLayout(getLayoutInflater());
        setContentView(binding.getRoot());
        customDialog = new CustomDialog(this);
        try {
            Class<VM> vmClass = ((Class<VM>) ((ParameterizedType) Objects.requireNonNull(getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[1]);
            viewModel = vmClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        mySnackbar  = Snackbar.make(binding.getRoot(),
                R.string.noInternet, Snackbar.LENGTH_INDEFINITE);
        mySnackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
                mySnackbar.dismiss();
            }
        });

        merlin = new Merlin.Builder().withAllCallbacks().build(this);
        connectionChecker();

        viewListeners();

    }


    public void checkComplete(int count,int size){
        if(count >= size){
            customDialog.hide();
            isRequestComplete = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        merlin.bind();
    }

    @Override
    protected void onPause() {
        super.onPause();
        merlin.unbind();
    }


    private void connectionChecker(){
        merlin.registerConnectable(new Connectable() {
            @Override
            public void onConnect() {

                if(!isFirst){
                    if(!isRequestComplete){
                        customDialog.dismiss();
                        //mySnackbar.dismiss();
                       // recreate();
                    }
                }
            }
        });

        merlin.registerBindable(new Bindable() {
            @Override
            public void onBind(NetworkStatus networkStatus) {
                if(networkStatus.isAvailable()){
                    isFirst = true;
                }else{
                    isFirst = false;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            customDialog.dismiss();
                            mySnackbar.show();
                        }
                    });
                }
            }
        });

        merlin.registerDisconnectable(new Disconnectable() {
            @Override
            public void onDisconnect() {
                isFirst = false;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customDialog.dismiss();

                        mySnackbar.show();
                    }
                });
            }
        });
    }

}

