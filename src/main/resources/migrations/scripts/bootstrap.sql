--//MySQL
--// Create Shiro Tables
--CREATE TABLE shiro_user_role (user_id BIGINT NOT NULL AUTO_INCREMENT, role_id BIGINT NOT NULL, PRIMARY KEY (user_id, role_id));
--CREATE TABLE shiro_role_permission (role_id BIGINT NOT NULL, permission VARCHAR(50) NOT NULL, PRIMARY KEY (role_id, permission));
--CREATE TABLE shiro_user (id BIGINT NOT NULL AUTO_INCREMENT, userid VARCHAR(100) NOT NULL UNIQUE, email VARCHAR(100) NOT NULL UNIQUE, passphrase VARCHAR(100) NOT NULL, salt VARCHAR(100) NOT NULL, date_created DATE NOT NULL, PRIMARY KEY (id));
--CREATE TABLE shiro_role (id BIGINT NOT NULL AUTO_INCREMENT, description VARCHAR(255), name VARCHAR(50) NOT NULL, PRIMARY KEY (id));
--ALTER TABLE shiro_role_permission ADD CONSTRAINT fk_shiro_role_id FOREIGN KEY (role_id) REFERENCES shiro_role (id);
--ALTER TABLE shiro_user_role ADD CONSTRAINT fk_shiro_user_role_user_id FOREIGN KEY (user_id) REFERENCES shiro_user (id);
--ALTER TABLE shiro_user_role ADD CONSTRAINT fk_shiro_user_role_role_id FOREIGN KEY (role_id) REFERENCES shiro_role (id);
--CREATE TABLE shiro_sequence (seq_name VARCHAR(50) NOT NULL, seq_count NUMERIC(38), PRIMARY KEY (seq_name));
--INSERT INTO shiro_sequence (seq_name, seq_count) VALUES ('shiro_user_seq', 0);
--INSERT INTO shiro_sequence (seq_name, seq_count) VALUES ('shiro_role_seq', 0);
----// Create a TestUser with a password of TestUserPassword
--INSERT INTO shiro_user (userid, passphrase, salt, email, date_created) VALUES ('TestUser', 'M1IFzumVt5cZznXtuE7uBS5xFE62vpcQY939F12ZTGQuJS9/vrnGKOiTu+cJGDEZO1XfJQYATVLO7qQTDuiCfA==', 'Cv2YXgmaudkMcw0/10T0jw==', 'TestUser@test.com', CURDATE()); 
--INSERT INTO shiro_role (description, name) VALUES ('Test Role', 'Test');
--INSERT INTO shiro_role_permission (role_id, permission) VALUES ( (SELECT id FROM shiro_role where name = 'Test' ), 'read');
--INSERT INTO shiro_user_role (user_id, role_id) VALUES ((SELECT id FROM shiro_user where userid = 'TestUser' ), (SELECT id FROM shiro_role where name = 'Test' ));

--//PostgreSQL
drop table if exists basic_role_menu_for_authorization CASCADE;
drop table if exists basic_role_menu CASCADE;
drop table if exists menu_permission CASCADE;
drop table if exists basic_menu CASCADE;
drop table if exists shiro_role_permission CASCADE;
drop table if exists shiro_user_role CASCADE;
drop table if exists shiro_role CASCADE;
drop table if exists shiro_user CASCADE;
drop table if exists basic_organization CASCADE;

--组织机构表
create table basic_organization (id serial not null, name varchar(40), parent_id bigint, order_number int default 999999, remark varchar(200), primary key(id));
alter table basic_organization add constraint fk_basic_organization_parent_id foreign key (parent_id) references basic_organization (id);
comment on table basic_organization is '组织机构表';
comment on column basic_organization.id is '部门唯一标识（从0逐1递增）';
comment on column basic_organization.name is '部门名称（最大长度为50）';
comment on column basic_organization.parent_id is '父级部门id（长整型）';
comment on column basic_organization.order_number is '用于排序的序号，默认为999999，数值越大，排序靠前（整型）';
comment on column basic_organization.remark is '部门备注信息（最大长度为200）';
comment on constraint fk_basic_organization_parent_id on basic_organization is 'basic_organization。parent_id的外键约束，指向basic_organization.id';
INSERT INTO BASIC_ORGANIZATION ( NAME, REMARK ) values ( '组织架构','组织架构备注' ) ;

