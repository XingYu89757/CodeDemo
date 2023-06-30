package com.iric.rmi.client;

import com.iric.rmi.api.UserInter;
import com.iric.rmi.model.User;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

@Slf4j
public class Client {
    public static void main(String[] args) {
        try {
            UserInter userInter = (UserInter) Naming.lookup("rmi://localhost:8888/user");
            User tom = userInter.getUserInfo("tom");
            System.out.println(tom);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }
}
