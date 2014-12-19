

drop table if exists basic_menu CASCADE;
drop table if exists shiro_user_role CASCADE;
drop table if exists shiro_role CASCADE;
drop table if exists shiro_user CASCADE;
drop table if exists basic_organization CASCADE;


create table basic_organization (id int not null AUTO_INCREMENT, name varchar(40), parent_id int, organizationType int, order_number int default 999999, remark varchar(200), primary key(id), foreign key (parent_id) references basic_organization (id));

INSERT INTO BASIC_ORGANIZATION ( NAME, REMARK ) values ( '组织架构','组织架构备注' ) ;

CREATE TABLE shiro_user (id int NOT NULL AUTO_INCREMENT, login_id VARCHAR(50) NOT NULL UNIQUE, name varchar(50), organization_id int, email VARCHAR(100) NOT NULL UNIQUE, passphrase VARCHAR(100) NOT NULL, salt VARCHAR(100) NOT NULL, state boolean default true, date_created TIMESTAMP NOT NULL, remark varchar(200), PRIMARY KEY (id), foreign key (organization_id) references basic_organization(id));

insert into shiro_user (login_id, name, email, passphrase, salt, state, organization_id, date_created) values ('admin','admin','adminadmin@gmail.com', '9nr6bzUO+BwcJrk8/WQl2XSPb9M10Ra53TEf6TyA9XHqdBWp3AvzjKLPkqWZx6zmARLywD6Mw5lPMYTW/uGwkQ==', 'qpOvViSVIY7XyYMpAsJHnQ==', TRUE, 1,'2013-06-25 14:54:22.646');

CREATE TABLE shiro_role (name VARCHAR(50) NOT NULL, description VARCHAR(200), organization_id int, permission varchar(300), menu VARCHAR(500), remark varchar(200), state boolean default true, PRIMARY KEY (name), foreign key (organization_id) references basic_organization(id));
insert into shiro_role (name,description, organization_id, permission, menu,  remark, state) values ('developer', '开发者角色', 1,'[{"permission":"menu:getallxx","remark":"eeee"}]',  '1,2,3,4,5,6,7,8', '开发者角色', true);

CREATE TABLE shiro_user_role (user_id int NOT NULL , role_name VARCHAR(50) NOT NULL, PRIMARY KEY (user_id, role_name),FOREIGN KEY (user_id) REFERENCES shiro_user (id), FOREIGN KEY (role_name) REFERENCES shiro_role (name));
insert into shiro_user_role (user_id, role_name) values (1, 'developer');


create table basic_menu (id int not null AUTO_INCREMENT, name varchar(20), parent_id integer, link_url varchar(300) not null, order_number int default 999999, remark varchar(200), full_permission varchar(300), read_permission varchar(300), primary key(id), FOREIGN KEY (parent_id) REFERENCES basic_menu (id));


insert into basic_menu (name, link_url) values ('root','') ;
insert into basic_menu (name, parent_id, link_url) values ('系统管理',1,''); 
insert into basic_menu (name, parent_id, link_url) values ('开发人员管理',1,''); 
insert into basic_menu (name, parent_id, link_url) values ('模板子系统',1,''); 
insert into basic_menu (name, parent_id, link_url) values ('权限管理',2,''); 
insert into basic_menu (name, parent_id, link_url) values ('菜单资源管理',5,'menu/manage'); 
insert into basic_menu (name, parent_id, link_url) values ('角色管理',5,'role/manage');
insert into basic_menu (name, parent_id, link_url) values ('用户管理',5,'user/manage');
insert into basic_menu (name, parent_id, link_url) values ('组织机构管理',5,'organization/manage');



/*==============================================================*/
/* Table: SS_PROBLEM_RECTIFY                                    */
/*==============================================================*/
create table SS_PROBLEM_RECTIFY 
(
   id                   varchar(64)                    not null,
   checker              int                            null,
   deadLine             datetime                       null,
   checkerOrg           int                            null,
   createTime           datetime                       null,
   isDeleted            int                            null,
   primary key(id)
);


/*==============================================================*/
/* Table: SS_PROBLEM_TASKDETAIL                                 */
/*==============================================================*/
create table SS_PROBLEM_TASKDETAIL 
(
   ID                   varchar(64)                    null,
   taskDetailId         varchar(64)                    null,
   ssProblemFixId       varchar(64)                    not null,
   deadline             datetime                       null,
   isDeleted            int                            null,
   primary key(id)
);









