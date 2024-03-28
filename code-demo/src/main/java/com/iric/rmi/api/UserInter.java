package com.iric.rmi.api;

import com.iric.rmi.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserInter extends Remote {

    User getUserInfo(String name) throws RemoteException;
}
