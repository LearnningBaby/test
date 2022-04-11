package com.student.studentservice.utils.facematch;

import com.student.studentservice.domain.FaceModel.Response;
import com.student.studentservice.utils.AuthServiceUtil;
import com.student.studentservice.utils.GsonUtils;
import com.student.studentservice.utils.HttpUtil;


public class FaceMatchUtil {

    public static Response faceMatch(String param) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthServiceUtil.getAuth();  //"[调用鉴权接口获取的token]";

            String result = HttpUtil.post(url, accessToken, "application/json", param);

            Response response = GsonUtils.fromJson(result, Response.class);

            System.out.println(response);

            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
