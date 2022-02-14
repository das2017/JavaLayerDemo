# JavaLayerDemo
订单服务

### 系统环境:
* dev：开发环境，使用环境变量CONFIG_PATH
* test：测试环境，查看K8S中的app-config配置项
* staging：测试环境，查看K8S中的app-config配置项
* prod：生产环境

### 开发环境配置：
1. 将k8s中的db.properties和app.properties 文件复制到你本地目录F:\workspace\config
2. 设置环境变量：CONFIG_PATH=F:\workspace\config
3. 设置spring.profiles.active=test

注：不修改此路径：@PropertySource(value = {"file:${CONFIG_PATH}/db.properties", "file:${CONFIG_PATH}/config.properties"})


### 模块划分：
* dto  (value object) 				传递对象
* controller						控制器
* service 							逻辑层
* service\bo (business object) 		业务对象
* dao          						数据层
* dao\entity  (persistant object) 	持久对象
* httpclient 						http客户端调用第三方服务
* listener 							消息队列监听
* job 						        job作业调度
* config							配置层 比如：数据库连接设置，应用配置
* common/redis  					缓存类
* common/jms 					    activemq
* common/auth 				        权限类
* common/custom  			        自定义
* common/constants 			        系统自定义的一些注解，异常等
* common/utils   				    常用的工具类

### 架构思想：
1. 不使用Spring Cloud或Spring Cloud Alibaba，侵入性太强。
2. 轻量级Spring Boot + Kubernetes微服务 + 少量中间件。让业务的归业务，基础的归基础。
3. 充分使用Kubernetes，以Kubernetes为母环境，如同基于Linux一样。
4. 云原生，充分利用云资源的优点，在云中土生土长，可以没有本地开发环境和测试环境，
5. 使用云中组件，开源优先，甚至大胆使用知名云/公有云的专有组件，如Alibaba API网关，PolarDB（已开源）
