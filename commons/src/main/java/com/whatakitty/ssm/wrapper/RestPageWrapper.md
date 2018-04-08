## RestPageWrapper

通过RestWrapper构建分页信息包装对象

### 用法

```java
List<TestUser> list = Arrays.asList(new TestUser("aaa", 16), new TestUser("bbb", 17), new TestUser("ccc", 18));
Page page = new Page(2, 10);
page.setTotal(list.size());
page.addAll(list);
RestWrapper restWrapper = RestWrapper.create("name").addHandler("name", name -> name + "%");
Object wrap = RestPageWrapper.wrap(page, restWrapper);
```

传入查询得到的分页信息和对数据处理的Wrapper，
RestPageWrapper.wrap将会构造出包含
"pageNum", "pageSize", "totalRow", "list"
这些key的的Map对象