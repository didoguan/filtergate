package com.deepspc.filtergate.utils;

import com.deepspc.filtergate.core.exception.CoreExceptionEnum;
import com.deepspc.filtergate.core.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO方式读取文件
 */
public class FileUtil {
    private static Logger log = LoggerFactory.getLogger(FileUtil.class);

    public static byte[] toByteArray(String filename) throws IOException {
        File f = new File(filename);
        if (!f.exists()) {
            log.error("文件未找到！" + filename);
            throw new ServiceException(CoreExceptionEnum.FILE_NOT_FOUND.getCode(),
                    CoreExceptionEnum.FILE_NOT_FOUND.getMessage());
        }
        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                throw e;
            }
            try {
                fs.close();
            } catch (IOException e) {
                throw e;
            }
        }
    }
}
