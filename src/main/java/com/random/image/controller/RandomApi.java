package com.random.image.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomApi {
    private static OkHttpClient client = OkhttpUtils.okHttpClient();

    @Value("${imageUrlPath.img1}")
    private String imageFile;
    @Value("${imageUrlPath.3ciyuan}")
    private String image3ciyuan;
    @Value("${imageUrlPath.2ciyuan}")
    private String image2ciyuan;
    @Value("${imageUrlPath.all}")
    private String imageAll;

    @Value("${imageUrlPath.pc18}")
    private String pc18;
    @Value("${imageUrlPath.pccos}")
    private String pccos;
    @Value("${imageUrlPath.pcecy}")
    private String pcecy;
    @Value("${imageUrlPath.pcfj}")
    private String pcfj;
    @Value("${imageUrlPath.pcmv}")
    private String pcmv;
    @Value("${imageUrlPath.sj18}")
    private String sj18;
    @Value("${imageUrlPath.sjcos}")
    private String sjcos;
    @Value("${imageUrlPath.sjecy}")
    private String sjecy;
    @Value("${imageUrlPath.sjfj}")
    private String sjfj;
    @Value("${imageUrlPath.sjmv}")
    private String sjmv;
    @Value("${imageUrlPath.dyjk}")
    private String dyjk;
    @Value("${imageUrlPath.dycos}")
    private String dycos;

    @GetMapping(value = "/{path}",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] test2(@PathVariable(value = "path",required = false) String path) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("all",imageAll);
        map.put("18",image2ciyuan);
        map.put("p",image3ciyuan);
        map.put("all",imageAll);

        map.put("pc18",pc18);
        map.put("pccos",pccos);
        map.put("pcecy",pcecy);
        map.put("pcfj",pcfj);
        map.put("pcmv",pcmv);
        map.put("sj18",sj18);
        map.put("sjcos",sjcos);
        map.put("sjecy",sjecy);
        map.put("sjfj",sjfj);
        map.put("sjmv",sjmv);
        map.put("dyjk",dyjk);
        map.put("dycos",dycos);
        File file = new File(map.get(path));
        ArrayList<String> urls = ReadFiledata.txt2String(file);
        Random random = new Random();
        Request request = new Request.Builder()
                .url(urls.get(random.nextInt(urls.size())))
                .get()
                .build();
        Response response = client.newCall(request).execute();
        byte[] bytes1 = response.body().bytes();
        return bytes1;
    }

//    @GetMapping(value = "/",produces = MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
//    public byte[] test() throws IOException {
//        File file = new File(imageFile);
//        ArrayList<String> urls = ReadFiledata.txt2String(file);
//        Random random = new Random();
//        Request request = new Request.Builder()
//                .url(urls.get(random.nextInt(urls.size())))
//                .get()
//                .build();
//        Response response = client.newCall(request).execute();
//        byte[] bytes1 = response.body().bytes();
//        return bytes1;
//    }

}
