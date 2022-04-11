package com.student.studentservice.service.Impl;

import com.student.studentservice.domain.FaceModel.ImageInfo;
import com.student.studentservice.domain.FaceModel.Response;
import com.student.studentservice.service.FaceService;
import com.student.studentservice.utils.Base64Util;
import com.student.studentservice.utils.FileUtil;
import com.student.studentservice.utils.GsonUtils;
import com.student.studentservice.utils.facematch.FaceMatchUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("FaceServiceImpl")
public class FaceServiceImpl implements FaceService {
    /**
     *  先把传入的 图片的信息转换为 base64 编码,再将编码封装到image 数组中!
     *  调用 FaceMatchUtil.faceMatch(param) 方法得到返回值 Response 的结果
     * @param imagePath1
     * @param imagePath2
     * @return
     */

    @Override
    public boolean isSameByPath(String imagePath1, String imagePath2) {
        Response response = null;
        try {
            byte[] bytes1 = FileUtil.readFileByBytes(imagePath1);
            byte[] bytes2 = FileUtil.readFileByBytes(imagePath2);

            String encode1 = Base64Util.encode(bytes1);
            String encode2 = Base64Util.encode(bytes2);

            ImageInfo[] imageInfos = new ImageInfo[2];

            for(int i = 0; i < 2; i++){
                imageInfos[i] = new ImageInfo();
            }

            imageInfos[0].setImage(encode1);
            imageInfos[1].setImage(encode2);

            String param = GsonUtils.toJson(imageInfos);

            response = FaceMatchUtil.faceMatch(param);

            if(response == null) return false;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.getResult().getScore() > 70;
    }

    @Override
    public boolean isSameByBase64(String encode1, String encode2) {

        Response response = null;

        ImageInfo[] imageInfos = new ImageInfo[2];

        for(int i = 0; i < 2; i++){
            imageInfos[i] = new ImageInfo();
        }

        imageInfos[0].setImage(encode1);
        imageInfos[1].setImage(encode2);

        String param = GsonUtils.toJson(imageInfos);

        response = FaceMatchUtil.faceMatch(param);

        if(response == null) return false;

        return response.getResult().getScore() > 70;
    }


    @Override
    public boolean isSameByBase64AndPath(String encode, String path) {
        byte[] bytes = null;
        Response response = null;
        try {
            bytes = FileUtil.readFileByBytes(path);
            String encode1 = Base64Util.encode(bytes);

            ImageInfo[] imageInfos = new ImageInfo[2];

            for(int i = 0; i < 2; i++){
                imageInfos[i] = new ImageInfo();
            }

            imageInfos[0].setImage(encode);
            imageInfos[1].setImage(encode1);

            String param = GsonUtils.toJson(imageInfos);

            response = FaceMatchUtil.faceMatch(param);

            if(response == null) return false;


        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.getResult().getScore() > 70 ;
    }

}
