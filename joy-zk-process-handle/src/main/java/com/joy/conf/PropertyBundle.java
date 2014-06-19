package com.joy.conf;

import com.joy.exception.ResourceLoadException;
import com.joy.exception.ServerException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-12
 * Time: 下午3:12
 * To change this template use File | Settings | File Templates.
 * 负责资源文件读取
 */
public class PropertyBundle {

    /**
     * 属性集合
     */
    private Properties props;

    /**
     * @param fileName 资源文件名
     */
    public PropertyBundle(String fileName) {
        this.props = loadConfig(fileName);
    }

    /**
     * 读取指定key的值
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return props.getProperty(key);
    }

    /**
     * 加载资源文件
     * @param fileName 资源文件名
     * @return
     */
    public Properties loadConfig(String fileName) {
        Properties props = new Properties();
        InputStreamReader reader = null;

        try {
            InputStream ins = PropertyBundle.class.getClassLoader().getResourceAsStream(fileName);
            if (ins == null) {
                throw new ResourceLoadException("Resource file [" + fileName + "] can not find in classpath.");
            }
            reader = new InputStreamReader(ins, "UTF-8");
            props.load(reader);
        } catch (UnsupportedEncodingException e) {
            throw new ServerException("Encoding UTF-8 is not supported.", e);
        } catch (IOException e) {
            throw new ServerException("Resource file [" + fileName + "] cat not be loaded.", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
        return props;
    }
}
