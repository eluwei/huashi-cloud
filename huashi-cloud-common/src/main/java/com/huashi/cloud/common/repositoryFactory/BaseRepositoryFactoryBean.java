package com.huashi.cloud.common.repositoryFactory;

import com.huashi.cloud.common.qiniu.QiniuStorage;
import com.huashi.cloud.common.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by konghao on 2016/12/7.
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

    @Autowired
    QiniuStorage qiniuUtil;


    public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new BaseRepositoryFactory(em, qiniuUtil);
    }

    //创建一个内部类，该类不用在外部访问
    private static class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

        private final EntityManager em;

        QiniuStorage qiniuUtil;

        public BaseRepositoryFactory(EntityManager em, QiniuStorage qiniuUtil) {
            super(em);
            this.em = em;
            this.qiniuUtil = qiniuUtil;
        }

        //设置具体的实现类是BaseRepositoryImpl
        @Override
        protected Object getTargetRepository(RepositoryInformation information) {
            return new BaseRepositoryImpl<T, I>((Class<T>) information.getDomainType(), em, qiniuUtil);
        }

        //设置具体的实现类的class
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }
}

