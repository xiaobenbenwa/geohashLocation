mysql的别名
alias mysql=/usr/local/mysql/bin/mysql
alias mysqladmin=/usr/local/mysql/bin/mysqladmin

root下改密码
mysqladmin -u root password 123456

以root身份登录mysql
mysql －u root －p123456


database:zhoulu 

//建表加上default charset=utf8，数据表编码为中文
CREATE TABLE STUDENT  
(  
SNO CHAR(7) NOT NULL,  
SNAME VARCHAR(8) NOT NULL,  
SEX CHAR(2) NOT NULL,  
BDATE DATE NOT NULL,  
HEIGHT DEC(5,2) DEFAULT 000.00,  
PRIMARY KEY(SNO)  
)default charset=utf8;  
insert into <表名> values (value1, value2, ...);

插入语句后面加上set names utf8;
set names utf8;  
insert into STUDENT values ('198','陈天佑','男','2011-08-24','98');


查询城市和经纬度

select geo_location,count(distinct city) from ml_user_latest_nearby_hourly 
where partition_date='20150419'
and country='中国'


select city,lng,lat from ml_user_latest_nearby_hourly
lateral view explode(geo_location) r1 as 

select city,lng,lat from
(select city,
geo_location['lng'] as lng,
geo_location['lat'] as lat,
ROW_NUMBER() over(partition by city order by rand()) as row_number from ml_user_latest_nearby_hourly
where partition_date='20150422'
and country='中国') t1
where row_number<=200;


**********建地理位置信息表***********************

CREATE TABLE location_info 
( 
geohash CHAR(5),
lat VARCHAR(255), 
lng VARCHAR(255), 
nation VARCHAR(255), 
province VARCHAR(255), 
city VARCHAR(255), 
district VARCHAR(255), 
street VARCHAR(255), 
street_number VARCHAR(255)
 )default charset=utf8;  
 
 
 select district,count(distinct loc) from
 (select district,substring(geohash,1,4) as loc from location_info) t1
 group  by district;

 ********************** 删除表中全部数据*********************
 TRUNCATE TABLE location_info;