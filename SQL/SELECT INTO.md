### SELECT INTO

**테이블에서 조회한 데이터를 INTO 변수에 담기** 
+ select의 조회한 결과 컬럼값을 ⇒ INTO의 변수에 대입 

+ 조회되는 행은 하나여야 한다.


```sql
DECLARE 	
    변수 NUMBER := 0; 
  BEGIN 	
    SELECT COUNT(*) CNT 	 
      INTO 변수 	 
      FROM TBL 	 
     WHERE DEPT = #{DEPT}; 
	
  IF 변수 = 0 THEN 	
	
    INSERT INTO USERTABLE 
    ( 	 
    ); 	
  
  ELSE 	
	
    UPDATE USERTABLE; 	
  
  END IF; 
END;

```




