### DNS概述

一个目的，就是人们记不住那么多的IP地址，所以将IP地址和域名对应，方便使用和记忆。在ARPAENT时代，机器少，人们就把这样的关系记录本机的
hosts文件，现在我们仍在使用。

DNS系统的设计：其实就是一个联机的分布式数据库系统，采用C/S方式，大多数IP解析都在本地因此效率较高，也不怕出现单点故障。

域名到IP地址的解析过程：某一个应用程序需要把主机名解析为IP地址时，该应用程序就调用解析程序，并成为DNS的一个client，把待解析的域名
放入DNS的请求报文中，以UDP发送到本地域名解析服务器，本地域名服务器找到对应的IP后，放入应答报文并响应，应用程序获得主机IP地址即可通信。
如果本地域名服务器找不到，那么本地域名服务器又作为一个client请求上一级域名服务器，知道找到为止。

### 互联网的域名结构

![DNS的域名结构](https://github.com/ytuan996/notebook/blob/master/network/image/dns_structure.png?raw=true)

### DNS 域名服务器的设计( 划分区)

按照服务器管辖的范围分区

![DNS_server_structure](https://github.com/ytuan996/notebook/blob/master/network/image/dns_server_structure.png?raw=true)

1.根域名服务器：

2.顶级域名服务器:管理辖区所有注册的二级域名；最高层次的域名服务器，知道所有顶级域名服务器和IP地址。也非常重要。

3.权限域名服务器:分区中负责一个区的域名服务器。

4.本地域名服务器:DNS客户端查询时第一个请求的域名服务器。

### DNS查询IP的两种方式

![DNS查找IP的两种方式](https://github.com/ytuan996/notebook/blob/master/network/image/dns_search_example.png?raw=true)
