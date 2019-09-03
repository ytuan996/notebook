### 统一资源定位符URL

URL用来标识互联网上资源的位置和访问这些资源的方法。协议://<主机>:<端口>/<路径>

- 万维网的站点访问使用http协议，http的URL如:http://<主机>:<端口>/<路径>；默认端口是80，通常可以省略。

### 超文本传输协议 HTTP

工作流程：

![http工作流程](https://github.com/ytuan996/notebook/blob/master/network/image/www_process.png?raw=true)

- 每个万维网站点都有一个服务进程，监听在80端口，随时处理客户端的连接请求，一旦客户端TCP连接上之后，就可以处理
请求并将结果返回客户端，最后释放TCP连接。

- 这样客户端和服务端的交互需要遵循一定的规则，就是HTTP协议。

- HTTP使用面向连接的TCP传输，保证了数据的可靠传输，但是HTTP协议本身是无连接的

- HTTP协议是无状态的，服务器不会记住谁访问过，也不知道访问了多少次。这样简化的设计，使服务器可以大量并发处理请求。

- http发送一个请求的时间大概是RTT * 2

![http 请求时间](https://github.com/ytuan996/notebook/blob/master/network/image/http_request_time.png?raw=true)

- 针对http/1.0 无连接的缺点，在http/1.1中使用持续连接解决问题
    1. 非流水线方式：保持TCP连接，收到响应之后再发送下一个请求。比http/1.0节省了一个RTT时间。
    2. 流水线方式：在收到上一个报文响应前就可以连续发送下一个报文，增加了TCP连接的利用率。
    
#### 代理服务器的工作原理示意图（类似高速缓存）

![代理服务器工作原理](https://github.com/ytuan996/notebook/blob/master/network/image/proxy_server.png?raw=true)

### HTTP的报文结构

- 请求报文

请求方法 | 说明 |
-- | -- |
option | 请求一些选项的信息
get | 获取URL标识的资源 
post | 向服务器提交信息 
put | 在指定的URL下存储一个文档 
head | 读取URL标志信息的首部
delete | 删除URL表明的资源 
connect | 用于代理服务器
trace | 用于环回测试的请求报文

- 响应报文

常见状态码 | 说明
-- | -- |
1xxx | 表示通知，收到了或者正在处理
2xxx | 成功执行并返回
3xxx | 重定向，请求还需要进一步处理
4xxx | 客户端错误，请求报文错误或者资源不存在
5xxx | 服务端错误，服务失效无法完成请求

### 使用Cooike保存和服务器的状态