--用户表
CREATE TABLE shiro_user (id SERIAL NOT NULL, login_id VARCHAR(50) NOT NULL UNIQUE, name varchar(50), organization_id bigint, email VARCHAR(100) NOT NULL UNIQUE, passphrase VARCHAR(100) NOT NULL, salt VARCHAR(100) NOT NULL, state boolean default true, date_created TIMESTAMP NOT NULL, remark varchar(200), PRIMARY KEY (id));
alter table shiro_user add constraint fk_shiro_user_organization_id foreign key (organization_id) references basic_organization (id);
comment on table shiro_user is '用户表';
comment on column shiro_user.id is '用户唯一标识（从0逐1递增）';
comment on column shiro_user.login_id is '用户登录账号（具有唯一性，最大长度为50）';
comment on column shiro_user.name is '用户名（人名可重复，最大长度为50）';
comment on column shiro_user.organization_id is '所属部门（长整型）';
comment on column shiro_user.email is 'email地址（具有唯一性，最大长度为100）';
comment on column shiro_user.passphrase is '密码（明文至少为8位，此为加密后的文字，最大长度为100）';
comment on column shiro_user.salt is '密码调料（最大长度为100）';
comment on column shiro_user.state is '用户状态，boolean类型，true为激活状态，false为锁定状态，默认为true';
comment on column shiro_user.date_created is '用户创建时间';
comment on column shiro_user.remark is '用户备注';
comment on constraint fk_shiro_user_organization_id on shiro_user is 'shiro_user.organization_id的外键，指向basic_organization';
--该用户位超级用户：admin 111111
insert into shiro_user (login_id, name, email, passphrase, salt, state, date_created) values ('admin','admin','adminadmin@gmail.com', '9nr6bzUO+BwcJrk8/WQl2XSPb9M10Ra53TEf6TyA9XHqdBWp3AvzjKLPkqWZx6zmARLywD6Mw5lPMYTW/uGwkQ==', 'qpOvViSVIY7XyYMpAsJHnQ==', TRUE, '2013-06-25 14:54:22.646');

--角色表
CREATE TABLE shiro_role (name VARCHAR(50) NOT NULL, description VARCHAR(200), organization_id bigint, permission varchar(300), menu VARCHAR(500), remark varchar(200), state boolean default true, PRIMARY KEY (name));
alter table shiro_role add constraint fk_shiro_role_organization_id foreign key (organization_id) references basic_organization(id);
comment on table shiro_role is '角色表';
comment on column shiro_role.name is '角色名称（最大长度为50,唯一标识）';
comment on column shiro_role.description is '角色描述(最大长度为200)';
comment on column shiro_role.organization_id is '所属部门id（长整型）';
comment on column shiro_role.permission is '角色所包含的权限，逗号分隔的菜单id，包含其父节点（最大长度为1000）';
comment on column shiro_role.remark is '角色备注（最大长度为200）';
comment on column shiro_role.state is '角色状态（boolean类型）true为激活状态，false为锁定状态，默认为true';
comment on constraint fk_shiro_role_organization_id on shiro_role is 'shiro_role.organization_id外键，指向basic_organization.id';
insert into shiro_role (name,description, organization_id, permission, menu,  remark, state) values ('developer', '开发者角色',1, '[{"permission":"menu:getallxx","remark":"eeee"}]',  '1,2,3,4,5,6,7,8', '开发者角色', true);

