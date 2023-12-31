# 随机图片api
#### 介绍
java随机图片api 在不改变当前url的情况下刷新可获取新的图片
#### jar运行

1.  下载项目后idea打开
2.  在yml里面指定存放图片url的文件
3.  使用maven命令打jar包`mvn -B clean package -Dmaven.test.skip=true` 
4.  将打好的jar包上传者linux 并开放7777端口
5.  执行命令 nohup java -jar XXX.jar
6.  访问api   http://你的ip:7777/
#### docker
1. docker pull study996/imageapi
2. 运行docker run study996/imageapi:latest
3. 访问api   http://你的ip:7777/
4. docker run -v [txt目录] /  --name=imageapi   study996/imageapi:latest
#### 使用说明
1.  因为获取图片是通过url的方式,所以你可以自行搭配图床,或者从壁纸网站弄点图片url
注意: 不是从本地获取的图片,所以请求api的时候会受当前网络环境影响速度
2.  你可以在接口中指定图片url文件的路径分类进行访问
3. 访问：http://你的ip:7777/[imageUrlPath.xxx]
```
 @GetMapping(value = "/{path}",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] test2(@PathVariable(value = "path",required = false) String path) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("all",imageAll);
        map.put("18",image2ciyuan);
        map.put("p",image3ciyuan);
        //key=请求路径 value=yml配置文件里的自定义的路径名称
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
```
#### pr规则
1. 图片分类见名知意 
2. 根目录下添加xxx.txt
3. 在src/main/resources/application.yml中的imageUrlPath下添加
```demo
imageUrlPath:
  xxx: ./xxx.txt
```
4. 在RandomApi.java 中添加 demo
```demo
@RestController
public class RandomApi {
    private static OkHttpClient client = OkhttpUtils.okHttpClient();

    @Value("${imageUrlPath.xxx}")
    private String dycos;

    @GetMapping(value = "/{path}",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] test2(@PathVariable(value = "path",required = false) String path) throws IOException {
        HashMap<String, String> map = new HashMap<>();
       
        map.put("xxx",xxx);   
        
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
}
```
**注意** : img1.txt---img4.txt为demo