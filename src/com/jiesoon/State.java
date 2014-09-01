package com.jiesoon;

public interface State {
    int NOT_AUTHENTICATED = 0;
    int AUTHENTICATED = 1;
    int SELECTED = 2;
    int LOGOUT = 3;
}
