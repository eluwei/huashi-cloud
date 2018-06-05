package com.huashi.cloud.common.repository;

import com.huashi.cloud.common.exception.FieldAccessException;
import com.huashi.cloud.common.page.PageBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by konghao on 2016/12/7.
 */
@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T,ID> {

    //sql原生查询
    List<Map<String, Object>> listBySQL(String sql);

    /**
     * 查找对象，找到时返回对象，找不到返回null。
     * @param <T>
     * @param clasz 对象类型
     * @param id 对象ID
     * @return 对应指定的对象
     */
     <T> T find(Class<T> clasz, Object id) throws FieldAccessException;

    /**
     * 查找第一个对象，找到时返回对象，找不到返回null。
     * @param <T>
     * @param clasz 对象类型
     * @return 第一个对象
     */
     <T> T findFirst(Class<T> clasz) throws FieldAccessException;

    /**
     * 查找第一个对象，找到时返回对象，找不到返回null。
     * @param <T>
     * @param clasz
     * @param conditions 查询条件
     * @param args 查询参数
     * @return 第一个对象
     */
     <T> T findFirst(Class<T> clasz, String conditions, Object[] args) throws FieldAccessException;

    /**
     * 查找第一个对象，找到时返回对象，找不到返回null。
     * @param <T>
     * @param clasz
     * @param conditions 查询条件
     * @param args 查询参数
     * @param order 排序
     * @return 第一个对象
     */
     <T> T findFirst(Class<T> clasz, String conditions, Object[] args, String order) throws FieldAccessException;




    /**
     * 查找第一个对象，找到时返回对象，找不到返回null。
     * @param <T>
     * @param clasz
     * @param conditions 查询条件
     * @param args 查询参数
     * @param order 排序
     * @param limit 返回最大数量
     * @return 第一个对象
     */
    <T> T findFirst(Class<T> clasz, String conditions, Object[] args, String order, int limit) throws FieldAccessException;

    /**
     * 查找第一个对象，找到时返回对象，找不到返回null。
     * @param <T>
     * @param clasz
     * @param conditions 查询条件
     * @param args 查询参数
     * @param order 排序
     * @param limit 返回最大数量
     * @param offset 查找的偏移位置
     * @return 第一个对象
     * @throws 
     */
    <T> T findFirst(Class<T> clasz, String conditions, Object[] args, String order, int limit, int offset) throws FieldAccessException;
    

    /**
     * 查找对象集合。
     * @param <T>
     * @param clasz
     * @return 对象集合
     * @throws 
     */
    <T> List<T> findAll(Class<T> clasz) throws FieldAccessException;

    /**
     * 查找对象集合。
     * @param <T>
     * @param clasz
     * @param conditions 查询条件
     * @return 对象集合
     * @throws 
     */
    <T> List<T> findAll(Class<T> clasz, String conditions)  throws FieldAccessException;

    /**
     * 查找对象集合。
     * @param <T>
     * @param clasz
     * @param conditions 查询条件
     * @param args 查询参数
     * @return 对象集合
     * @throws 
     */
    <T> List<T> findAll(Class<T> clasz, String conditions, Object[] args) throws FieldAccessException;

    /**
     * 查找对象集合。
     * @param <T>
     * @param clasz
     * @param conditions 查询条件
     * @param args 查询参数
     * @param order 排序
     * @return 对象集合
     * @throws 
     */
    <T> List<T> findAll(Class<T> clasz, String conditions, Object[] args, String order)  throws FieldAccessException;

    /**
     * 查找对象集合。
     * @param <T>
     * @param clasz
     * @param conditions 查询条件
     * @param args 查询参数
     * @param order 排序
     * @param limit 返回最大数量
     * @return 对象集合
     * @throws 
     */
    <T> List<T> findAll(Class<T> clasz, String conditions, Object[] args, String order, int limit)  throws FieldAccessException;

    /**
     * 查找对象集合。
     * @param <T>
     * @param clasz
     * @param conditions 查询条件
     * @param args 查询参数
     * @param order 排序
     * @param limit 返回最大数量
     * @param offset 查找的偏移位置
     * @return 对象集合
     * @throws 
     */
    <T> List<T> findAll(Class<T> clasz, String conditions, Object[] args, String order, int limit, int offset) throws FieldAccessException ;

    /**
     * 查找对象集合
     * @param <T>
     * @param clasz
     * @param sql SQL语句
     * @return 对象集合
     * @
     */
    <T> List<T> findBySql(Class<T> clasz, String sql) throws FieldAccessException;

    /**
     * 查找对象集合
     * @param <T>
     * @param clasz
     * @param sql SQL语句
     * @param args 查询参数
     * @return 对象集合
     * @
     */
    <T> List<T> findBySql(Class<T> clasz, String sql, Object[] args) throws FieldAccessException;
    /**
     * 查找对象集合
     * @param <T>
     * @param clasz
     * @param sql SQL语句
     * @param args 查询参数
     * @param order 排序
     * @return 对象集合
     * @
     */
    <T> List<T> findBySql(Class<T> clasz, String sql, Object[] args, String order) throws FieldAccessException;
    /**
     * 查找对象集合
     * @param <T>
     * @param clasz
     * @param sql SQL语句
     * @param args 查询参数
     * @param order 排序
     * @param limit 返回最大数量
     * @return 对象集合
     * @
     */
    <T> List<T> findBySql(Class<T> clasz, String sql, Object[] args, String order, int limit) throws FieldAccessException;

    /**
     * 通过SQL查找对象集合，按照clasz指定的连接操作，如果找不到连接就使用ActiveRecordBase所指定的连接
     * @param <T>
     * @param clasz
     * @param sql SQL语句
     * @param args 查询参数
     * @param order 排序
     * @param limit 返回最大数量
     * @param offset 查找偏移位置
     * @return 对象集合
     * @
     */
    <T> List<T> findBySql(Class<T> clasz, String sql, Object[] args, String order, int limit, int offset) throws FieldAccessException;

    /**
     * 通过SQL查找对象集合，并查询总数，按照clasz指定的连接操作，如果找不到连接就使用ActiveRecordBase所指定的连接
     * @param <T>
     * @param clasz
     * @param sql SQL语句
     * @param args 查询参数
     * @param order 排序
     * @param pageBean 分页对象
     * @return 对象集合
     * @
     * @author Administrator 2016年3月3日
     */
    <T> List<T> findBySql(Class<T> clasz, String sql, Object[] args, String order, PageBean pageBean) throws FieldAccessException;

    /**
     * 单个对象的分页查询（不含参数）
     * @param clasz    查询的累
     * @param pageBean
     * @return
     * @
     */
    <T> PageBean find(Class<T> clasz,String order,PageBean pageBean) throws FieldAccessException;

    /**
     * 单个对象的条件查询，(不含排序)
     * @param clasz
     * @param conditions
     * @param args
     * @param pageBean
     * @return
     * @
     */

    <T> PageBean find(Class<T> clasz, String conditions, Object[] args,PageBean pageBean) throws FieldAccessException;

    /**
     * 单个对象的查询
     * @param clasz    连接的基类
     * @param conditions 查询参数
     * @param order 排序
     * @param args
     * @param pageBean
     * @return
     * @
     */
    <T> PageBean find(Class<T> clasz, String conditions,String order, Object[] args,PageBean pageBean) throws FieldAccessException;

    /**
     * 将查询结果ResultSet映射成Map集合，多用于统计。
     * @param sql 查询统计语句
     * @param args 查询参数
     * @param order 排序
     * @param limit 返回最大数量
     * @param offset 返回偏移位置
     * @return Map集合
     * @throws 
     */
    List<Map<String,Object>> getResultMap(String sql, Object[] args, String order, int limit, int offset) throws FieldAccessException;

    /**
     * 将查询结果ResultSet映射成Map集合,并查找总条数，多用于分页统计。
     * @param sql 查询统计语句
     * @param args 查询参数
     * @param order 排序
     * @param pageBean 分页对象
     * @return Map集合
     * @throws 
     */
    List<Map<String,Object>> getResultMap(String sql, Object[] args, String order, PageBean pageBean) throws FieldAccessException;

    /**
     * 将查询结果ResultSet映射成Map集合,并查找总条数，多用于分页统计。
     * @param sql 查询统计语句
     * @param args 查询参数
     * @param order 排序
     * @param pageBean 分页对象
     * @param searchPrev  当前页数大于总页数时，是否将当前页数置为总页数
     * @return Map集合
     * @throws 
     */
    List<Map<String,Object>> getResultMap(String sql, Object[] args, String order, PageBean pageBean, Boolean searchPrev) throws FieldAccessException;

    /**
     * 统计数量
     * @param c 被统计类
     * @param conditions 条件表达式
     * @param args 条件参数
     * @return 统计结果数量
     * @
     */
    int count(Class<?> c, String conditions, Object[] args) throws FieldAccessException;

    Object executeScalar(String sql, Object[] args) throws FieldAccessException;


}


