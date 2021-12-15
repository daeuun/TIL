1. 커서 선언  
CURSOR 커서이름 IS select문장;  
2. 커서 열기  
OPEN 커서이름;  
3. 커서로부터 데이터 읽기  (LOOP end의 반복문을 활용한다)  
FETCH 커서이름 INTO 저장할 로컬변수   
4. 커서닫기  
CLOSE 커서이름

```sql
OPEN V_CUR; -- 커서 열기
LOOP
FETCH V_CUR INTO V_TMP; -- V_CUR 커서에서 V_TMP에 값 담기
EXIT WHEN V_CUR%NOTFOUND; --커서에서 데이터를 찾을수 없으면 반복문 빠져나가라

    BEGIN
        V_UNI :=V_TMP.RATE;
        V_SQL2 := V_SQL2 || ',MAX(CASE WHEN A.RATE =''' || V_UNI || ''' THEN A.RATE ELSE N'''' END) AS RATE' || V_TMP.NUM;
        V_SQL2 := V_SQL2 || ',MAX(CASE WHEN A.RATE =''' || V_UNI || ''' THEN A.AMT ELSE 0 END) AS AMT' || V_TMP.NUM;
        V_SQL2 := V_SQL2 || ',MAX(CASE WHEN A.RATE =''' || V_UNI || ''' THEN A.VAT ELSE 0 END) AS VAT' || V_TMP.NUM;
        V_SQL2 := V_SQL2 || ',MAX(CASE WHEN A.RATE =''' || V_UNI || ''' THEN A.SUM ELSE 0 END) AS SUM' || V_TMP.NUM;
              
        V_SQL3 :=  V_SQL3  || V_SQL2;
     END;
END LOOP;
CLOSE V_CUR;
```
