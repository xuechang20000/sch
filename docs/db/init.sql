create sequence SEQ_APP_USERID
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

-- Create table
create table APP_USER
(
  userid         NUMBER(19) primary key,
  loginname      VARCHAR2(100) not null,
  password       VARCHAR2(300) not null,
  domainid  NUMBER(19),
  authentic_type VARCHAR2(60),
  certificate    VARCHAR2(500),
  name           VARCHAR2(200),
  sex            VARCHAR2(1),
  telephone      VARCHAR2(100),
  address        VARCHAR2(500),
  mobile_phone   VARCHAR2(100),
  fax            VARCHAR2(100),
  email          VARCHAR2(500),
  msn            VARCHAR2(200),
  qq             VARCHAR2(100),
  admin          NUMBER(1),
  status         NUMBER(1)
);

-- Add comments to the table 
comment on table APP_USER
  is '用户信息';
-- Add comments to the columns 
comment on column APP_USER.userid
  is '用户标识';
comment on column APP_USER.loginname
  is '登录名';
comment on column APP_USER.password
  is '密码';
comment on column APP_USER.domainid
  is '所属应用域';
comment on column APP_USER.authentic_type
  is '认证类型';
comment on column APP_USER.certificate
  is '证书';
comment on column APP_USER.name
  is '姓名';
comment on column APP_USER.sex
  is '性别';
comment on column APP_USER.telephone
  is '联系电话';
comment on column APP_USER.address
  is '联系地址';
comment on column APP_USER.mobile_phone
  is '移动电话';
comment on column APP_USER.fax
  is '传真';
comment on column APP_USER.email
  is '电子邮箱';
comment on column APP_USER.msn
  is 'MSN';
comment on column APP_USER.qq
  is 'QQ';
comment on column APP_USER.admin
  is '是否管理';
comment on column APP_USER.status
  is '状态';

create index APP_USER_LOGINNAME on APP_USER(LOGINNAME);



-- Create table
create table APP_GROUP_USER
(
  groupid NUMBER(19) not null,
  userid  NUMBER(19) not null,
  status  NUMBER(1) not null
);
-- Add comments to the table 
comment on table APP_GROUP_USER
  is '组用户关联';
-- Add comments to the columns 
comment on column APP_GROUP_USER.groupid
  is '组标识';
comment on column APP_GROUP_USER.userid
  is '用户标识';
comment on column APP_GROUP_USER.status
  is '状态';
-- Create/Recreate primary, unique and foreign key constraints 
alter table APP_GROUP_USER
  add constraint PK_APP_GROUP_USER primary key (USERID, GROUPID);


create sequence SEQ_APP
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;


-- Create table
create table APP_APPLICATION
(
  appid              NUMBER(19) primary key,
  domainid           NUMBER(19),
  code               VARCHAR2(100),
  name               VARCHAR2(200),
  description        VARCHAR2(300),
  status           NUMBER(1)
);
-- Add comments to the table 
comment on table APP_APPLICATION
  is '应用系统';
-- Add comments to the columns 
comment on column APP_APPLICATION.appid
  is '应用标识';
comment on column APP_APPLICATION.domainid
  is '所属域';
comment on column APP_APPLICATION.code
  is '代码';
comment on column APP_APPLICATION.name
  is '名称';
comment on column APP_APPLICATION.description
  is '描述';
comment on column APP_APPLICATION.status
  is '状态';


-- Create table
create table APP_DOMAIN
(
  domainid    NUMBER(19) primary key,
  code        VARCHAR2(60),
  name        VARCHAR2(300),
  description VARCHAR2(300),
  admin       NUMBER(1),
  status     NUMBER(1) not null
);
-- Add comments to the table 
comment on table APP_DOMAIN
  is '域信息';
-- Add comments to the columns 
comment on column APP_DOMAIN.domainid
  is '域标识';
comment on column APP_DOMAIN.code
  is '域代码';
