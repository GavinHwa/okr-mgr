# okr-mgr
okr后端部分

基于springboot+mybaits搭建
目前集成了swagger、druid

### 项目启动
1、在mysql数据库中执行okr.sql

2、修改okr-api中application.properties文件的数据库配置

3、打开okr-api中com.cmb.okr.api.OkrApp文件 run as Java Application

4、打开url:http://localhost:8001/okr/swagger-ui.html

5、

### 存储
存储使用Ali云的oss，需要在配置文件中添加如下配置

#### 访问OSS的域名
okr.oss.endpoint=

#### OSS的访问密钥
okr.oss.accessKeyId=
okr.oss.accessKeySecret=

#### Bucket用来管理所存储Object的存储空间
okr.oss.bucketName=
