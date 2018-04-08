## CollectionUtils.takeIntersection

根据自定义比较条件获取两个集合的交集数据

```java
public static <T, K, W> List<T> takeIntersection(Collection<T> source, Collection<K> target, Function<T, W> sourceFunction, Function<K, W> targetFunction) {
    return source.parallelStream().filter(
        t -> target.parallelStream().filter(k -> sourceFunction.apply(t).equals(targetFunction.apply(k))).count() > 0
    ).collect(Collectors.toList());
}
```

### 参数

* sourceList: 待比较数据（所得数据从该参数中获取） 

* targetList: 比较数据

* sourceFunction: 待比较数据自定义比较方法

* targetFunction: 比较数据自定义比较方法


### 用法

#### 简单类型数据比较

```java
List<Integer> sourceList = Arrays.asList(100, 101, 102, 103);
List<Integer> targetList = Arrays.asList(103, 104, 105, 106);
List<Integer> list = CollectionUtils.takeIntersection(sourceList, targetList, i -> i, i -> i);
```

#### 复杂对象数据比较

```java
List<TestUser> sourceList = Arrays.asList(new TestUser("aaa", 16), new TestUser("bbb", 17), new TestUser("ccc", 18));
List<TestUser> targetList = Arrays.asList(new TestUser("ccc", 18), new TestUser("ddd", 19), new TestUser("eee", 17));

List<TestUser> testUsersAge = CollectionUtils.takeIntersection(sourceList, targetList, testUser -> testUser.age, testUser -> testUser.age);
List<TestUser> testUsersName = CollectionUtils.takeIntersection(sourceList, targetList, testUser -> testUser.name, testUser -> testUser.name);
```
### 注意

自定义比较方法返回的类型不可以是基本数据类型

### 测试用例

/src/test/java/com/gnet/commons/utils/CollectionUtilsTest.java