comment on column APP_DOMAIN.name
  is '域名称';
comment on column APP_DOMAIN.description
  is '描述';
comment on column APP_DOMAIN.admin
  is '是否管理';
comment on column APP_DOMAIN.status
  is '状态';


create sequence SEQ_APP_GROUPID
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

-- Create table
create table APP_GROUP
(
  groupid            NUMBER(19) primary key,
  domainid           NUMBER(19),
  group_type         VARCHAR2(30),
  code               VARCHAR2(100),
  name               VARCHAR2(200),
  description        VARCHAR2(300),
  status              NUMBER(1)
);
-- Add comments to the table 
comment on table APP_GROUP
  is '组信息';
-- Add comments to the columns 
comment on column APP_GROUP.groupid
  is '组标识';
comment on column APP_GROUP.domainid
  is '所属域';
comment on column APP_GROUP.group_type
  is '组类型';
comment on column APP_GROUP.code
  is '代码';
comment on column APP_GROUP.name
  is '名称';
comment on column APP_GROUP.description
  is '描述';
comment on column APP_GROUP.status
  is '状态';


create sequence SEQ_APP_NODEID
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;


-- Create table
create table APP_ORGAN_NODE
(
  nodeid          NUMBER(19) primary key,
  parentnodeid    NUMBER(19),
  nodetypeid      NUMBER(19),
  domainid        NUMBER(19),
  groupid         NUMBER(19),
  value           VARCHAR2(100),
  name            VARCHAR2(100),
  description     VARCHAR2(300),
  status          NUMBER(1)
);
-- Add comments to the table 
comment on table APP_ORGAN_NODE
  is '组织节点';
-- Add comments to the columns 
comment on column APP_ORGAN_NODE.nodeid
  is '节点标识';
comment on column APP_ORGAN_NODE.parentnodeid
  is '父节点';
comment on column APP_ORGAN_NODE.nodetypeid
  is '节点类型';
comment on column APP_ORGAN_NODE.domainid
  is '所属域';
comment on column APP_ORGAN_NODE.groupid
  is '关联用户组';
comment on column APP_ORGAN_NODE.value
  is '组织节点值';
comment on column APP_ORGAN_NODE.name
  is '名称';
comment on column APP_ORGAN_NODE.description
  is '描述';
comment on column APP_ORGAN_NODE.status
  is '状态';
  
create index APP_ORGAN_NODE_GROUPID on APP_ORGAN_NODE(GROUPID);

create sequence SEQ_APP_PERMISSIONID
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

-- Create table
create table APP_PERMISSION
(
  permissionid NUMBER(19) primary key,
  groupid     NUMBER(19) ,
  resourceid   NUMBER(19),
  status       NUMBER(1)
);
-- Add comments to the table 
comment on table APP_PERMISSION
  is '权限信息';
-- Add comments to the columns 
comment on column APP_PERMISSION.permissionid
  is '权限标识';
comment on column APP_PERMISSION.groupid
  is '组标识';
comment on column APP_PERMISSION.resourceid
  is '资源标识';
comment on column APP_PERMISSION.resourceid
  is '状态';
 
create index APP_PERMISSION_GROUPID on APP_PERMISSION(GROUPID);
create index APP_PERMISSION_RESOURCEID on APP_PERMISSION(RESOURCEID);



create sequence SEQ_APP_RESOURCEID
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
-- Create table
create table APP_RESOURCE
(
  resourceid       NUMBER(19),
  domainid     NUMBER(19),
  appid	number(19),
  parentresourceid NUMBER(19),
  code         VARCHAR2(100),
  name         VARCHAR2(100),
  description  VARCHAR2(300),
  url          VARCHAR2(300),
  icon1        NUMBER(19),
  icon2        NUMBER(19),
  icon3        NUMBER(19),
  priority     NUMBER(19) not null,
  status       NUMBER(1) default 1
);
-- Add comments to the table 
comment on table APP_RESOURCE
  is '菜单资源';
