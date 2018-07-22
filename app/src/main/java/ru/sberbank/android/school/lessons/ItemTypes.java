package ru.sberbank.android.school.lessons;

public enum ItemTypes {
    ONE_BUTTON_TYPE(0),
    TWO_BUTTONS_TYPE(1),
    THREE_BUTTONS_TYPE(2);

    int type;

    ItemTypes(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
