```sql
CREATE VIEW 뷰이름[(속성이름[,속성이름])]
AS 
SELECT문;
```

```sql
-- 고객 테이블에서 주소가 서울시인 고객들의 성명과 전화번호를 [서울고객이라는 뷰]로 만들어라
CREATE VIEW 서울고객(성명, 전화번호)
AS 
SELECT 
       성명, 전화번호
  FROM 고객
 WHERE 주소 = '서울시';
```
