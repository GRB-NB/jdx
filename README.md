# JDX

## 特别声明:

1. 本仓库涉及的任何解锁和解密分析脚本或代码，仅用于测试和学习研究，禁止用于商业用途，不能保证其合法性，准确性，完整性和有效性，请根据情况自行判断.
2. 请勿将本项目的任何内容用于商业或非法目的，否则后果自负.
3. 如果任何单位或个人认为该项目的脚本可能涉嫌侵犯其权利，则应及时通知并提供身份证明，所有权证明，我们将在收到认证文件后删除相关代码.
4. 任何以任何方式查看此项目的人或直接或间接使用本仓库项目的任何脚本的使用者都应仔细阅读此声明。rubyangxg 保留随时更改或补充此免责声明的权利。一旦使用并复制了任何本仓库项目的规则，则视为您已接受此免责声明.
5. 您必须在下载后的24小时内从计算机或手机中完全删除以上内容.
6. 您使用或者复制了本仓库且本人制作的任何脚本，则视为已接受此声明，请仔细阅读

## 安装说明

本项目已打包成`docker`镜像，拉取配置即可使用
> docker安装方法不再赘述

### 1. 拉取并运行docker

```dockerfile
docker run -d \
    -v <config dir>:/jdx/config \
    -p <port>:80 \
    --restart=always \
    --name jdx registry.cn-hangzhou.aliyuncs.com/yiidii-hub/jdx:v0.1.6
```
> 这里命令自行替换卷和端口映射
> 
> 例如：
> ```dockerfile
> docker run -d \
>   -v  /data/jdx/config:/jdx/config \
>   -p 5702:80 \
>   --restart=always \
>   --name jdx registry.cn-hangzhou.aliyuncs.com/yiidii-hub/jdx:v0.1.6
> ```
> 
注意：
 - 记得放行端口

### 访问
这时候访问 `http://ip:port/` 就能访问了

### 后台管理配置
后台访问需要的映射的`config`目录修改`config.json`文件。

socialPlatforms为社交登录的配置，说明如下：
- `source`： `GITEE` `GITHUB` `BAIDU`
- `clientId`：社交平台的配置应用的`clientId`
- `clientSecret`：社交平台的配置应用的`clientSecret`
- `redirectUri`：`http://xxxx:xxx/oauth/callback/<source>`
  > 1. 这里的`source`必须为：`GITEE` 或 `GITHUB` （例：`http://xxxx:xxx/oauth/callback/GITEE`）
  > 
  > 2. `xxxx:xxx` 为`JDX`的域名或者`ip:port`
- `admin`：管理员的名称，多个以英文半角逗号`,`分割

注意：
- 社交登录的`source`目前只支持`GITEE` `GITHUB` `BAIDU`，请勿填写错误，推荐使用`GITEE`
- 配置文件不能写任何注释，必须是一个完整的json文件

**配置应用相关文档**
- [Gitee](https://gitee.com/api/v5/oauth_doc#/list-item-3)
  > `GITEE` 获取自己用户名的方法: 登录 -> 点击右上角头像 -> 个人主页 -> 地址栏的`https://gitee.com/`之后的就是自己的用户名
- [Github](https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app)
  > `GITHUB` 获取自己用户名的方法: 登录 -> 点击右上角头像 -> Your profile -> 地址栏的`https://github.com/`之后的就是自己的用户名
- [Baidu](http://developer.baidu.com/wiki/index.php?title=docs/pcs/guide/app_create)

## 使用说明
1. QL配置只能删除和新增，不能编辑操作
2. 所有涉及编辑和删除的操作，左滑即可（就像微信删除最近联系人一样...）

其他问题可以空Q&A

## Q&A
**Q: 点击登录出现: `{"error":"Application does not exist"}`**

A: 在对应的平台正确配置正确的应用，特别是应用回调地址，详情看上方**配置应用相关文档**

## 更新说明
1. 停止并删除容器
```shell
docker stop jdx && docker rm jdx
```
2. 根据最新版本号跑一个新的容器即可