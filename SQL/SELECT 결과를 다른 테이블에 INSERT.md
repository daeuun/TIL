### SELECT 결과를 다른 테이블에 INSERT

*⇒ 조회 데이터가 너무 적어서 데이터를 늘리기 위해 사용한 방법..*

```sql
INSERT
  INTO tbl_temp2 (fld_id)
SELECT tbl_temp1.fld_order_id
  FROM tbl_temp1
 WHERE tbl_temp1.fld_order_id > 100;
```
