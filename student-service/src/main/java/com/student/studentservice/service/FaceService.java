package com.student.studentservice.service;


public interface FaceService {
    /**
     *  通过两个文照片的位置进行比较
     * @param imagePath1
     * @param imagePath2
     * @return
     */
    boolean isSameByPath(String imagePath1, String imagePath2);

    /**
     *  通过两个照片的 base64编码进行比较
     * @param encode1
     * @param encode2
     * @return
     */
    boolean isSameByBase64(String encode1, String encode2);

    /**
     *  一个是base64 编码的照片和 一个本地文件比较!
     * @param encode
     * @param path
     * @return
     */
    boolean isSameByBase64AndPath(String encode, String path);
}
