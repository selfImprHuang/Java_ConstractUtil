# 我的公众号
![](.readme/B5F8E89A-CE33-4a78-A461-E9014B10A3D9.png)

# 工具说明
本工程提供ContrastUtil.java作为通用同类型对象处理工具类，处理规则如下（有没有测试到的bug，欢迎指教.qq：851516902）：
    
    1.Collection和Map的处理当前类只会处理一层，也就是说比如List<泛型>：这个泛型类型会直接调用equals进行处理，可能照成结果的不准确（可以通过Abstract类进行重写）
    2.如果比较的不是结构体而是基本类型，返回兼容结果
    3.比较的两个对象类型必须一致
    4.没有处理Collection的入参和Map的入参，后续会加上
    5.入参可以为空
    6.自定义类型没有Get、Set方法会进行报错。不会比较
    7、自定义类型必须重写equals和hashcode方法，否则不会比较值，而是比较对象地址（这边提供一种自处理的方式，可参考lombok的处理，直接修改AST，这样就算用户不重写equals也会直接比较对应值）
    8、
    
本工程提供AbstractContrastUtil.java作为对象对比抽象基类，提供isSpecialType的判断，可以在对象属性比较的时候进行对应类型的拦截处理，并使用者需要实现Collection、Map、基本类型、自定义类型的处理
提供最大的自定义性，因为对应的类型太多，ContractUtil没办法一并处理，所以提供对应抽象类。抽象类的继承示例可参考：ContrastUtilExample.java。该类的实现主要参考ContractUtil.java。

本工程对于一些抽象类的方法没有非常详细的注释，可直接参考Contract.java的实现处理。

本工程比较通用而且直接使用反射的方式，在处理速度上面会有一定的限制，后期可修改为内省的方式处理，且处理情况过多，没有覆盖所有的测试场景，希望读者可以提出宝贵的意见和建议。
    
# 更新日志
2020/4/30: 创建项目、单测，进行第一轮简单的测试


2020/5/9:添加测试方法，修改bug（反射处理null出现空指针的问题比较多），创建抽象类和继承使用示例。添加readme修改记录