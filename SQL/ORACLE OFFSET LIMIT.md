```sql
SELECT *
  FROM (SELECT a.*
             , ROWNUM AS rnum
          FROM (사용자정의쿼리) a
         WHERE ROWNUM <= end_num )a
 WHERE rnum >= start_number
```

```sql
SELECT * 
  FROM 테이블명 
 ORDER BY 컬럼명 
OFFSET 20 ROWS FETCH NEXT 10 ROWS ONLY
```
