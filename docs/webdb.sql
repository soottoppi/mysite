-- scheme user
desc user;

-- insert
insert into user values(null, '둘리', 'dooly@mgail.com', '1234', 'male', now());

-- select01
select no, name from user where email='dooly@mgail.com' and password='1234';

-- select02
select * from user;


select no, name, email, password, gender from user where no = 10;

update user
set name = '홍길동', password = '1111', gender='female'
where no = 10;
delete from user;