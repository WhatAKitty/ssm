## OrderByUtils.getOrderBy

解析Sort, 获取正确的排序方式

### 参数

* sort: Pageable中的属性，封装了请求中的排序方式
* orderMapping: 自定义的排序映射

### 用法

```java
Sort.Order orderType = new Sort.Order(Sort.Direction.DESC, "type");
Sort.Order orderName = new Sort.Order(Sort.Direction.ASC, "name");
List<Sort.Order> orderList = Arrays.asList(orderType, orderName);
Sort sort = new Sort(orderList);

String order = OrderByUtils.getOrderBy(sort, ImmutableMap.of(
    "name", "name1",
    "type", "type1"));
```