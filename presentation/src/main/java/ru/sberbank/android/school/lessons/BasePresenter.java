package ru.sberbank.android.school.lessons;

public interface BasePresenter<T extends BaseView> {
    void attach(T view);
    void detach();
}
