# SSM Framework

## Technology Stack
* Spring MVC 3
* Spring 3
* Spring Security 3
* FreeMarker + FreeMarker Template Inheritance
* JQuery 1.12.4
* EasyUI Latest
* [EasyUI Super Theme](https://github.com/itcodes/easyui-super-theme)

## Features
* SSM
* Support code generate
* Support theme ui

## Preview

![common component preview](https://static.xuqiang.me/public/images/140108.png)
![book example preview](https://static.xuqiang.me/public/images/140123.png)

## RoadMap
- [x] Basic Framework
- [ ] Add more common ui component
- [ ] Add system functions user interface

## Framework Structure
ssm  
|  
|___ dependencies  项目包依赖管理  
|  
|___ commons       通用代码  
|  
|___ biz           业务代码  
|  
|___ api           接口模块  
|  
|___ webapp        页面渲染相关模块  
|  
|___ generator    代码生成模块  
|  
|___ doc           文档文件夹(包含SQL)  

## Usage

### Code Generation

* Run `Application.java` in `_generator` submodule
* First, you should set database configuration what the program asked
* Second, specific which table would you like to use
* And then, the generator will auto create the crud java files and ftl pages for you
* Last, the file window will auto open(The mac is finder app)
* All you can see is generated files, and put them into your projects and modify something
* All right, all things has done, you can run app in web container and see the views you want

## Attention

### Potential security vulnerability

JQuery 1.12.4

jQuery before 3.0.0 is vulnerable to Cross-site Scripting (XSS) attacks when a cross-domain Ajax request is performed

