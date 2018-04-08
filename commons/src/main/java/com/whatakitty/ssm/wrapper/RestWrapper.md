## RestWrapper

用于将服务层返回的对象，根据业务或者安全性的需求考虑，选择性的返回特定的某些属性。

### 用法

根据不同的需求可以做具体的处理。

#### 基础用法

最简单的用法是创建一个RestWrapper实例，并且选择需要返回的属性；之后封装需要重构的对象即可。

如下代码描述的，对`obj`实例对象选择两个属性`name`和`address`返回给接口调用方。
```java
// 创建一个RestWrapper实例，并解析
RestWrapper restWrapper = RestWrapper
    .create("name", "address")
    .wrap(obj);
```

#### 如何处理集合类数据？

不管是集合类的数据还是单个对象或`Map`，`RestWrapper`都是一样的处理方式。

所需要注意的是：
* 对于单个对象，属性筛选的是对象本身的属性
* 对于集合类数据，属性筛选的是集合中每个`item`内的属性

### 高级用法

接下来，对于某些特殊场景做一些简单的介绍

#### 属性处理

如果对于对象的某些属性需要特殊处理呢？

举个例子来说，由于`js`处理的数组有最大最小值限制，如果一旦Java本身返回了过大或者过小的数字，那么很可能导致精度缺失。

这个时候，就需要对对象本身的这个属性做类型转化，将数值类型转化为字符串类型；这样，在传递给前端的过程中就不会出现精度缺失的情况。

如下代码：
```java
RestWrapper restWrapper = RestWrapper
    .create("id")
    .addHandler("id", id -> String.valueOf(id))
    .wrap(obj);
```

#### 复用`RestWrapper`

某些时候，发现基本上都是相同的属性筛选处理，那么如何做到复用呢？

具体代码如下：

在某处申明一个restWrapper实例，比如：
```java
private final RestWrapper restWrapper;

public XXXController() {
    this.restWrapper = RestWrapper
        .create("id", "name")
        .addHandler("id", id -> String.valueOf(id));
}
```

然后在需要封装对象的地方调用就可以了。
```java
@RequestMapping(path = "/list", method = RequestMethod.GET)
public Object list() {
    return restWrapper.wrap(page);
}
```

#### 新构建对象

某些情况下，需要凭空构建一个对象，而不是在一个已存在的对象基础上进行处理。这个时候可以使用`RestWrapper`的`build`方法。

用法如下：
```java
Map<String, Object> nameProvider;
Map<String, Object> numberProvider;

Map<String, Object> result = RestWrapper
    .create("name", "number")
    .addHandler("name", name -> {
        // 注意，`name`的值是无效的，无法使用
        return nameProvider.get("name");    // 从name提供方处获取name值
    })
    .addHandler("number", number -> {
        return numberProvider.get("number");    // 从number提供方处获取number值
    })
    .build();       // 根据上述的属性和属性处理构建对象
```

再举一例，比如我们需要对`Page`做处理，并返回给前端`Page`的一些信息：
```java
TestUser testUser1 = new TestUser();
testUser1.setName("WhatAKitty1");
testUser1.setSex("男");
TestUser testUser2 = new TestUser();
testUser2.setName("WhatAKitty2");
testUser2.setSex("男");
TestUser testUser3 = new TestUser();
testUser3.setName("WhatAKitty3");
testUser3.setSex("男");
Page<TestUser> page = new PageImpl<>(Arrays.asList(testUser1, testUser2, testUser3));

RestWrapper restWrapper = RestWrapper
    .create("page", "list")
    .addHandler("page", pageProperty -> {
        Map<String, Object> map = new HashMap<>();
        map.put("pageNum", page.getNumber());
        map.put("pageSize", page.getTotalPages());
        map.put("totalRow", page.getTotalElements());
        return map;
    })
    .addHandler("list", list -> RestWrapper
        .create("name")
        .addHandler("name", val -> String.valueOf(val) + "$")
        .wrap(page));

Map<String, Object> result = restWrapper.build();
// result就是我们需要的对象，里面包含了page信息和list列表
```

这个对Page的处理可以抽离成一个单独的RestPageWrapper类。

### 测试用例

具体测试用例查看：
`test/com.gnet.commons.wrapper.RestWrapperTest.java`
