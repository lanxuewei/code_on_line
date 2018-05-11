-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE `code_on_line` CHARACTER SET utf8 COLLATE utf8_general_ci;
-- 使用数据库
USE code_on_line;

-- 创建user表（用户表用于存储用户账号信息）
CREATE TABLE IF NOT EXISTS `user`(
  `id` BIGINT AUTO_INCREMENT COMMENT 'id',
  `user_name` VARCHAR(20) NOT NULL COMMENT '用户名',
  `password` VARCHAR(32) NOT NULL COMMENT '密码',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '权限默认学生 0表示老师以及管理员 1表示学生',
  `real_name` VARCHAR(20) NOT NULL COMMENT '姓名',
  `sex` TINYINT NOT NULL DEFAULT 1 COMMENT '性别默认男生 0表示女 1表示男',
  `img` VARCHAR(120) COMMENT '头像',
  `des` TEXT COMMENT '个性语录',

  -- 主键
  PRIMARY KEY (id),
  UNIQUE (user_name),
  -- 索引
  KEY (status),
  KEY (real_name),
  KEY (sex)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='用户表';

-- 插入测试数据
INSERT INTO user(`user_name`,`password`,`status`,`real_name`,`sex`,`des`)
    VALUES
      ('lanxuewei','520027',0,'lanxuewei',1,'主人太懒了，不想说话。');

-- 问题表(用于存储各种问题)
CREATE TABLE IF NOT EXISTS `problem`(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'problem id',
  `name` VARCHAR(128) NOT NULL COMMENT '题目名',
  `des` TEXT NOT NULL COMMENT '问题描述',
  `des_html` TEXT NOT NULL COMMENT '问题描述html',
  `difficulty` TINYINT NOT NULL COMMENT '题目难度 0:简单 1:中等 2:难',
  `submit` INT NOT NULL DEFAULT 0 COMMENT '提交次数,默认0',
  `fail` INT NOT NULL DEFAULT 0 COMMENT '失败次数,默认0',
  `success` INT NOT NULL DEFAULT 0 COMMENT '成功次数,默认0',
  `author` VARCHAR(128) NOT NULL COMMENT '作者',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态码',
  -- 主键
  PRIMARY KEY (id),
  -- 索引
  UNIQUE KEY (`name`),
  KEY (`difficulty`),
  KEY (`author`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='问题表';

-- 插入测试数据
insert into problem(`name`, `des`, `des_html` , `difficulty`, `author`) values ("test", "test des", "test des for html", 1, "lanxuewei");

-- 用例表(用于存储问题对应的各种用例)
CREATE TABLE IF NOT EXISTS `case`(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'case id',
  `problem_id` BIGINT NOT NULL COMMENT 'problem id',
  `input` VARCHAR(128) NOT NULL COMMENT '输入',
  `output` VARCHAR(128) NOT NULL COMMENT '输出',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态码',
  -- 主键
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='用例表';

-- 标签表(各类问题的分类标签)
CREATE TABLE IF NOT EXISTS `tag`(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'tag id',
  `name` VARCHAR(128) NOT NULL COMMENT '标签名',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态码',
  -- 主键
  PRIMARY KEY (id),
  -- 唯一
  UNIQUE (name)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='标签表';

-- 插入测试数据
insert into tag(`name`) values ("test6");

-- 问题-用例表(问题和用例关联表)
# CREATE TABLE IF NOT EXISTS `problem_case`(
#   `problem_id` BIGINT NOT NULL COMMENT 'problem id',
#   `case_id` BIGINT NOT NULL NOT NULL COMMENT 'case id',
#   `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
#   `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
#   `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态码',
#   -- 联合主键
#   PRIMARY KEY (problem_id, case_id)
# )ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='问题表';

-- 用户-题目表(用户和题目关联表，用于记录一个用户对于某一题的相关信息)
CREATE TABLE IF NOT EXISTS `user_problem`(
  `user_id` BIGINT NOT NULL COMMENT 'user id',
  `problem_id` BIGINT NOT NULL COMMENT 'problem id',
  `submit` INT NOT NULL COMMENT '提交次数',
  `fail` INT NOT NULL COMMENT '失败次数',
  `success` INT NOT NULL COMMENT '成功次数',
  `last_submit` TEXT NOT NULL COMMENT '最后一次提交记录代码',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态码',
  -- 主键
  PRIMARY KEY (user_id, problem_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='用户-问题表';

-- 问题-标签表(用于记录问题和标签对应关系)
CREATE TABLE IF NOT EXISTS `problem_tag` (
  `problem_id` BIGINT NOT NULL COMMENT 'problem id',
  `tag_id` BIGINT NOT NULL COMMENT 'tag id',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态码',
  -- 主键
  PRIMARY KEY (problem_id, tag_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='问题-标签表';

-- 用户-提交记录表(用于记录用户提交记录)
CREATE TABLE IF NOT EXISTS `user_record` (
  `id` BIGINT NOT NULL COMMENT '记录id',
  `user_id` BIGINT NOT NULL COMMENT 'user id',
  `problem_id` BIGINT NOT NULL COMMENT 'problem id',
  `is_success` TINYINT NOT NULL COMMENT '提交是否成功',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态码',
  -- 主键
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='用户-提交记录表';