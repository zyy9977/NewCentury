package com.nc.utils.test;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.WritableResource;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author zhangyangyang
 * @date 2019/3/13 17:10
 */
public class TestSpring {
    public static void main(String[] args) throws IOException {
        String filePath = "F:/spring.txt";
        WritableResource writableResource = new PathResource(filePath);

        OutputStream outputStream = writableResource.getOutputStream();
        outputStream.write("今天怎么样？".getBytes());
    }
}
