desc board;

select b.no, title, contents, hit, reg_date, group_no, order_no, depth, user_no, name 
from board b, user u
where b.user_no = u.no
order by group_no desc limit 0, 10;            

select max(group_no) from board;
select title, contents from board where group_no = 1 and order_no = 1 and depth = 0;

select * from board order by no desc, order_no ASC;

select b.no, title, contents, hit, reg_date, group_no, order_no, depth, user_no, name 
from board b, user u
where b.user_no = u.no
order by group_no desc, order_no asc 
limit 0, 10;  

update board set hit = hit+1 where group_no = 5 and order_no = 1 and depth = 0;
update board set title='456', contents='7\n8\n9' where group_no = 7 and order_no = 1 and depth = 0;

update board set order_no = order_no + 1 where group_no = 변수 and order_no >= 변수;

insert into board values(null, '1번 제목입니다', '1번 내용입니다', 1, now(), 1, 1, 0, 10);

delete from board;

delete b
from board b join user a on b.user_no=a.no
where a.password=123 and b.no=4;
delete from board where group_no = 5;

SELECT * 
	FROM (
    	 SELECT A.*, ROWNUM AS RNUM, FLOOR((ROWNUM-1)/{디스플레이수}+1) AS PAGE, COUNT(*) OVER() AS TOTCNT 
			FROM (
          	
    		) A
	) 
WHERE PAGE = {페이지번호};

select * 
   from (
         select A.*, ROWNUM AS RNUM, COUNT(*) OVER(order by group_no desc, order_no asc) AS TOTCNT 
                  from board as A
              ) 
  WHERE RNUM > {범위부터} AND RNUM <= {범위까지};

select R1.* 
from (
	select b.*, u.name
		from board b, user u
	where b.user_no = u.no
	) R1
order by group_no desc, order_no asc
limit 0, 10;