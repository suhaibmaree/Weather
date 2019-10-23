package com.example.weather.base;




public interface Presenter<V extends MvpView>  {

    void detachView();
    void attachView(V mvpView);
}
