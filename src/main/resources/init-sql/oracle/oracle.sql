--系统管理模块相关表
-- ----------------------------
-- Sequence structure for SEQ_SYS_PERMISSION
-- ----------------------------
declare
      num  number;
begin
    select count(1) into num from user_sequences where sequence_name = upper('SEQ_SYS_PERMISSION') ;
    if num > 0 then
        execute immediate 'DROP SEQUENCE "WBANK"."SEQ_SYS_PERMISSION"' ;
    end if;
end;
/
CREATE SEQUENCE "WBANK"."SEQ_SYS_PERMISSION"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999
 START WITH 19
 CACHE 20;
-- ----------------------------
-- Sequence structure for SEQ_SYS_ROLE
-- ----------------------------
declare
      num  number;
begin
    select count(1) into num from user_sequences where sequence_name = upper('SEQ_SYS_ROLE') ;
    if num > 0 then
        execute immediate 'DROP SEQUENCE "WBANK"."SEQ_SYS_ROLE"' ;
    end if;
end;
/
CREATE SEQUENCE "WBANK"."SEQ_SYS_ROLE"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999
 START WITH 1
 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_ROLE_PERMISSION
-- ----------------------------
declare
      num  number;
begin
    select count(1) into num from user_sequences where sequence_name = upper('SEQ_SYS_ROLE_PERMISSION') ;
    if num > 0 then
        execute immediate 'DROP SEQUENCE "WBANK"."SEQ_SYS_ROLE_PERMISSION"' ;
    end if;
end;
/
CREATE SEQUENCE "WBANK"."SEQ_SYS_ROLE_PERMISSION"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999
 START WITH 1
 CACHE 20;

-- ----------------------------
-- Sequence structure for SEQ_SYS_USER_ROLE
-- ----------------------------
declare
      num  number;
begin
    select count(1) into num from user_sequences where sequence_name = upper('SEQ_SYS_USER_ROLE') ;
    if num > 0 then
        execute immediate 'DROP SEQUENCE "WBANK"."SEQ_SYS_USER_ROLE"' ;
    end if;
end;
/
CREATE SEQUENCE "WBANK"."SEQ_SYS_USER_ROLE"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9999999
 START WITH 1
 NOCACHE ;

-- ----------------------------
-- Table structure for T_SYS_PERMISSION
-- ----------------------------
declare
      num  number;
begin
    select count(1) into num from user_tables where table_name = upper('T_SYS_PERMISSION') ;
    if num > 0 then
        execute immediate 'drop table T_SYS_PERMISSION' ;
    end if;
