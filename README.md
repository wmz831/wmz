SpringBoot常用技术整合

Java文件上传下载原理：
1、上传原理：表单enctype="multipart/form-data"
    1)application/x-www-form-urlencoded：默认编码方式，只处理表单域里value属性值，这种编码方式会把表单域的值处理成URL编码方式
    2)multipart/form-data：表单会以二进制流的方式来处理表单数据，这种编码方式会把文件域制定文件的内容封装到请求参数里
    3)
2、下载原理：
    STEP1:通过HttpServletResponse.setContentType方法设置Content-Type头字段的值，eg.application/octet-stream,application/msdownload  
    STEP2:通过HttpServletResponse.setHeader方法设置Content-Disposition头的值为 attachment;filename=文件名
    STEP3:读取下载文件，调用HttpServletResponse.getOutputStream方法返回的ServletOutputStream对象来向客户端写入附件文件内容


