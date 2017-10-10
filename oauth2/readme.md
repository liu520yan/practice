## oauth2+jwt 调用方法示例

-----
> - ### 工具：postman

-----
> - ### 通过用户密码请求得到jwt示例
![image](http://ohqtksm07.bkt.clouddn.com/oauth2+jwt.png)
-----
> - ### 通过jwt获取资源示例
![image](http://ohqtksm07.bkt.clouddn.com/%E9%80%9A%E8%BF%87jwt%E8%8E%B7%E5%8F%96%E8%B5%84%E6%BA%90.png)
-----
> - ### 刷新token方式示例

1. ##### password方式 同通过用户密码请求得到jwt示例
2. ##### refresh_token 方式
![image](http://ohqtksm07.bkt.clouddn.com/refresh_token.png)
> - ### client_credentials方式（客户端凭证许可）
注：这种方式需要在ClientDetailsServiceConfigurer中配置authorities否则没有任何权限。
![image](http://ohqtksm07.bkt.clouddn.com/client_credentials.png)
> - ### authorization_code方式（授权码许可）

其他的没用到懒得看了

---－
