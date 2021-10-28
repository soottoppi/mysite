-- scheme user
desc user;

-- insert
insert into user values(null, '둘리', 'dooly@mgail.com', '1234', 'male', now());

select * from emaillist;

-- select01
select no, name from user where email='dooly@mgail.com' and password='1234';
select no, name from user where no = 10;
-- select02
select * from user;

delete from user where no = 17;

select no, name, email, password, gender from user where no = 10;

update user
set name = '홍길동', password = '1111', gender='female'
where no = 10;
delete from user;

select no, name, password
		  from user
		 where email='duwjs1103@gmail.com'
		   and password='123';
           

alter table user add column role enum('USER', 'ADMIN') not null default 'USER';
select * from user;
insert into user values(null, '관리자', 'admin@mysite.com', '123', 'male', now(), 'ADMIN');

desc site;

select * from site;

insert into site values(null, 'MySite', '안녕하세요. 김수형의 홈페이지에 오신 것을 환영합니다.', '/gallery/images/cat.png', '이 사이트는 웹 프로그램밍 실습과제 예제 사이트입니다.
\n메뉴는 사이트 소개, 방명록, 게시판이 있구요. \nJava수업 + 데이터베이스 수업 + 웹프로그래밍 수업 배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트 입니다.');