-- Add comments to the columns 
comment on column APP_RESOURCE.resourceid
  is '菜单标识';
comment on column APP_RESOURCE.domainid
  is '所属域';
comment on column APP_RESOURCE.appid
  is '所属应用';
comment on column APP_RESOURCE.parentresourceid
  is '父菜单';
comment on column APP_RESOURCE.code
  is '代码';
comment on column APP_RESOURCE.name
  is '名称';
comment on column APP_RESOURCE.description
  is '描述';
comment on column APP_RESOURCE.url
  is '链接';
comment on column APP_RESOURCE.icon1
  is '图标1';
comment on column APP_RESOURCE.icon2
  is '图标2';
comment on column APP_RESOURCE.icon3
  is '图标3';
comment on column APP_RESOURCE.priority
  is '优先级';
comment on column APP_RESOURCE.status
  is '状态';
  


create sequence SEQ_APP_AA09
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
-- Create table
create table APP_AA09
(
  aaa001   NUMBER(16) primary key,
  aaa100   VARCHAR2(20),
  aaa101   VARCHAR2(50),
  aaa104   VARCHAR2(1),
  aae100   VARCHAR2(1)
);
-- Add comments to the table 
comment on table APP_AA09
  is 'APP_AA09（代码类别）';
-- Add comments to the columns 
comment on column APP_AA09.aaa001
  is '代码类别ID';
comment on column APP_AA09.aaa100
  is '代码类别';
comment on column APP_AA09.aaa101
  is '类别名称';
comment on column APP_AA09.aaa104
  is '代码可维护标志';
comment on column APP_AA09.aae100
  is '有效标志';


-- Create table
create table APP_AA10
(
  aaa002   NUMBER(16)  primary key,
  aaa100   VARCHAR2(20),
  aaa102   VARCHAR2(8),
  aaa103   VARCHAR2(300),
  aae030   NUMBER(8),
  aae031   NUMBER(8),
  cae008   NUMBER(16),
  aae013   VARCHAR2(100),
  aae100   VARCHAR2(1)
);
-- Add comments to the table 
comment on table APP_AA10
  is 'APP_AA10（代码表）';
-- Add comments to the columns 
comment on column APP_AA10.aaa002
  is '代码ID';
comment on column APP_AA10.aaa100
  is '代码类别';
comment on column APP_AA10.aaa102
  is '代码值';
comment on column APP_AA10.aaa103
  is '代码名称';
comment on column APP_AA10.aae030
  is '开始日期';
comment on column APP_AA10.aae031
  is '终止日期';
comment on column APP_AA10.cae008
  is '排序编号';
comment on column APP_AA10.aae013
  is '备注';
comment on column APP_AA10.aae100
  is '有效标志';






insert into app_resource(resourceid,domainid,parentresourceid,code,name,url,priority,status)
values(900000,-1,null,900000,'系统管理',null,1,1);
insert into app_resource(resourceid,domainid,parentresourceid,code,name,url,priority,status)
values(910000,-1,900000,910000,'权限管理',null,1,1);
insert into app_resource(resourceid,domainid,parentresourceid,code,name,url,priority,status)
values(910001,-1,910000,910001,'用户管理','/admin/user.action',1,1);
insert into app_resource(resourceid,domainid,parentresourceid,code,name,url,priority,status)
values(910002,-1,910000,910002,'组织管理','/admin/organ.action',2,1);
insert into app_resource(resourceid,domainid,parentresourceid,code,name,url,priority,status)
values(910003,-1,910000,910003,'角色管理','/admin/group.action',3,1);
insert into app_resource(resourceid,domainid,parentresourceid,code,name,url,priority,status)
values(910004,-1,910000,910004,'用户授权','/admin/user_permission.action',4,1);
insert into app_resource(resourceid,domainid,parentresourceid,code,name,url,priority,status)
values(910005,-1,910000,910005,'角色授权','/admin/role_permission.action',5,1);