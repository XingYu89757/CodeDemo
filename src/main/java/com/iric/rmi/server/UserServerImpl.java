package com.iric.rmi.server;

import com.iric.rmi.api.UserInter;
import com.iric.rmi.model.User;
import lombok.extern.slf4j.Slf4j;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class UserServerImpl extends UnicastRemoteObject implements UserInter {
    private static final long serialVersionUID = 1L;

    public UserServerImpl() throws RemoteException {
    }

    @Override
    public User getUserInfo(String name) throws RemoteException{
        System.out.println("客户端参数name:"+ name);
        User user = new User();
        user.setAge(12);
        user.setName(name);
        return user;
    }
}