--用户角色关联表
CREATE TABLE shiro_user_role (user_id SERIAL NOT NULL , role_name VARCHAR(50) NOT NULL, PRIMARY KEY (user_id, role_name));
ALTER TABLE shiro_user_role ADD CONSTRAINT fk_shiro_user_role_user_id FOREIGN KEY (user_id) REFERENCES shiro_user (id);
ALTER TABLE shiro_user_role ADD CONSTRAINT fk_shiro_user_role_role_name FOREIGN KEY (role_name) REFERENCES shiro_role (name);
comment on table shiro_user_role is '用户id与其角色id关联表';
comment on column shiro_user_role.user_id is '用户唯一标识';
comment on column shiro_user_role.role_name is '角色唯一标识';
comment on constraint fk_shiro_user_role_user_id on shiro_user_role is 'shiro_user_role.user_id外键，指向shiro_user.id';
comment on constraint fk_shiro_user_role_role_name on shiro_user_role is 'shiro_user_role.role_name外键，指向shiro_role.id';
insert into shiro_user_role (user_id, role_name) values (1, 'developer');

--角色权限关联表  --------暂时不用~直接采用menu里面的fullpermission
--CREATE TABLE shiro_role_permission (role_id BIGINT NOT NULL, permission VARCHAR(100) NOT NULL, PRIMARY KEY (role_id, permission));
--ALTER TABLE shiro_role_permission ADD CONSTRAINT fk_shiro_role_id FOREIGN KEY (role_id) REFERENCES shiro_role (id);
--comment on table shiro_role_permission is '角色id与权限关联表 --------暂时不用~直接采用menu里面的fullpermission';
--comment on column shiro_role_permission.role_id is '角色唯一标识';
--comment on column shiro_role_permission.permission is '权限，单行只允许存入单个权限，如果有多个，需要分开单独存储多行。最大长度为100。';
--comment on constraint fk_shiro_role_id on shiro_role_permission is 'shiro_role_permission.role_id外键，指向shiro_role.id';


--树级菜单条目详细表
create table basic_menu (id serial not null, name varchar(20), parent_id integer, link_url varchar(300) not null, order_number int default 999999, remark varchar(200), full_permission varchar(300), read_permission varchar(300), primary key(id));
ALTER TABLE basic_menu ADD CONSTRAINT fk_basic_menu_parent_id FOREIGN KEY (parent_id) REFERENCES basic_menu (id);
comment on table basic_menu is '树级菜单条目详细表';
comment on column basic_menu.id is '树形菜单条目唯一标识';
comment on column basic_menu.name is '树形菜单名称（最长为20个字符）';
comment on column basic_menu.parent_id is '父级菜单唯一标识（外键）';
comment on column basic_menu.order_number is '用于排序的序号，默认为999999，数值越大，排序靠前（整型）';
comment on column basic_menu.remark is '树形菜单备注说明';
comment on column basic_menu.full_permission is '可执行所有操作权限，由分号分隔，如：account:create；account:read';
comment on column basic_menu.read_permission is '可执行读取的操作权限，由分号分隔，如：account:create；account:read';
comment on constraint fk_basic_menu_parent_id on basic_menu is 'basic_menu.parent_id外键，指向basicmenu.id';
insert into basic_menu (name, link_url) values ('root','') ;
insert into basic_menu (name, parent_id, link_url) values ('系统管理',1,''); 
insert into basic_menu (name, parent_id, link_url) values ('开发人员管理',1,''); 
insert into basic_menu (name, parent_id, link_url) values ('模板子系统',1,''); 
insert into basic_menu (name, parent_id, link_url) values ('权限管理',2,''); 
insert into basic_menu (name, parent_id, link_url) values ('菜单资源管理',5,'menu/manage'); 
insert into basic_menu (name, parent_id, link_url) values ('角色管理',5,'role/manage');
insert into basic_menu (name, parent_id, link_url) values ('用户管理',5,'user/manage');
insert into basic_menu (name, parent_id, link_url) values ('组织机构管理',5,'organization/manage');


