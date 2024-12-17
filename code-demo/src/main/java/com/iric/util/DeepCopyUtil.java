package com.iric.util;

import java.io.*;
import java.util.List;

public class DeepCopyUtil {
    public static <T> List<T> deepCopy(List<T> src) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
            objectOut.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream objectIn = new ObjectInputStream(byteIn);
            List<T> dest = (List<T>) objectIn.readObject();

            return dest;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}