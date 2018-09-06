package com.singularity.datapersistence.common;

import com.singularity.datapersistence.db.BaseEntity;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * 公用类
 */
public class Common {

    public static final String INSERT_PLACEHOLDER ="{data}";


    /**
     * javabean 转map
     * @param obj 类
     * @return
     */
    public static Map<String, Object> beanToMap(Object obj) {

        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!"class".equals(key)) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            System.out.println("BeanToMap Error " +obj.toString()+ e+"\n");
        }

        return map;

    }

    /**
     * 递归获取指定文件或者文件夹下的指定文件
     *
     * @param path
     * @param filePaths
     * @param fileType
     */

    public static void searchFiles(String path, List<String> filePaths, String fileType) {
        File file = new File(path);
        if (file.isFile()) {
            filePaths.add(path);
            return;
        }
        File[] files = file.listFiles();
        if (files != null) {
            for (File itemFile : files) {
                if (itemFile.isFile() && itemFile.getName().endsWith(fileType)) {
                    filePaths.add(itemFile.getAbsolutePath());
                } else if (itemFile.isDirectory()) {
                    searchFiles(itemFile.getAbsolutePath(), filePaths, fileType);
                }
            }
        }
    }




    /**
     * 从包package中获取所有的Class
     *
     * @param pack
     * @return
     */
    public static Set<Class> getClasses(String pack) throws IOException {

        // 第一个class类的集合
        Set<Class> classes = new LinkedHashSet<Class>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath,
                            recursive, classes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }


    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    public static void findAndAddClassesInPackageByFile(String packageName,String packagePath,
                                                        final boolean recursive, Set<Class> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            @Override
            public boolean accept(File file) {
                return (recursive && file.isDirectory())
                        || (file.getName().endsWith(".class"));
            }
        });
        if(dirfiles==null){
            return;
        }
        // 循环所有文件
        for (File file : dirfiles) {

            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "."
                                + file.getName(), file.getAbsolutePath(), recursive,
                        classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0,
                        file.getName().length() - 6);
                try {
                    // 添加到集合中去
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 判断是否继承于 BaseEntity
     * @param object
     * @return
     */
    public static boolean isBaseEntityChildren(Object object){
        Class baseEntity= BaseEntity.class;
        if(object==null){
            String error="Magic orm框架不支持不继承"+baseEntity.getName()+"的实体\n";
            System.out.printf(error);
            return false;
        }
        if(baseEntity.equals(object.getClass().getSuperclass())){
            return true;
        }
        Class superClass=object.getClass().getSuperclass();
        while (superClass!=null && superClass!=Object.class){
            return isBaseEntityChildren(superClass);
        }
        return false;
    }
}