end;
/
CREATE TABLE "WBANK"."T_SYS_PERMISSION" (
"ID" NUMBER(11) NOT NULL ,
"PID" NUMBER(11) NULL ,
"NAME" VARCHAR2(50 BYTE) NULL ,
"TYPE" VARCHAR2(20 BYTE) NULL ,
"SORT" NUMBER(11) NULL ,
"URL" VARCHAR2(255 BYTE) NULL ,
"PERM_CODE" VARCHAR2(50 BYTE) NULL ,
"ICON" VARCHAR2(255 BYTE) NULL ,
"STATE" VARCHAR2(10 BYTE) NULL ,
"DESCRIPTION" VARCHAR2(500 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_SYS_PERMISSION
-- ----------------------------
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('1', null, '系统管理', 'F', '1', null, null, 'icon-standard-cog', null, '系统管理');
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('2', '1', '角色管理', 'F', '3', 'system/role', null, 'icon-hamburg-my-account', 'closed', null);
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('3', '2', '查看', 'O', null, null, 'sys:role:view', null, null, '角色查看');
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('4', '2', '添加', 'O', null, null, 'sys:role:add', null, null, '角色添加');
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('5', '2', '删除', 'O', null, null, 'sys:role:delete', null, null, '角色删除');
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('6', '2', '修改', 'O', null, null, 'sys:role:update', null, null, '角色修改');
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('7', '2', '保存授权', 'O', null, null, 'sys:role:permUpd', null, null, '保存修改的角色权限');
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('8', '2', '查看角色权限', 'O', null, null, 'sys:role:permView', null, null, '查看角色拥有的权限');
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('9', '1', '权限管理', 'F', '5', 'system/permission', null, 'icon-hamburg-login', 'closed', null);
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('10', '9', '添加', 'O', null, null, 'sys:perm:add', null, null, '菜单添加');
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('11', '9', '查看', 'O', null, null, 'sys:perm:view', null, null, '权限查看');
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('12', '9', '修改', 'O', null, null, 'sys:perm:update', null, null, '菜单修改');
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('13', '9', '删除', 'O', null, null, 'sys:perm:delete', null, null, '菜单删除');
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('14', '1', '菜单管理', 'F', '4', 'system/permission/menu', null, 'icon-hamburg-old-versions', 'closed', null);
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('15', '14', '修改', 'O', null, null, 'sys:perm:update', null, null, '菜单修改');
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('16', '14', '添加', 'O', null, null, 'sys:perm:add', null, null, '菜单添加');
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('17', '14', '删除', 'O', null, null, 'sys:perm:delete', null, null, '菜单删除');
INSERT INTO "WBANK"."T_SYS_PERMISSION" VALUES ('18', '14', '查看', 'O', null, null, 'sys:perm:menu:view', null, null, '菜单查看');

-- ----------------------------
-- Indexes structure for table T_SYS_PERMISSION
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_SYS_PERMISSION
-- ----------------------------
ALTER TABLE "WBANK"."T_SYS_PERMISSION" ADD PRIMARY KEY ("ID");


-- ----------------------------
-- Table structure for T_SYS_ROLE
-- ----------------------------
declare
      num  number;
begin
    select count(1) into num from user_tables where table_name = upper('T_SYS_ROLE') ;
    if num > 0 then
        execute immediate 'drop table T_SYS_ROLE' ;
    end if;
end;
/
CREATE TABLE "WBANK"."T_SYS_ROLE" (
"ID" NUMBER(11) NULL ,
"NAME" VARCHAR2(20 BYTE) NULL ,
"ROLE_CODE" VARCHAR2(20 BYTE) NULL ,
"DESCRIPTION" VARCHAR2(500 BYTE) NULL ,
"SORT" NUMBER(6) NULL ,
"DEL_FLAG" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_SYS_ROLE
-- ----------------------------
INSERT INTO "WBANK"."T_SYS_ROLE" VALUES (SEQ_SYS_ROLE.NEXTVAL, 'admin', 'admin', 'admin', '1', null);
INSERT INTO "WBANK"."T_SYS_ROLE" VALUES (SEQ_SYS_ROLE.NEXTVAL, 'guest', 'guest', 'guest', '2', null);


-- ----------------------------
-- Table structure for T_SYS_ROLE_PERMISSION
-- ----------------------------
declare
      num  number;
begin
    select count(1) into num from user_tables where table_name = upper('T_SYS_ROLE_PERMISSION') ;
    if num > 0 then
        execute immediate 'drop table T_SYS_ROLE_PERMISSION' ;
    end if;
end;
/
CREATE TABLE "WBANK"."T_SYS_ROLE_PERMISSION" (
"ID" NUMBER(11) NULL ,
"ROLE_ID" NUMBER(11) NULL ,
"PERMISSION_ID" NUMBER(11) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_SYS_ROLE_PERMISSION
-- ----------------------------
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '1');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '2');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '3');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '4');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '5');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '6');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '7');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '8');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '9');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '10');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '11');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '12');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '13');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '14');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '15');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '16');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '17');
INSERT INTO "WBANK"."T_SYS_ROLE_PERMISSION" VALUES (SEQ_SYS_ROLE_PERMISSION.NEXTVAL, '1', '18');


-- ----------------------------
-- Table structure for T_SYS_USER_ROLE
-- ----------------------------
declare
      num  number;
begin
    select count(1) into num from user_tables where table_name = upper('T_SYS_USER_ROLE') ;
    if num > 0 then
        execute immediate 'drop table T_SYS_USER_ROLE' ;
    end if;
end;
/
CREATE TABLE "WBANK"."T_SYS_USER_ROLE" (
"ID" NUMBER(11) NULL ,
"USER_ID" NUMBER(11) NULL ,
"ROLE_ID" NUMBER(11) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_SYS_USER_ROLE
-- ----------------------------
INSERT INTO "WBANK"."T_SYS_USER_ROLE" VALUES (SEQ_SYS_USER_ROLE.NEXTVAL, '151787', '1');
INSERT INTO "WBANK"."T_SYS_USER_ROLE" VALUES (SEQ_SYS_USER_ROLE.NEXTVAL, '170445', '1');