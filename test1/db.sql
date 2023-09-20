
-- c.1.保证incident_id的唯一性，如表中有重复incident_id，则保留start_date_time最晚的那一条，如start_date_time一样，就保留那个offence_code最大的那一条。
-- c.1.1新建中间表
CREATE TABLE `crime_data_new` (
  `incident_id` int DEFAULT NULL,
  `offence_code` int DEFAULT NULL,
  `dispatch_time` datetime DEFAULT NULL,
  `victims` int DEFAULT NULL,
  `crime_name1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `crime_name2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `crime_name3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `start_date_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- c.1.2查询出不重复incident_id数据并转移到中间表

INSERT INTO crime_data_new (incident_id,
  offence_code,dispatch_time,victims,crime_name1,
  crime_name2,crime_name3,city,start_date_time)
SELECT incident_id,offence_code,dispatch_time,
  victims,crime_name1,crime_name2,crime_name3,city,start_date_time FROM crime_data where incident_id in (select incident_id from crime_data group by incident_id HAVING count(incident_id)=1 )
	
	
-- c.1.3再将符合的数据插入到中间表

INSERT INTO crime_data_new (incident_id,
  offence_code,dispatch_time,victims,crime_name1,
  crime_name2,crime_name3,city,start_date_time)
select c.incident_id,c.offence_code,c.dispatch_time,c.victims,c.crime_name1,c.crime_name2,c.crime_name3,c.city,c.start_date_time from 
(
select a.*,IF(@bak = incident_id,@rownum:=@rownum+1,@rownum:=1) as row_num,@bak:=incident_id from 
(SELECT * FROM crime_data where incident_id
 in (select incident_id from crime_data group by incident_id HAVING count(incident_id)>1 )
order by incident_id asc,start_date_time,offence_code desc) a,(select @rownum:=0,@bak:='') b) c
where c.row_num = 1

-- c.1.4将原表清空
TRUNCATE crime_data

-- c.1.5将中间表数据按照顺序插入到原表
INSERT INTO crime_data (incident_id,
  offence_code,dispatch_time,victims,crime_name1,
  crime_name2,crime_name3,city,start_date_time)
	select c.incident_id,c.offence_code,c.dispatch_time,c.victims,c.crime_name1,c.crime_name2,c.crime_name3,c.city,c.start_date_time from crime_data_new c order by c.incident_id asc

-- c.2. 所有字段不能为空。如果 dispatch_time值为空，则以start_date_time的值代替。如其它字段值为空，则删除本条记录。
delete FROM crime_data  c
WHERE c.incident_id is  null or c.incident_id=''
	 or c.offence_code is  null or c.offence_code=''
	 or c.dispatch_time is null
	 or c.victims is  null or c.victims=''
	 or c.crime_name1 is  null or c.crime_name1=''
	 or c.crime_name2 is  null or c.crime_name2=''
	 or c.crime_name3 is  null or c.crime_name3=''
	 or c.city is  null or c.city=''
	 or c.start_date_time is  null;  
	 
UPDATE crime_data  
SET dispatch_time = start_date_time  
WHERE dispatch_time IS NULL;


-- c.3. 删除start_date_time在 2020年之前的所有记录
DELETE FROM crime_data  
WHERE YEAR(start_date_time) < 2020;

-- c.4. 确保城市名称都为大写字母
UPDATE crime_data  
SET city = UPPER(city); 

-- c.5. 如发现有其它异常数据，也要删除或修正。 
DELETE from crime_data where crime_name1 REGEXP '^[0-9]+$'
or crime_name2 REGEXP '^[0-9]+$'
or crime_name3 REGEXP '^[0-9]+$'
or city REGEXP '^[0-9]+$'

-- d.1.1  crimes表: 以incident_id为主键，包含除crime_name1、crime_name2、crime_name3之外的所有字段。

CREATE TABLE `crimes` (
  `incident_id` int AUTO_INCREMENT NOT NULL,
  `offence_code` int NOT NULL,
  `dispatch_time` datetime NOT NULL,
  `victims` int NOT NULL,
  `city` varchar(255) NOT NULL,
  `start_date_time` datetime NOT NULL,
  PRIMARY KEY (`incident_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--       d.1.2. offences表: 以offence_code为主键，包含crime_name1、crime_name2、crime_name3这几个字段。
CREATE TABLE `offences` (
  `offence_code` int NOT NULL,
  `crime_name1` varchar(255) NOT NULL,
  `crime_name2` varchar(255) NOT NULL,
  `crime_name3` varchar(255) NOT NULL,
  PRIMARY KEY (`offence_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- d.2.将原始表数据导入这两张表中。并保证所有字段不为Null。
INSERT INTO crimes(incident_id,offence_code ,dispatch_time,
  victims,city,start_date_time)
	SELECT incident_id,offence_code ,dispatch_time,
  victims,city,start_date_time FROM crime_data WHERE incident_id is  not null and offence_code is not null
	and dispatch_time is not null
	 and victims is not null
	 and city is not null
	 and start_date_time is not null
	 
	 
	 INSERT INTO offences(offence_code ,crime_name1,
  crime_name2,crime_name3)
	SELECT offence_code ,crime_name1,crime_name2,crime_name3 FROM crime_data    WHERE offence_code is not null
	and crime_name1 is not null
	 and crime_name2 is not null
	 and crime_name3 is not null group by offence_code ,crime_name1,crime_name2,crime_name3
