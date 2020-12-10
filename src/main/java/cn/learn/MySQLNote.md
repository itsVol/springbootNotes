##learn MySQL in 2hours
###了解mysql：
    Oracle公司
        oracle数据库 国外用的比较多，贵
        mysql 数据库 开源   mysql++ 商业版（商业化支持）

参考文章：http://www.justdojava.com/2020/11/17/mysql-sql/

```roomsql

CREATE DATABASE IF NOT EXISTS test_db default charset utf8mb4 COLLATE utf8mb4_unicode_ci;
USE test_db
CREATE TABLE ts_user (
id bigint(20) unsigned NOT NULL COMMENT '编码',
name varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户姓名',
mobile varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '手机号',
create_userid varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人',
create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
update_userid varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '更新人',
update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (id),
KEY idx_create_time (create_time) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';
ALTER TABLE ts_user RENAME TO ts_new_user;
show full columns from ts_new_user;
ALTER  TABLE ts_new_user add column gender tinyint(4) NOT NULL DEFAULT '1' COMMENT '性别，1，男；2，女'AFTER mobile;
ALTER TABLE ts_new_user modify column mobile varchar(30) NOT NULL DEFAULT '' COMMENT '用户手机号';
show INDEXES FROM ts_new_user;
ALTER table ts_new_user add index idx_id(id);
ALTER table ts_new_user add unique index idx_iid(id);
alter table ts_new_user drop index idx_id;

SELECT * FROM ts_new_user;
SELECT id,name FROM ts_new_user;
SELECT id,name FROM ts_new_user where name='hank' and name='joden';
SELECT id,name FROM ts_new_user where id>10 and id<20;

SELECT name,
(
CASE
WHEN id<6 THEN 'GOOD JOB'
WHEN id>=6 and id<16 THEN 'NOT BAD'
WHEN id>=16 THEN 'FAIL EXAM'
ELSE 'TOO BAD'
END
)AS judge
FROM ts_new_user;

INSERT INTO ts_new_user (id,name)
values (1,'n1'),(2,'n2'),(3,'n3');
UPDATE ts_new_user set name ='张三'WHERE id=1;
UPDATE ts_new_user set name ='李四'WHERE id=2;
UPDATE ts_new_user set name ='王武',gender =0 WHERE id=3;

```
