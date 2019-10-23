package com.example.weather.base;




public interface Presenter<V extends MvpView>  {

    public void detachView();
    public void attachView( V mvpView);
}
