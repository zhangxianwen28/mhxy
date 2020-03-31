##### java.awt.AWTException: headless environment
解决办法: 在VM的Option里加上一句-Djava.awt.headless=false

##### 使用opencv需要添加
解决办法: 在VM的Option里加上一句-Djava.library.path=D:\opencv\opencv\build\java\x64