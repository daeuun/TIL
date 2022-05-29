구조가 같은 두개의 테이블을 비교하여 하나의 테이블로 합치기 위한 명령어.

```sql
MERGE INTO [TABLE / VIEW] -- update 또는 insert할 테이블 혹은 뷰
USING [TABLE / VIEW / DUAL] -- 비교할 대상 테이블 혹은 뷰 (위 테이블과 동일할 경우 DUAL을 사용)
   ON [조건] -- UPDATE 와 INSERT 처리할 조건문 (조건이 일치하면 UPDATE / 불일치 시 INSERT)
 WHEN MATCHED THEN
      UPDATE SET
      [COLUMN1] = [VALUE1],
      [COLUMN2] = [VALUE2],
      ...
      (DELETE [TABLE] WHERE [COLUMN 1] = [VALUE 1] AND ...) -- UPDATE 뿐만 아니라 DELETE 구문도 사용 가능
 WHEN NOT MATCHED THEN
      INSERT (COLUMN1, COLUMN2, ...)         
      VALUES (VALUE1, VALUE2, ...)
```
