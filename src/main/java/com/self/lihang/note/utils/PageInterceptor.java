package com.self.lihang.note.utils;

import com.self.lihang.note.pageModel.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

/**
 *  mybaits拦截器  spring boot整合添加@Component
 * */
@Component
@Intercepts({@Signature(type = StatementHandler.class,method = "prepare",args ={Connection.class,Integer.class} )})
public class PageInterceptor implements Interceptor {
    public Object intercept(Invocation invocation) throws Throwable {
        //获得mybatis中的StatementHandler以拦截正确的sql语句       RoutingStatementHandler实现类
        StatementHandler statementHandler=(StatementHandler) invocation.getTarget();

        //通过反射机制获得private属性
        MetaObject metaObject=SystemMetaObject.forObject(statementHandler);

        //获得sqlid  以获取方法名
        MappedStatement mappedStatement=(MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String id=mappedStatement.getId();

        // 如果该id以Page结尾(分页)
        if(id.matches(".+Page$")){
            //获得boundSql
            BoundSql boundSql=statementHandler.getBoundSql();

            //获得原始sql语句
            StringBuffer sql=new StringBuffer(boundSql.getSql());

            //拼接count(*)获得总记录数
            StringBuffer countSql=new StringBuffer("select count(*) from (");
            countSql.append(sql);
            countSql.append(")a");
            Connection connection=(Connection)invocation.getArgs()[0];
            PreparedStatement preparedStatement = connection.prepareStatement(countSql.toString());
            //通过反射机制获得statementhandler中的私有的parameterhandler
            ParameterHandler ph=(ParameterHandler)metaObject.getValue("delegate.parameterHandler");
            //通过parameterHandler.setParameters匹配preparedStatement中的参数
            ph.setParameters(preparedStatement);
            ResultSet resultSet=preparedStatement.executeQuery();

            // 获取方法的参数
            Map<String,Object> parameterObject=(Map<String, Object>) boundSql.getParameterObject();
            Page page=(Page) parameterObject.get("page");
            if(resultSet.next()) {
                page.setTotalCount(resultSet.getInt(1));
                page.computeStartRow();
            }

            //动态拼接分页查询语句
            sql.append(" limit ");
            sql.append(page.getStartRow());
            sql.append(",");
            sql.append(page.getPageSize());
            metaObject.setValue("delegate.boundSql.sql",sql.toString());
        }
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    public void setProperties(Properties properties) {

    }
}

