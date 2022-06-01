# 代码生成器使用注意事项

若表结构设计中，字段的注释按照规范编写了@Echo()注解，可以直接将@Echo中引用的常量新增在3个文件中，但存在以下问题：

1. 重复生成某个表时，类中已经存在的常量会重复生成。
2. 批量生成多个表时，生成的常量可能会被覆盖！
3. 生成的EchoApi常量，只能保证编译通过，请按业务编写实现类，并修改常量中的值

```
    # 如生成以下常量，需要将后面的"DICTIONARY_ITEM_FEIGN_CLASS"改成自己实现的正确的接口
    String DICTIONARY_ITEM_FEIGN_CLASS = "DICTIONARY_ITEM_FEIGN_CLASS";
```