--菜单权限表 
--create table basic_menu_permission(id SERIAL NOT NULL,menu_id int not null, permission varchar(100) not null, primary key(id));
--alter table basic_menu_permission add constraint fk_basic_menu_permission_menu_id foreign key (menu_id) references basic_menu(id);
--comment on table basic_menu_permission is '菜单权限表，用于描述某一菜单所包含的的权限(暂时只考虑，一个菜单只包含一种权限，即所有子链接或功能，公用该权限)' 
--comment on column basic_menu.id is '菜单权限条目唯一标识';
--comment on column basic_menu_permission.menu_id is '菜单条目唯一标识,外键';
--comment on column basic_menu_permission.permission is '权限，单行只允许存入单个权限，如果有多个，需要分开单独存储多行。最大长度为100。';
--comment on constraint fk_basic_menu_permission_menu_id on basic_menu_permission is 'basic_menu_permission.menu_id外键，指向basic_menu.id';

----角色菜单表
--create table basic_role_menu(role_name varchar(50) not null, menu_id int not null, primary key(role_name, menu_id));
--alter table basic_role_menu add constraint fk_basic_role_menu_role_name foreign key (role_name) references shiro_role(name);
--alter table basic_role_menu add constraint fk_basic_role_menu_menu_id foreign key (menu_id) references basic_menu(id);
--comment on table basic_role_menu is '角色所用关联的菜单表';
--comment on column basic_role_menu.role_name is '角色唯一标识（外键）';
--comment on column basic_role_menu.menu_id is '菜单条目唯一标识（外键）';
--comment on constraint fk_basic_role_menu_role_name on basic_role_menu is 'basic_role_menu.role_name外键，指向shiro_role.id';
--comment on constraint fk_basic_role_menu_menu_id on basic_role_menu is 'basic_role_menu.menu_id外键，指向basic_menu.id';


--角色可授权菜单表
--create table basic_role_menu_for_authorization(role_id int not null, menu_id int not null, primary key(role_id, menu_id));
--alter table basic_role_menu_for_authorization add constraint fk_basic_role_menu_for_authorization_role_id foreign key (role_id) references shiro_role(id);
--alter table basic_role_menu_for_authorization add constraint fk_basic_role_menu_for_authorization_menu_id foreign key (menu_id) references basic_menu(id);
--comment on table basic_role_menu_for_authorization is '角色可授权菜单表';
--comment on column basic_role_menu_for_authorization.role_id is '角色唯一标识（外键）';
--comment on column basic_role_menu_for_authorization.menu_id is '菜单条目唯一标识（外键）';
--comment on constraint fk_basic_role_menu_for_authorization_role_id on basic_role_menu_for_authorization is 'basic_role_menu_for_authorization.role_id外键，指向shiro_role.id';
--comment on constraint fk_basic_role_menu_for_authorization_menu_id on basic_role_menu_for_authorization is 'basic_role_menu_for_authorization.menu_id外键，指向basic_menu.id';
--

--通用数据字典
--create table system_data_dictionary 

--全局参数表
--create table system_global_parameter

--系统异常记录
--create table system_exception



--// Create a TestUser with a password of TestUserPassword
--INSERT INTO shiro_user (userid, passphrase, salt, email, date_created) VALUES ('TestUser', 'M1IFzumVt5cZznXtuE7uBS5xFE62vpcQY939F12ZTGQuJS9/vrnGKOiTu+cJGDEZO1XfJQYATVLO7qQTDuiCfA==', 'Cv2YXgmaudkMcw0/10T0jw==', 'TestUser@test.com', NOW()); 
--INSERT INTO shiro_role (description, name) VALUES ('Test Role', 'Test');
--INSERT INTO shiro_role_permission (role_id, permission) VALUES ( (SELECT id FROM shiro_role where name = 'Test' ), 'read');
--INSERT INTO shiro_user_role (user_id, role_id) VALUES ((SELECT id FROM shiro_user where userid = 'TestUser' ), (SELECT id FROM shiro_role where name = 'Test' ));














