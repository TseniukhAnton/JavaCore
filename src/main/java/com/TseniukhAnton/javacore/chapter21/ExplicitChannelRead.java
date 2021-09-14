package com.TseniukhAnton.javacore.chapter21;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExplicitChannelRead {
    public static void main(String[] args) {
        int count;
        Path filepath = null;

        try (SeekableByteChannel fChan = Files.newByteChannel(Paths.get("test.txt"))) {
            ByteBuffer mBuf = ByteBuffer.allocate(128);
            do {
                count = fChan.read(mBuf);
                if (count != -1){
                    mBuf.rewind();
                    for (int i = 0; i < count; i++) {
                        System.out.print((char) mBuf.get());
                    }
                }
            }while (count != -1);
            System.out.println();
        }catch (InvalidPathException e){
            System.out.println("Wrong path error " + e);
        }catch (IOException e){
            System.out.println("IO Error " + e);
        }
    }
}
