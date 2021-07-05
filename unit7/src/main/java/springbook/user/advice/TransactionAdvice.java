package springbook.user.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionAdvice implements MethodInterceptor {

    PlatformTransactionManager transactionManager;
    TransactionDefinition transactionDefinition;

    public TransactionAdvice(PlatformTransactionManager manager) {
        this.transactionManager = manager;
        this.transactionDefinition = new DefaultTransactionDefinition(); // Transaction 설정 부분
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        TransactionStatus status =
                this.transactionManager.getTransaction(
                        transactionDefinition
                );

        try {
            System.out.println("Proxy!!!");
            Object ret = methodInvocation.proceed();
            this.transactionManager.commit(status);
            return ret;
        }catch (Exception e) {
            this.transactionManager.rollback(status);
            throw e;
        }
    }
}
