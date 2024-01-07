package top.tangyh.lamp.generator.converts.select;


import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 分支提供者
 *
 * @author hanchunlin
 * @author zuihou
 * Created at 2020/6/11 17:19
 * @see BranchBuilder
 */
public interface Branch<P, T> {

    /**
     * 工厂方法，快速创建分支
     *
     * @param tester  测试器
     * @param factory 值工厂
     * @param <P>     参数类型
     * @param <T>     值类型
     * @return 返回一个新的分支
     */
    static <P, T> Branch<P, T> of(Predicate<P> tester, Function<P, T> factory) {
        return new Branch<P, T>() {

            @Override
            public Predicate<P> tester() {
                return tester;
            }

            @Override
            public Function<P, T> factory() {
                return factory;
            }

        };
    }

    /**
     * 条件
     *
     * @return 分支进入条件
     */
    Predicate<P> tester();

    /**
     * 工厂
     *
     * @return 值工厂
     */
    Function<P, T> factory();
}
